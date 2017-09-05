package common.util;


/**
 * @Author Froid_Li
 * @Email 269504518@qq.com
 * @Date 2017/9/5  14:58
 */
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
public class RedisUtil{



    public static boolean mPut(String redisKey,String mapKey,String value){
        return getStatefulConnection().sync().hset(redisKey,mapKey,value);
    }

    public static boolean mPut(String redisKey, String mapKey, String value, long expireSeconds){
        StatefulRedisConnection connection=getStatefulConnection();
        connection.sync().hset(redisKey,mapKey,value);
        return connection.sync().expire(redisKey,expireSeconds);
    }

    public static String  mGet(String redisKey,String mapKey){
        return getStatefulConnection().sync().hget(redisKey,mapKey);
    }

    public static boolean  mRemove(String redisKey,String mapKey){
        return getStatefulConnection().sync().hdel(redisKey,mapKey)!=0;
    }

    public static boolean  delete(String redisKey){
        return getStatefulConnection().sync().del(redisKey)!=0;
    }

    public static String get(String redisKey){
        return getStatefulConnection().sync().get(redisKey);
    }

    public static boolean put(String redisKey,String value){
        getStatefulConnection().sync().set(redisKey,value);
        return true;
    }

    public static boolean put(String redisKey,String value,long expireSeconds){
        StatefulRedisConnection connection=getStatefulConnection();
        connection.sync().set(redisKey,value);
        connection.sync().expire(redisKey,expireSeconds);
        return true;
    }

    private static StatefulRedisConnection<String,String> getStatefulConnection(){
        String redisHost=PropertyPlaceholder.getProperty("redis.host","127.0.0.1");
        String port=PropertyPlaceholder.getProperty("redis.port","6379");
        RedisURI redisUri = RedisURI.Builder.redis(redisHost).withPort(Integer.valueOf(port))
                .withDatabase(2)
                .build();
        RedisClient client = RedisClient.create(redisUri);
        return client.connect();
    }

}
