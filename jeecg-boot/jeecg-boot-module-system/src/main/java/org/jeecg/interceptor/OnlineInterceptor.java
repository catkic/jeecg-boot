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
public class OnlineInterceptor implements HandlerInterceptor {
    private IOnlineBaseAPI onlineBaseAPI;
    private ISysBaseAPI iSysBaseAPI;
    private static final String d = "/online/cgform";
    private static final String[] FILTER_URLS = new String[]{"/online/cgformInnerTableList", "/online/cgformErpList", "/online/cgformList", "/online/cgformTreeList", "/online/cgformTabList"};

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        OnlineAuth onlineAuth;
        boolean bl = handler.getClass().isAssignableFrom(HandlerMethod.class);
        if (bl && (onlineAuth = ((HandlerMethod)handler).getMethodAnnotation(OnlineAuth.class)) != null) {
            OnlineAuthDTO onlineAuthDTO;
            log.debug("===== online 菜单访问拦截器 =====");
            String contextPath = this.convertUrl(request.getRequestURI().substring(request.getContextPath().length()));
            String auth = onlineAuth.value();
            String authString = contextPath.substring(contextPath.lastIndexOf(auth) + auth.length());
            log.debug("拦截请求(" + request.getMethod() + ")：" + contextPath + ",");
            if ("form".equals(auth) && "DELETE".equals(request.getMethod())) {
                authString = authString.substring(0, authString.lastIndexOf("/"));
            }
            String tabletype = request.getParameter("tabletype");
            if (this.onlineBaseAPI == null) {
                this.onlineBaseAPI = SpringContextUtils.getBean(IOnlineBaseAPI.class);
            }
            authString = this.onlineBaseAPI.getOnlineErpCode(authString, tabletype);
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String url : FILTER_URLS) {
                arrayList.add(url + authString);
            }
            if (this.iSysBaseAPI == null) {
                this.iSysBaseAPI = SpringContextUtils.getBean(ISysBaseAPI.class);
            }
            if (!this.iSysBaseAPI.hasOnlineAuth(new OnlineAuthDTO(JwtUtil.getUserNameByToken(request), arrayList, d))) {
                log.info("请求无权限(" + request.getMethod() + ")：" + contextPath);
                this.a(response, auth);
                return false;
            }
        }
        return true;
    }

    private String convertUrl(String url) {
        if (oConvertUtils.isNotEmpty(url)) {
            String string2 = url.replace("\\", "/").replace("//", "/");
            if (string2.contains("//")) {
                return this.convertUrl(string2);
            }
        }
        return "";
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
            log.error(iOException.getMessage());
        }
        finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}

