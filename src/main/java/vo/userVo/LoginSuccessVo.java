package vo.userVo;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Froid_Li
 * @Email 269504518@qq.com
 * @Date 2017/9/5  14:56
 */
public class LoginSuccessVo {

    private BigDecimal userId;

    private Set<BigDecimal> roleIds =new HashSet<BigDecimal>();

    private String token;

    private Integer userType;

    private String sessionId="";

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public Set<BigDecimal> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<BigDecimal> roleIds) {
        this.roleIds = roleIds;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
