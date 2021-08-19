package zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public  class ZkUtil {


    private static final String ZK_ADDRESS = "127.0.0.1:2181";



    private ZooKeeper zk;

    public static ZooKeeper getZkClient(String node){
        try {
            final CountDownLatch cd = new CountDownLatch(1);
            ZooKeeper zk = new ZooKeeper(ZK_ADDRESS + node, 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                    //System.out.println("回调：" + event.toString());
                    Event.KeeperState state = event.getState();
                    switch (state) {
                        case Unknown:
                            break;
                        case Disconnected:
                            break;
                        case NoSyncConnected:
                            break;
                        case SyncConnected:
                            //System.out.println("zk connect SyncConnected");
                            cd.countDown();
                            break;
                        case AuthFailed:
                            break;
                        case ConnectedReadOnly:
                            break;
                        case SaslAuthenticated:
                            break;
                        case Expired:
                            break;
                    }

                }
            });

            try {
                //System.out.println("zk connect wait");
                cd.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ZooKeeper.States state = zk.getState();
            switch (state) {
                case CONNECTING:
                    //System.out.println("zk ing");
                    break;
                case ASSOCIATING:
                    break;
                case CONNECTED:
                    //System.out.println("zk ed");

                    break;
                case CONNECTEDREADONLY:
                    break;
                case CLOSED:
                    break;
                case AUTH_FAILED:
                    break;
                case NOT_CONNECTED:
                    break;
            }



            return zk;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void close(){
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
