package zookeeper;

import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public  class ZkLock implements AsyncCallback.StringCallback,Watcher, AsyncCallback.DataCallback {

    private ZooKeeper zk;
    private String threadName;
    private String nodeName;

    private CountDownLatch cd = new CountDownLatch(1);

    public void lock(){

        zk.create("/lock", "111".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, "abc");
        try {
            cd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unLock(){

        try {
            zk.delete( nodeName, -1);
            System.out.println(threadName + " delete node" + nodeName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }



    @SneakyThrows
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {

        if (name != null) {
            System.out.println(threadName + " create node name:" + name);
            this.nodeName = name;
            List<String> children = zk.getChildren("/", this);
            watchChild(children, name);
        }
    }

    private void watchChild(List<String> children, String name) {
        Collections.sort(children);
        int index = children.indexOf(name.substring(1));
        if (index == 0) {
            System.out.println(threadName + " 抢到锁 node:" + nodeName);
            cd.countDown();
        } else {
            zk.getData("/" + children.get(index - 1), this, this, "ac");
        }

    }


    @SneakyThrows
    @Override
    public void process(WatchedEvent event) {
        Event.EventType type = event.getType();
        switch (type) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                List<String> children = zk.getChildren("/", this);

                watchChild(children, nodeName);
                break;
            case NodeDataChanged:
                break;
            case NodeChildrenChanged:
                break;
        }
    }




    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }


    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {

    }
}
