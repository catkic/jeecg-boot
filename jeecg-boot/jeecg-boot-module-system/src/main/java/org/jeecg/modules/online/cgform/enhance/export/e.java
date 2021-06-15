/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Component
 */
package org.jeecg.modules.online.cgform.enhance.export;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaListInter;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component(value="cgformEnhanceQueryDemo")
public class e
implements CgformEnhanceJavaListInter {
    @Override
    public void execute(String tableName, List<Map<String, Object>> data) throws BusinessException {
        List<a> list = this.a();
        for (Map<String, Object> map : data) {
            Object object = map.get("province");
            if (object == null) continue;
            String string = list.stream().filter(a2 -> object.toString().equals(a2.a())).map(a::b).findAny().orElse("");
            map.put("province", string);
        }
    }

    private List<a> a() {
        ArrayList<a> arrayList = new ArrayList<a>();
        arrayList.add(new a("bj", "北京"));
        arrayList.add(new a("sd", "山东"));
        arrayList.add(new a("ah", "安徽"));
        return arrayList;
    }

    class a {
        String a;
        String b;

        public a(String string, String string2) {
            this.a = string;
            this.b = string2;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }
    }
}

