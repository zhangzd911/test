package threads;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class newConditionDemo {

    static Lock lock = new ReentrantLock();

    static Condition conditionA = lock.newCondition();
    static Condition conditionB = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {


        for (int i = 0; i< 10;i++) {
            final int j = i;
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    lock.lock();
                    System.out.println("wait start thread:" + Thread.currentThread().getName());
                    if (j % 2 == 0) {
                        conditionA.await();
                    } else {
                        conditionB.await();
                    }
                    System.out.println("starting thread:" + Thread.currentThread().getName());
                    lock.unlock();
                }
            }, "thread" + i).start();
        }

        //Thread.sleep(3000);

        lock.lock();
        System.out.println("conditionB start:" + Thread.currentThread().getName());
        conditionB.signalAll();
        lock.unlock();

        System.out.println();

    }
}
