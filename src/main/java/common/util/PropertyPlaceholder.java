package common.util;


/**
 * @Author Froid_Li
 * @Email 269504518@qq.com
 * @Date 2017/9/5  15:07
 */
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.*;

public class PropertyPlaceholder extends PropertyPlaceholderConfigurer {

    private static Map<String,String> propertyMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        propertyMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertyMap.put(keyStr, value);
        }
    }

    public static String getProperty(String name) {
        return propertyMap.get(name);
    }

    public static String getProperty(String name,String defaultValue) {
        String value= propertyMap.get(name);
        if(value == null){
            return defaultValue;
        }
        return value;
    }

    public static int  getIntProperty(String name,int defaultValue){
        String value= propertyMap.get(name);
        if(value == null || StringUtils.isNumeric(value)){
            return defaultValue;
        }
        return Integer.valueOf(value);
    }

    public static boolean  getBooleanProperty(String name,boolean defaultValue){
        String value= propertyMap.get(name);
        List<String> list=new ArrayList<>();
        list.add("true");
        list.add("false");
        if(value == null || !list.contains(value.toLowerCase())){
            return defaultValue;
        }
        return Boolean.valueOf(value);
    }
}
