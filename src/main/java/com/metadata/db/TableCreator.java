package com.metadata.db;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解处理器
 */
public class TableCreator {
    public static void main(String args[]) throws Exception {
        if (args.length < 1) {
            System.out.println("no args");
            System.exit(-1);
        }
        for (String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if (dbTable == null) {
                System.out.println("NO DBTable in class" + className);
                continue;
            }
            String tableName = dbTable.name();
            // 如果name为空，则使用类名
            if (tableName.length() < 1) {
                tableName = cl.getName().toUpperCase();
            }

            List<String> columnDefs = new ArrayList<String>();
            for (Field field : cl.getDeclaredFields()) {
               String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if (anns.length<1){
                    // 说明不是一个列
                    continue;
                }
                // 处理SQLInteger
                if (anns[0] instanceof SQLInteger) {

                    SQLInteger sInt = (SQLInteger) anns[0];
                    // name没有指定则使用field name
                    if (sInt.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sInt.name();
                    columnDefs.add(columnName+ " INT "+ getConstraints(sInt.constraints()));
                }
                // 处理SQLInteger
                if (anns[0] instanceof SQLString){
                    SQLString sString = (SQLString)anns[0];
                    // name没有指定则使用field name
                    if (sString.name().length()<1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sString.name();
                    columnDefs.add(columnName+" VARCHAR("+sString.value()+")"+getConstraints(sString.constraints()));
                }

                // 拼接打印字符串
                StringBuilder createCommand = new StringBuilder();
                createCommand.append("CREATE TABLE"+tableName+"(");
                for(String columnDef :columnDefs)
                    createCommand.append("\n     "+columnDef +",");
                String tableCreate = createCommand.substring(0,createCommand.length()-1)+");";
                System.out.println("生成的SQL语句为："+className+"is:\n"+tableCreate);
            }
        }
    }

    /**
     * 拼接限制条件
     * @param con
     * @return
     */
    private static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull()) {
            constraints += " NOT NULL";
        }
        if (con.primaryKey()) {
            constraints += " PRIMARY KEY";
        }
        if (con.unique()) {
            constraints += " UNIQUE";
        }
        return constraints;
    }
}
