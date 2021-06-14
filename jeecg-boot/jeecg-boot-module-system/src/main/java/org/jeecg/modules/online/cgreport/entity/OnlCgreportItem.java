package org.jeecg.modules.online.cgreport.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@TableName("onl_cgreport_item")
public class OnlCgreportItem implements Serializable {
   private static final long serialVersionUID = 1L;
   @TableId(
      type = IdType.ASSIGN_ID
   )
   private String id;
   private String cgrheadId;
   private String fieldName;
   private String fieldTxt;
   private Integer fieldWidth;
   private String fieldType;
   private String searchMode;
   private Integer isOrder;
   private Integer isSearch;
   private String dictCode;
   private String fieldHref;
   private Integer isShow;
   private Integer orderNum;
   private String replaceVal;
   private String isTotal;
   private String createBy;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @DateTimeFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   private Date createTime;
   private String updateBy;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @DateTimeFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   private Date updateTime;
   private String groupTitle;

   public OnlCgreportItem() {
   }

   public String getId() {
      return this.id;
   }

   public String getCgrheadId() {
      return this.cgrheadId;
   }

   public String getFieldName() {
      return this.fieldName;
   }

   public String getFieldTxt() {
      return this.fieldTxt;
   }

   public Integer getFieldWidth() {
      return this.fieldWidth;
   }

   public String getFieldType() {
      return this.fieldType;
   }

   public String getSearchMode() {
      return this.searchMode;
   }

   public Integer getIsOrder() {
      return this.isOrder;
   }

   public Integer getIsSearch() {
      return this.isSearch;
   }

   public String getDictCode() {
      return this.dictCode;
   }

   public String getFieldHref() {
      return this.fieldHref;
   }

   public Integer getIsShow() {
      return this.isShow;
   }

   public Integer getOrderNum() {
      return this.orderNum;
   }

   public String getReplaceVal() {
      return this.replaceVal;
   }

   public String getIsTotal() {
      return this.isTotal;
   }

   public String getCreateBy() {
      return this.createBy;
   }

   public Date getCreateTime() {
      return this.createTime;
   }

   public String getUpdateBy() {
      return this.updateBy;
   }

   public Date getUpdateTime() {
      return this.updateTime;
   }

   public String getGroupTitle() {
      return this.groupTitle;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setCgrheadId(String cgrheadId) {
      this.cgrheadId = cgrheadId;
   }

   public void setFieldName(String fieldName) {
      this.fieldName = fieldName;
   }

   public void setFieldTxt(String fieldTxt) {
      this.fieldTxt = fieldTxt;
   }

   public void setFieldWidth(Integer fieldWidth) {
      this.fieldWidth = fieldWidth;
   }

   public void setFieldType(String fieldType) {
      this.fieldType = fieldType;
   }

   public void setSearchMode(String searchMode) {
      this.searchMode = searchMode;
   }

   public void setIsOrder(Integer isOrder) {
      this.isOrder = isOrder;
   }

   public void setIsSearch(Integer isSearch) {
      this.isSearch = isSearch;
   }

   public void setDictCode(String dictCode) {
      this.dictCode = dictCode;
   }

   public void setFieldHref(String fieldHref) {
      this.fieldHref = fieldHref;
   }

   public void setIsShow(Integer isShow) {
      this.isShow = isShow;
   }

   public void setOrderNum(Integer orderNum) {
      this.orderNum = orderNum;
   }

   public void setReplaceVal(String replaceVal) {
      this.replaceVal = replaceVal;
   }

   public void setIsTotal(String isTotal) {
      this.isTotal = isTotal;
   }

   public void setCreateBy(String createBy) {
      this.createBy = createBy;
   }

   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   public void setCreateTime(Date createTime) {
      this.createTime = createTime;
   }

   public void setUpdateBy(String updateBy) {
      this.updateBy = updateBy;
   }

   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   public void setUpdateTime(Date updateTime) {
      this.updateTime = updateTime;
   }

   public void setGroupTitle(String groupTitle) {
      this.groupTitle = groupTitle;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof OnlCgreportItem)) {
         return false;
      } else {
         OnlCgreportItem var2 = (OnlCgreportItem)o;
         if (!var2.canEqual(this)) {
            return false;
         } else {
            label251: {
               Integer var3 = this.getFieldWidth();
               Integer var4 = var2.getFieldWidth();
               if (var3 == null) {
                  if (var4 == null) {
                     break label251;
                  }
               } else if (var3.equals(var4)) {
                  break label251;
               }

               return false;
            }

            Integer var5 = this.getIsOrder();
            Integer var6 = var2.getIsOrder();
            if (var5 == null) {
               if (var6 != null) {
                  return false;
               }
            } else if (!var5.equals(var6)) {
               return false;
            }

            Integer var7 = this.getIsSearch();
            Integer var8 = var2.getIsSearch();
            if (var7 == null) {
               if (var8 != null) {
                  return false;
               }
            } else if (!var7.equals(var8)) {
               return false;
            }

            label230: {
               Integer var9 = this.getIsShow();
               Integer var10 = var2.getIsShow();
               if (var9 == null) {
                  if (var10 == null) {
                     break label230;
                  }
               } else if (var9.equals(var10)) {
                  break label230;
               }

               return false;
            }

            label223: {
               Integer var11 = this.getOrderNum();
               Integer var12 = var2.getOrderNum();
               if (var11 == null) {
                  if (var12 == null) {
                     break label223;
                  }
               } else if (var11.equals(var12)) {
                  break label223;
               }

               return false;
            }

            label216: {
               String var13 = this.getId();
               String var14 = var2.getId();
               if (var13 == null) {
                  if (var14 == null) {
                     break label216;
                  }
               } else if (var13.equals(var14)) {
                  break label216;
               }

               return false;
            }

            String var15 = this.getCgrheadId();
            String var16 = var2.getCgrheadId();
            if (var15 == null) {
               if (var16 != null) {
                  return false;
               }
            } else if (!var15.equals(var16)) {
               return false;
            }

            label202: {
               String var17 = this.getFieldName();
               String var18 = var2.getFieldName();
               if (var17 == null) {
                  if (var18 == null) {
                     break label202;
                  }
               } else if (var17.equals(var18)) {
                  break label202;
               }

               return false;
            }

            String var19 = this.getFieldTxt();
            String var20 = var2.getFieldTxt();
            if (var19 == null) {
               if (var20 != null) {
                  return false;
               }
            } else if (!var19.equals(var20)) {
               return false;
            }

            label188: {
               String var21 = this.getFieldType();
               String var22 = var2.getFieldType();
               if (var21 == null) {
                  if (var22 == null) {
                     break label188;
                  }
               } else if (var21.equals(var22)) {
                  break label188;
               }

               return false;
            }

            String var23 = this.getSearchMode();
            String var24 = var2.getSearchMode();
            if (var23 == null) {
               if (var24 != null) {
                  return false;
               }
            } else if (!var23.equals(var24)) {
               return false;
            }

            String var25 = this.getDictCode();
            String var26 = var2.getDictCode();
            if (var25 == null) {
               if (var26 != null) {
                  return false;
               }
            } else if (!var25.equals(var26)) {
               return false;
            }

            label167: {
               String var27 = this.getFieldHref();
               String var28 = var2.getFieldHref();
               if (var27 == null) {
                  if (var28 == null) {
                     break label167;
                  }
               } else if (var27.equals(var28)) {
                  break label167;
               }

               return false;
            }

            label160: {
               String var29 = this.getReplaceVal();
               String var30 = var2.getReplaceVal();
               if (var29 == null) {
                  if (var30 == null) {
                     break label160;
                  }
               } else if (var29.equals(var30)) {
                  break label160;
               }

               return false;
            }

            String var31 = this.getIsTotal();
            String var32 = var2.getIsTotal();
            if (var31 == null) {
               if (var32 != null) {
                  return false;
               }
            } else if (!var31.equals(var32)) {
               return false;
            }

            String var33 = this.getCreateBy();
            String var34 = var2.getCreateBy();
            if (var33 == null) {
               if (var34 != null) {
                  return false;
               }
            } else if (!var33.equals(var34)) {
               return false;
            }

            label139: {
               Date var35 = this.getCreateTime();
               Date var36 = var2.getCreateTime();
               if (var35 == null) {
                  if (var36 == null) {
                     break label139;
                  }
               } else if (var35.equals(var36)) {
                  break label139;
               }

               return false;
            }

            String var37 = this.getUpdateBy();
            String var38 = var2.getUpdateBy();
            if (var37 == null) {
               if (var38 != null) {
                  return false;
               }
            } else if (!var37.equals(var38)) {
               return false;
            }

            Date var39 = this.getUpdateTime();
            Date var40 = var2.getUpdateTime();
            if (var39 == null) {
               if (var40 != null) {
                  return false;
               }
            } else if (!var39.equals(var40)) {
               return false;
            }

            String var41 = this.getGroupTitle();
            String var42 = var2.getGroupTitle();
            if (var41 == null) {
               if (var42 != null) {
                  return false;
               }
            } else if (!var41.equals(var42)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof OnlCgreportItem;
   }

   public int hashCode() {
      boolean var1 = true;
      byte var2 = 1;
      Integer var3 = this.getFieldWidth();
      int var23 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      Integer var4 = this.getIsOrder();
      var23 = var23 * 59 + (var4 == null ? 43 : var4.hashCode());
      Integer var5 = this.getIsSearch();
      var23 = var23 * 59 + (var5 == null ? 43 : var5.hashCode());
      Integer var6 = this.getIsShow();
      var23 = var23 * 59 + (var6 == null ? 43 : var6.hashCode());
      Integer var7 = this.getOrderNum();
      var23 = var23 * 59 + (var7 == null ? 43 : var7.hashCode());
      String var8 = this.getId();
      var23 = var23 * 59 + (var8 == null ? 43 : var8.hashCode());
      String var9 = this.getCgrheadId();
      var23 = var23 * 59 + (var9 == null ? 43 : var9.hashCode());
      String var10 = this.getFieldName();
      var23 = var23 * 59 + (var10 == null ? 43 : var10.hashCode());
      String var11 = this.getFieldTxt();
      var23 = var23 * 59 + (var11 == null ? 43 : var11.hashCode());
      String var12 = this.getFieldType();
      var23 = var23 * 59 + (var12 == null ? 43 : var12.hashCode());
      String var13 = this.getSearchMode();
      var23 = var23 * 59 + (var13 == null ? 43 : var13.hashCode());
      String var14 = this.getDictCode();
      var23 = var23 * 59 + (var14 == null ? 43 : var14.hashCode());
      String var15 = this.getFieldHref();
      var23 = var23 * 59 + (var15 == null ? 43 : var15.hashCode());
      String var16 = this.getReplaceVal();
      var23 = var23 * 59 + (var16 == null ? 43 : var16.hashCode());
      String var17 = this.getIsTotal();
      var23 = var23 * 59 + (var17 == null ? 43 : var17.hashCode());
      String var18 = this.getCreateBy();
      var23 = var23 * 59 + (var18 == null ? 43 : var18.hashCode());
      Date var19 = this.getCreateTime();
      var23 = var23 * 59 + (var19 == null ? 43 : var19.hashCode());
      String var20 = this.getUpdateBy();
      var23 = var23 * 59 + (var20 == null ? 43 : var20.hashCode());
      Date var21 = this.getUpdateTime();
      var23 = var23 * 59 + (var21 == null ? 43 : var21.hashCode());
      String var22 = this.getGroupTitle();
      var23 = var23 * 59 + (var22 == null ? 43 : var22.hashCode());
      return var23;
   }

   public String toString() {
      return "OnlCgreportItem(id=" + this.getId() + ", cgrheadId=" + this.getCgrheadId() + ", fieldName=" + this.getFieldName() + ", fieldTxt=" + this.getFieldTxt() + ", fieldWidth=" + this.getFieldWidth() + ", fieldType=" + this.getFieldType() + ", searchMode=" + this.getSearchMode() + ", isOrder=" + this.getIsOrder() + ", isSearch=" + this.getIsSearch() + ", dictCode=" + this.getDictCode() + ", fieldHref=" + this.getFieldHref() + ", isShow=" + this.getIsShow() + ", orderNum=" + this.getOrderNum() + ", replaceVal=" + this.getReplaceVal() + ", isTotal=" + this.getIsTotal() + ", createBy=" + this.getCreateBy() + ", createTime=" + this.getCreateTime() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ", groupTitle=" + this.getGroupTitle() + ")";
   }
}
