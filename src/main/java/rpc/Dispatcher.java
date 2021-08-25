package rpc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册中心
 */
public class Dispatcher {


    private static Dispatcher dispatcher;



    static {
        dispatcher = new Dispatcher();

    }

    private Dispatcher() {
    }

    public static Dispatcher getDispatcher(){
        return dispatcher;
    }

    public  static ConcurrentHashMap<String,Object> invokeMap = new ConcurrentHashMap<>();

    public void register(String k,Object obj){
        invokeMap.put(k,obj);
    }
    public Object get(String k){
        return invokeMap.get(k);
    }
}
