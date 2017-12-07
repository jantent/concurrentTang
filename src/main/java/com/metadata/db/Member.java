package com.metadata.db;

@DBTable(name = "MEMBER")
public class Member {
    @SQLString(30)
    String firstName = "测试String";
    @SQLString(50)
    String lastName = "这是last";
    @SQLInteger
    Integer age;
    @SQLString(value = 30,constraints = @Constraints(primaryKey = true))
    String handle;
    static int memberCount;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getHandle() {
        return handle;
    }

    public static int getMemberCount() {
        return memberCount;
    }
}
