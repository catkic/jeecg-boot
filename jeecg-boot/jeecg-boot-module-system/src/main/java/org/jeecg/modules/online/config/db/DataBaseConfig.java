/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  org.jeecg.common.util.oConvertUtils
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.boot.context.properties.ConfigurationProperties
 *  org.springframework.stereotype.Component
 */
package org.jeecg.modules.online.config.db;

import org.jeecg.common.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.master")
public class DataBaseConfig {
    
    @Autowired
    private DmDataBaseConfig dmDataBaseConfig;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private Druid druid;

    public Druid getDruid() {
        if (this.druid == null) {
            return this.dmDataBaseConfig.getDruid();
        }
        return this.druid;
    }

    public void setDruid(Druid druid) {
        this.druid = druid;
    }

    public String getUrl() {
        return oConvertUtils.getString(this.url, this.dmDataBaseConfig.getUrl());
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return oConvertUtils.getString(this.username, this.dmDataBaseConfig.getUsername());
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return oConvertUtils.getString(this.password, this.dmDataBaseConfig.getPassword());
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return oConvertUtils.getString(this.driverClassName, this.dmDataBaseConfig.getDriverClassName());
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}

