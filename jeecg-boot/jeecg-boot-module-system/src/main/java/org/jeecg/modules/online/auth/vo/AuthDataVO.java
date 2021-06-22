/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.auth.vo;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AuthDataVO
implements Serializable {
    private static final long serialVersionUID = 1057819436991228603L;
    private String id;
    private String title;
    private String relId;
    private Boolean checked;

}

