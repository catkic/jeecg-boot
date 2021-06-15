/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.jeecg.common.api.dto.OnlineAuthDTO
 *  org.jeecg.common.api.vo.Result
 *  org.jeecg.common.aspect.annotation.OnlineAuth
 *  org.jeecg.common.system.api.ISysBaseAPI
 *  org.jeecg.common.system.util.JwtUtil
 *  org.jeecg.common.util.SpringContextUtils
 *  org.jeecg.common.util.oConvertUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.web.method.HandlerMethod
 *  org.springframework.web.servlet.HandlerInterceptor
 */
package org.jeecg.interceptor;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.dto.OnlineAuthDTO;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.OnlineAuth;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.service.IOnlineBaseAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class a
implements HandlerInterceptor {
    private static final Logger a = LoggerFactory.getLogger(a.class);
    private IOnlineBaseAPI b;
    private ISysBaseAPI c;
    private static final String d = "/online/cgform";
    private static final String[] e = new String[]{"/online/cgformInnerTableList", "/online/cgformErpList", "/online/cgformList", "/online/cgformTreeList", "/online/cgformTabList"};

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        OnlineAuth onlineAuth;
        boolean bl = handler.getClass().isAssignableFrom(HandlerMethod.class);
        if (bl && (onlineAuth = (OnlineAuth)((HandlerMethod)handler).getMethodAnnotation(OnlineAuth.class)) != null) {
            OnlineAuthDTO onlineAuthDTO;
            int n2;
            a.debug("===== online 菜单访问拦截器 =====");
            String string = request.getRequestURI().substring(request.getContextPath().length());
            string = this.a(string);
            String string2 = onlineAuth.value();
            String string3 = string.substring(string.lastIndexOf(string2) + string2.length());
            a.debug("拦截请求(" + request.getMethod() + ")：" + string + ",");
            if ("form".equals(string2) && "DELETE".equals(request.getMethod())) {
                string3 = string3.substring(0, string3.lastIndexOf("/"));
            }
            String string4 = request.getParameter("tabletype");
            if (this.b == null) {
                this.b = (IOnlineBaseAPI)SpringContextUtils.getBean(IOnlineBaseAPI.class);
            }
            string3 = this.b.getOnlineErpCode(string3, string4);
            ArrayList<String> arrayList = new ArrayList<String>();
            String[] object = e;
            int n3 = ((String[])object).length;
            for (n2 = 0; n2 < n3; ++n2) {
                String string5 = object[n2];
                arrayList.add(string5 + string3);
            }
            if (this.c == null) {
                this.c = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);
            }
            if ((n2 = (int)(this.c.hasOnlineAuth(onlineAuthDTO = new OnlineAuthDTO(JwtUtil.getUserNameByToken(request), arrayList, d)) ? 1 : 0)) == 0) {
                a.info("请求无权限(" + request.getMethod() + ")：" + string);
                this.a(response, string2);
                return false;
            }
        }
        return true;
    }

    private String a(String string) {
        String string2 = "";
        if (oConvertUtils.isNotEmpty((Object)string)) {
            string2 = string.replace("\\", "/");
            if ((string2 = string2.replace("//", "/")).indexOf("//") >= 0) {
                string2 = this.a(string2);
            }
        }
        return string2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(HttpServletResponse httpServletResponse, String string) {
        PrintWriter printWriter = null;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setHeader("auth", "fail");
        try {
            printWriter = httpServletResponse.getWriter();
            if ("exportXls".equals(string)) {
                printWriter.print("");
            } else {
                Result result = Result.error((String)"无权限访问(操作)");
                printWriter.print(JSON.toJSON((Object)result));
            }
        }
        catch (IOException iOException) {
            a.error(iOException.getMessage());
        }
        finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}

