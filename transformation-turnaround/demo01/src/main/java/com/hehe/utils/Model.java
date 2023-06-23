package com.hehe.utils;

import java.io.Serializable;

/**
 * @ClassName Model
 **/
public class Model implements Serializable {

    /**
     * 模型名称
     */
    private String modelName;
    /**
     * 属性
     */
    private String fields;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }
}
