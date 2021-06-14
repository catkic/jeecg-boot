/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.config.service;

import org.jeecg.modules.online.config.b.a;
import org.jeecg.modules.online.config.exception.DBException;

public interface DbTableHandleI {
    public String getAddColumnSql(a var1);

    public String getReNameFieldName(a var1);

    public String getUpdateColumnSql(a var1, a var2) throws DBException;

    public String getMatchClassTypeByDataType(String var1, int var2);

    public String dropTableSQL(String var1);

    public String getDropColumnSql(String var1);

    public String getCommentSql(a var1);

    public String getSpecialHandle(a var1, a var2);

    public String dropIndexs(String var1, String var2);

    public String countIndex(String var1, String var2);
}

