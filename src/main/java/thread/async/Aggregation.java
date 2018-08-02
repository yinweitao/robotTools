package thread.async;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yinweitao on 2018/8/2.
 */
public class Aggregation {

    private static final CountDownLatch latch = new CountDownLatch(2);//两个工人的协作

    @Async
    public static String getNmae() {
        return "name=小马;";
    }

    @Async
    public static String getAgeStr() {
        return "age=23;";
    }

    public static void main(String[] args) throws InterruptedException {
        StringBuffer info = new StringBuffer();
        String name = getNmae();
        info.append(name);
      //  latch.countDown();

        String age = getAgeStr();
        info.append(age);
      //  latch.countDown();

       // latch.await();
        System.out.println("info=" + info);
    }

}
