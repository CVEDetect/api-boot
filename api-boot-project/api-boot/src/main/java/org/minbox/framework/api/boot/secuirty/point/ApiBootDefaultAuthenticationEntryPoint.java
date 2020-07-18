package org.minbox.framework.api.boot.secuirty.point;

import com.alibaba.fastjson.JSON;
import org.minbox.framework.api.boot.common.model.ApiBootResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The {@link AuthenticationEntryPoint} implement class
 * <p>
 * The default implementation class of {@link AuthenticationEntryPoint} provided by ApiBoot
 * @author 恒宇少年
 */
public class ApiBootDefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(ApiBootDefaultAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        logger.error("Unauthorized", e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        // ApiBoot Result
        ApiBootResult result = ApiBootResult.builder().errorMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase()).errorCode(String.valueOf(HttpStatus.UNAUTHORIZED.value())).build();
        // return json
        response.getWriter().write(JSON.toJSONString(result));
    }
}
