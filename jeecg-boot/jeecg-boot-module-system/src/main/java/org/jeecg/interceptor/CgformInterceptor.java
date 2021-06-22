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

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.dto.OnlineAuthDTO;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.OnlineAuth;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.service.IOnlineBaseAPI;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class CgformInterceptor
implements HandlerInterceptor {
    private IOnlineBaseAPI onlineBaseAPI;
    private ISysBaseAPI sysBaseAPI;
    private static final String BASE_PATH = "/online/cgform";
    private static final String[] CHECK_PATH = new String[]{"/online/cgformInnerTableList", "/online/cgformErpList", "/online/cgformList", "/online/cgformTreeList", "/online/cgformTabList"};

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        OnlineAuth onlineAuth;
        boolean bl = handler.getClass().isAssignableFrom(HandlerMethod.class);
        if (bl && (onlineAuth = ((HandlerMethod)handler).getMethodAnnotation(OnlineAuth.class)) != null) {
            OnlineAuthDTO onlineAuthDTO;
            int n2;
            log.debug("===== online 菜单访问拦截器 =====");
            String string = request.getRequestURI().substring(request.getContextPath().length());
            string = this.a(string);
            String string2 = onlineAuth.value();
            String string3 = string.substring(string.lastIndexOf(string2) + string2.length());
            log.debug("拦截请求(" + request.getMethod() + ")：" + string + ",");
            if ("form".equals(string2) && "DELETE".equals(request.getMethod())) {
                string3 = string3.substring(0, string3.lastIndexOf("/"));
            }
            String tabletype = request.getParameter("tabletype");
            if (this.onlineBaseAPI == null) {
                this.onlineBaseAPI = SpringContextUtils.getBean(IOnlineBaseAPI.class);
            }
            string3 = this.onlineBaseAPI.getOnlineErpCode(string3, tabletype);
            ArrayList<String> arrayList = new ArrayList<>();
            String[] object = CHECK_PATH;
            int n3 = object.length;
            for (int i = 0; i < n3; ++i) {
                String string5 = object[i];
                arrayList.add(string5 + string3);
            }
            if (this.sysBaseAPI == null) {
                this.sysBaseAPI = SpringContextUtils.getBean(ISysBaseAPI.class);
            }
            if (this.sysBaseAPI.hasOnlineAuth(new OnlineAuthDTO(JwtUtil.getUserNameByToken(request), arrayList, BASE_PATH))) {
                log.info("请求无权限(" + request.getMethod() + ")：" + string);
                this.a(response, string2);
                return false;
            }
        }
        return true;
    }

    private String a(String string) {
        String string2 = "";
        if (oConvertUtils.isNotEmpty(string)) {
            string2 = string.replace("\\", "/");
            if ((string2 = string2.replace("//", "/")).contains("//")) {
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
                Result result = Result.error("无权限访问(操作)");
                printWriter.print(JSON.toJSON(result));
            }
        }
        catch (IOException iOException) {
            log.error(iOException.getMessage());
        }
        finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}

