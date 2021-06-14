/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.enums;

public enum DataBaseEnum {
    MYSQL("MYSQL", "1"),
    ORACLE("ORACLE", "2"),
    SQLSERVER("SQLSERVER", "3"),
    POSTGRESQL("POSTGRESQL", "4");

    private String name;
    private String value;

    private DataBaseEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static String getDataBaseNameByValue(String value) {
        for (DataBaseEnum dataBaseEnum : DataBaseEnum.values()) {
            if (!dataBaseEnum.value.equals(value)) continue;
            return dataBaseEnum.name;
        }
        return DataBaseEnum.MYSQL.name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

