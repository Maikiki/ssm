package common.util.constant;


/**
 * @Author Froid_Li
 * @Email 269504518@qq.com
 * @Date 2017/9/5  14:56
 */
public enum ErrorCode {
    NotExists(1000),
    Existed(1001),
    Invalid(1002),
    Expired(1003),
    IpLimited(1004),
    PhoneLimited(1005),
    AccountInvalid(1006),
    PasswordError(1007),
    TokenNotExists(1100),
    TokenExpired(1101),
    ParamError(9998),
    UnknownError(9999);

    private int errorCode = 0;

    ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

