/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.auth.vo;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AuthPageVO implements Serializable {
    private static final long serialVersionUID = 724713901683956568L;
    private String id;
    private String code;
    private String title;
    private Integer page;
    private Integer control;
    private String relId;
    private Boolean checked;

}

