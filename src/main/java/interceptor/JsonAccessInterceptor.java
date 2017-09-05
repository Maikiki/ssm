package interceptor;


/**
 * @Author Froid_Li
 * @Email 269504518@qq.com
 * @Date 2017/9/5  14:37
 */
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonAccessInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","*");
        response.addHeader("Access-Control-Max-Age","3600"); // 预检请求的有效期，单位为秒。有效期内，不会重复发送预检请求
        response.addHeader("Access-Control-Allow-Headers", "Authorization,x-requested-with,content-type");
        response.addHeader("Access-Control-Allow-Credentials","true");
        if(request.getMethod().equalsIgnoreCase("OPTIONS")){
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
