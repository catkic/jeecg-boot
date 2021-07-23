/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.common.util.jsonschema;

import java.io.Serializable;
import java.util.List;

public class JsonSchemaDescrip
implements Serializable {
    private String schema = "http://json-schema.org/draft-07/schema#";
    private String title;
    private String description;
    private String type;
    private List<String> required;

    public List<String> getRequired() {
        return this.required;
    }

    public void setRequired(List<String> required) {
        this.required = required;
    }

    public String getSchema() {
        return this.schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonSchemaDescrip() {
    }

    public JsonSchemaDescrip(List<String> required) {
        this.description = "我是一个jsonschema description";
        this.title = "我是一个jsonschema title";
        this.type = "object";
        this.required = required;
    }
}

