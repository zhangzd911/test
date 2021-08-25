package rpc.protocol;

/*
 * @author zzd
 * 传输请求对象
 */

import rpc.rpcPackage.MyContent;
import rpc.rpcPackage.MyHeader;

public class RpcRequest {

    private String id;
    private MyHeader header;
    private MyContent content;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MyHeader getHeader() {
        return header;
    }

    public void setHeader(MyHeader header) {
        this.header = header;
    }

    public MyContent getContent() {
        return content;
    }

    public void setContent(MyContent content) {
        this.content = content;
    }
}