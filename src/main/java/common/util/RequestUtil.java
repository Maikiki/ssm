package common.util;


import common.util.constant.Constant;
import common.util.constant.ErrorCode;
import common.util.constant.RedisKey;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import vo.commonVo.ResultVo;
import vo.userVo.LoginSuccessVo;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Map;


/**
 * @Author Froid_Li
 * @Email 269504518@qq.com
 * @Date 2017/9/5  14:50
 */
public class RequestUtil {

    private static final SignatureAlgorithm ALG = SignatureAlgorithm.HS256;

    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            }
            return ip;
        }
        return request.getRemoteAddr();
    }

    public static ResultVo<LoginSuccessVo> getUserInfo(HttpServletRequest request) {
        String token = request.getHeader(Constant.HEADER_TOKEN_KEY);
        return getUserInfo(token);
    }

    public static ResultVo<LoginSuccessVo> getUserInfo(String token) {
        if (StringUtils.isEmpty(token)) {
            return new ResultVo<>(ErrorCode.TokenNotExists.getErrorCode());
        }
        Claims claims = parseToken(token);
        if (claims == null) {
            return new ResultVo<>(ErrorCode.TokenExpired.getErrorCode());
        }
        if (claims.getExpiration() != null && claims.getExpiration().before(new Date())) {
            return new ResultVo<>(ErrorCode.TokenExpired.getErrorCode());
        }
        String sessionId = claims.get(Constant.JWT_CLAIM_KEY, String.class);
        if (sessionId == null) {
            return new ResultVo<>(ErrorCode.TokenExpired.getErrorCode());
        }
        String userInfoJson = RedisUtil.mGet(RedisKey.TOKEN, sessionId);
        if (StringUtils.isEmpty(userInfoJson)) {
            return new ResultVo<>(ErrorCode.TokenNotExists.getErrorCode());
        }
        LoginSuccessVo successVo = JSONUtil.parse(userInfoJson, LoginSuccessVo.class);
        if (successVo == null) {
            return new ResultVo<>(ErrorCode.TokenExpired.getErrorCode());
        }
        successVo.setSessionId(sessionId);
        return new ResultVo(Constant.SUCCESS_CODE, successVo);
    }

    public static boolean hasAccess(LoginSuccessVo userInfo, String url) {
        return true;
    }

    private static Claims parseToken(String token) {
        try {
            return Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token.trim()).getBody();
        } catch (ExpiredJwtException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(Constant.JWT_SECRET.getBytes());
        SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return secretKey;
    }

    public static String generateJwtToken(Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        //构建jwt参数
        JwtBuilder jwtBuilder = Jwts.builder();
        //Payload
        jwtBuilder.setClaims(claims);
        jwtBuilder.setIssuedAt(new Date(now));
        jwtBuilder.setExpiration(new Date(now + Constant.TOKEN_EXPIRY_MINUTES * 60 * 1000L));
        jwtBuilder.signWith(ALG, generalKey());
        return jwtBuilder.compact();
    }

}
