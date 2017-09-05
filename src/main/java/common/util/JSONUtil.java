package common.util;


import com.alibaba.fastjson.JSON;

/**
 * @Author Froid_Li
 * @Email 269504518@qq.com
 * @Date 2017/9/5  14:48
 */
public class JSONUtil {

    public static String toJsonString (Object obj){
        return JSON.toJSONString(obj);
    }

    public static <T> T parse(String text,Class<T> clazz){
        return JSON.parseObject(text,clazz);
    }

}
