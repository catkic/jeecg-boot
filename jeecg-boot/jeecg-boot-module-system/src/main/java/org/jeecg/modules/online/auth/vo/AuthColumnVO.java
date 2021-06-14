/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.auth.vo;

import java.io.Serializable;

import lombok.*;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthColumnVO implements Serializable {
    private static final long serialVersionUID = 5445993027926933917L;
    private String id;
    private String cgformId;
    private Integer type = 1;
    private String code;
    private String title;
    private Integer status;
    private boolean listShow;
    private boolean formShow;
    private boolean formEditable;
    private Integer isShowForm;
    private Integer isShowList;
    private String tableName;
    private String tableNameTxt;
    private int switchFlag;

    public AuthColumnVO(OnlCgformField field) {
        this.id = field.getId();
        this.cgformId = field.getCgformHeadId();
        this.code = field.getDbFieldName();
        this.title = field.getDbFieldTxt();
        this.type = 1;
        this.isShowForm = field.getIsShowForm();
        this.isShowList = field.getIsShowList();
    }

}

