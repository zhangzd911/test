package localCache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaDemo {

    private static LoadingCache<String, String> corpCache = CacheBuilder.newBuilder().maximumSize(100000)
            .expireAfterWrite(1, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
                @Override
                public String load(String id) throws Exception {
                    return null;
                }
            });

    public static void main(String[] args) {


        corpCache.put("1", "1");
    }
}
