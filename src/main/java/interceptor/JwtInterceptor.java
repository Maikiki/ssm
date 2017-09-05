package interceptor;


import common.util.JSONUtil;
import common.util.RequestUtil;
import common.util.constant.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import vo.commonVo.ResultVo;
import vo.userVo.LoginSuccessVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * @Author Froid_Li
 * @Email 269504518@qq.com
 * @Date 2017/9/5  14:38
 */
public class JwtInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    //action 执行之前验证token
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getServletPath();
        uri = uri.endsWith("/") ? uri.substring(0, uri.length() - 1) : uri;
        try {
            ResultVo<LoginSuccessVo> result = RequestUtil.getUserInfo(request);
            if (result.getErrorCode() != 0) {
                PrintWriter printWriter = response.getWriter();
                printWriter.write(JSONUtil.toJsonString(result));
                return false;
            }
            LoginSuccessVo userInfo = result.getData();
            if (!RequestUtil.hasAccess(userInfo, uri)) {
                PrintWriter printWriter = response.getWriter();
                printWriter.write(JSONUtil.toJsonString(new ResultVo<>(HttpStatus.FORBIDDEN.value())));
                return false;
            }
            return true;
        } catch (Exception e) {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(JSONUtil.toJsonString(new ResultVo<>(ErrorCode.UnknownError.getErrorCode())));
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //action执行之后,生产视图之前
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //最后执行
    }

}