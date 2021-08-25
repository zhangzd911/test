package rpc.rpcPackage;

import java.io.Serializable;

public class MyHeader  implements Serializable {

    int flag;  //32bit可以设置很多信息。。。
    long requestID;
    long dataLen;


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public long getDataLen() {
        return dataLen;
    }

    public void setDataLen(long dataLen) {
        this.dataLen = dataLen;
    }
}
