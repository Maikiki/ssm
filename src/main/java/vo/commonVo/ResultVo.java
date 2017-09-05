package vo.commonVo;


/**
 * @Author Froid_Li
 * @Email 269504518@qq.com
 * @Date 2017/9/5  14:45
 */
public class ResultVo<T> {

    private Integer errorCode=0;

    private T data = null;

    public ResultVo() {
    }

    public ResultVo(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public ResultVo(Integer errorCode, T t) {
        this.errorCode = errorCode;
        this.data = t;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
