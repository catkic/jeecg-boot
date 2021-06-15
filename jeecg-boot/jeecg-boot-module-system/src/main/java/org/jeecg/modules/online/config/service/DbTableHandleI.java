/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.config.service;

import org.jeecg.modules.online.config.util.ColumnProperty;
import org.jeecg.modules.online.config.exception.DBException;

public interface DbTableHandleI {
    public String getAddColumnSql(ColumnProperty var1);

    public String getReNameFieldName(ColumnProperty var1);

    public String getUpdateColumnSql(ColumnProperty var1, ColumnProperty var2) throws DBException;

    public String getMatchClassTypeByDataType(String var1, int var2);

    public String dropTableSQL(String var1);

    public String getDropColumnSql(String var1);

    public String getCommentSql(ColumnProperty var1);

    public String getSpecialHandle(ColumnProperty var1, ColumnProperty var2);

    public String dropIndexs(String var1, String var2);

    public String countIndex(String var1, String var2);
}

