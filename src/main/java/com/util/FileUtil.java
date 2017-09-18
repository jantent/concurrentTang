package com.util;

import java.io.*;

public class FileUtil {

    /**
     * 以byteArray的形式读文件的全部内容
     * @param path
     * @return
     * @throws IOException
     */
    public static final byte[] readFileAsByteArray(String path)throws IOException{
        File file = new File(path);
        FileInputStream fi = new FileInputStream(file);
        //获取字节大小，构造返回数组
        byte[] data = new byte[(int)file.length()];
        try{
            fi.read(data);
        }finally {
            fi.close();
        }
        return data;
    }

    /**
     * 以字符串的形式读文件的全部内容
     * @param path
     * @return
     * @throws IOException
     */
    public static final String readFileAsString(String path)throws IOException{
        return new String(readFileAsByteArray(path));
    }

    /**
     * 按指定行读取文件
     * @param path
     * @param rowcount 指定的行数
     * @return
     * @throws Exception
     */
    public static final String readFileAsRows(String path,int rowcount)throws Exception{
        File file = new File(path);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        InputStreamReader read = null;
        BufferedReader reader = null;
        try{
            read = new InputStreamReader(new FileInputStream(file));
            reader = new BufferedReader(read);
            String line;
            while((line = reader.readLine())!=null){
                sb.append(line);
                sb.append("\n");
                ++count;
                if (count==rowcount){
                    break;
                }
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (read!=null){
                read.close();
            }
            if(reader!=null){
                reader.close();
            }
        }
        return sb.toString();
    }

    /**
     * 将文件以byte形式写入
     * @param path
     * @param data
     * @throws Exception
     */
    public static final void writeFileToByteArray(String path, byte[]data) throws Exception{
        File file = new File(path);
        FileOutputStream fos =new FileOutputStream(file);
        try{
            fos.write(data);
        }finally {
            fos.close();
        }
    }
}
