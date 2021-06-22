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

@Component
public class CgformEnhanceQueryDemo implements CgformEnhanceJavaListInter {
    @Override
    public void execute(String tableName, List<Map<String, Object>> data) throws BusinessException {
        List<Area> list = this.init();
        for (Map<String, Object> map : data) {
            Object object = map.get("province");
            if (object == null) continue;
            String string = list.stream().filter(area2 -> object.toString().equals(area2.code())).map(Area::name).findAny().orElse("");
            map.put("province", string);
        }
    }

    private List<Area> init() {
        ArrayList<Area> arrayList = new ArrayList<Area>();
        arrayList.add(new Area("bj", "北京"));
        arrayList.add(new Area("sd", "山东"));
        arrayList.add(new Area("ah", "安徽"));
        return arrayList;
    }

    class Area {
        String code;
        String name;

        public Area(String string, String string2) {
            this.code = string;
            this.name = string2;
        }

        public String code() {
            return this.code;
        }

        public String name() {
            return this.name;
        }
    }
}

