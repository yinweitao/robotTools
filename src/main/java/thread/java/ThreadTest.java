package thread.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yinweitao on 2018/8/2.
 */
public class ThreadTest {

    private static final CountDownLatch latch = new CountDownLatch(2);//两个工人的协作

    public static void main(String[] args) throws InterruptedException {

        StringBuffer info = new StringBuffer();
        Thread t1 = new Thread() {
            public void run() {
                String name = getNmae();
                info.append(name);
                latch.countDown();
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                String age = getAgeStr();
                info.append(age);
                latch.countDown();
            }
        };
        t1.start();
        t2.start();
        latch.await();

            System.out.println("info=" + info);
    }

    public static String getNmae() {
        return "name=小马;";
    }

    public  static String getAgeStr() {
        return "age=23;";
    }
}
