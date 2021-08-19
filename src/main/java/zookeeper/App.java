package zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * zookeeper实现分布式锁
 * 1、创建zk连接
 * 2、尝试获取锁
 *  i、在某个借点下创建一个 EPHEMERAL_SEQUENTIAL类型的子节点
 *  ii、
 */
public class App {


    public static void main(String[] args) throws IOException {


        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ZkLock zkLock = new ZkLock();
                    zkLock.setZk(ZkUtil.getZkClient("/TestLock"));
                    zkLock.setThreadName(Thread.currentThread().getName());


                    zkLock.lock();

                    System.out.println(Thread.currentThread().getName() + " working" + "node: " + zkLock.getNodeName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    zkLock.unLock();



                }
            }).start();
        }


        System.in.read();
    }
}
