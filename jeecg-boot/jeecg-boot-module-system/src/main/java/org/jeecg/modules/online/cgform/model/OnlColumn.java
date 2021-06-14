/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.model;

import org.jeecg.modules.online.cgform.model.c;

public class OnlColumn {
    private String title;
    private String dataIndex;
    private String align;
    private String customRender;
    private c scopedSlots;
    private String hrefSlotName;
    private int showLength;
    private boolean sorter = false;

    public OnlColumn() {
    }

    public OnlColumn(String title, String dataIndex) {
        this.align = "center";
        this.title = title;
        this.dataIndex = dataIndex;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDataIndex() {
        return this.dataIndex;
    }

    public String getAlign() {
        return this.align;
    }

    public String getCustomRender() {
        return this.customRender;
    }

    public c getScopedSlots() {
        return this.scopedSlots;
    }

    public String getHrefSlotName() {
        return this.hrefSlotName;
    }

    public int getShowLength() {
        return this.showLength;
    }

    public boolean isSorter() {
        return this.sorter;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDataIndex(String dataIndex) {
        this.dataIndex = dataIndex;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public void setCustomRender(String customRender) {
        this.customRender = customRender;
    }

    public void setScopedSlots(c scopedSlots) {
        this.scopedSlots = scopedSlots;
    }

    public void setHrefSlotName(String hrefSlotName) {
        this.hrefSlotName = hrefSlotName;
    }

    public void setShowLength(int showLength) {
        this.showLength = showLength;
    }

    public void setSorter(boolean sorter) {
        this.sorter = sorter;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OnlColumn)) {
            return false;
        }
        OnlColumn onlColumn = (OnlColumn)o;
        if (!onlColumn.canEqual(this)) {
            return false;
        }
        if (this.getShowLength() != onlColumn.getShowLength()) {
            return false;
        }
        if (this.isSorter() != onlColumn.isSorter()) {
            return false;
        }
        String string = this.getTitle();
        String string2 = onlColumn.getTitle();
        if (string == null ? string2 != null : !string.equals(string2)) {
            return false;
        }
        String string3 = this.getDataIndex();
        String string4 = onlColumn.getDataIndex();
        if (string3 == null ? string4 != null : !string3.equals(string4)) {
            return false;
        }
        String string5 = this.getAlign();
        String string6 = onlColumn.getAlign();
        if (string5 == null ? string6 != null : !string5.equals(string6)) {
            return false;
        }
        String string7 = this.getCustomRender();
        String string8 = onlColumn.getCustomRender();
        if (string7 == null ? string8 != null : !string7.equals(string8)) {
            return false;
        }
        c c2 = this.getScopedSlots();
        c c3 = onlColumn.getScopedSlots();
        if (c2 == null ? c3 != null : !((Object)c2).equals(c3)) {
            return false;
        }
        String string9 = this.getHrefSlotName();
        String string10 = onlColumn.getHrefSlotName();
        return !(string9 == null ? string10 != null : !string9.equals(string10));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OnlColumn;
    }

    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        n3 = n3 * 59 + this.getShowLength();
        n3 = n3 * 59 + (this.isSorter() ? 79 : 97);
        String string = this.getTitle();
        n3 = n3 * 59 + (string == null ? 43 : string.hashCode());
        String string2 = this.getDataIndex();
        n3 = n3 * 59 + (string2 == null ? 43 : string2.hashCode());
        String string3 = this.getAlign();
        n3 = n3 * 59 + (string3 == null ? 43 : string3.hashCode());
        String string4 = this.getCustomRender();
        n3 = n3 * 59 + (string4 == null ? 43 : string4.hashCode());
        c c2 = this.getScopedSlots();
        n3 = n3 * 59 + (c2 == null ? 43 : ((Object)c2).hashCode());
        String string5 = this.getHrefSlotName();
        n3 = n3 * 59 + (string5 == null ? 43 : string5.hashCode());
        return n3;
    }

    public String toString() {
        return "OnlColumn(title=" + this.getTitle() + ", dataIndex=" + this.getDataIndex() + ", align=" + this.getAlign() + ", customRender=" + this.getCustomRender() + ", scopedSlots=" + this.getScopedSlots() + ", hrefSlotName=" + this.getHrefSlotName() + ", showLength=" + this.getShowLength() + ", sorter=" + this.isSorter() + ")";
    }
}

