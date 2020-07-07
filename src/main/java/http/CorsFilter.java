package http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/** 跨域
 * @author: ywt
 * @date: 2020/5/18 09:32
 */
@WebFilter(urlPatterns = {"/*","/**"}, filterName = "CorsFilter")
public class CorsFilter implements Filter {

    @Value("${spring.profiles.active}")
    private String profiles;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String origin = httpServletRequest.getHeader("Origin");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        //允许csdn.net下的子域名跨域 || 非开发环境跨域全部通过
        if ((!StringUtils.isEmpty(origin) && origin.endsWith("csdn.net")) || !"prod".equalsIgnoreCase(profiles)) {

            Enumeration<String> headers = httpServletRequest.getHeaderNames();
            StringBuilder headersStr = new StringBuilder();
            headersStr.append("content-type,");
            while (headers.hasMoreElements()) {
                headersStr.append(headers.nextElement()).append(",");
            }
            String corsAllowHeaders = headersStr.toString().substring(0, headersStr.length() - 1);

            httpServletResponse.setHeader("Access-Control-Allow-Origin", origin);
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", corsAllowHeaders);
            httpServletResponse.setHeader("Access-Control-Max-Age", "86400");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

            if (HttpMethod.OPTIONS.toString().equalsIgnoreCase(httpServletRequest.getMethod())) {
                httpServletResponse.setStatus(HttpStatus.OK.value());
                return;
            }

        }

        chain.doFilter(request, response);
    }

}