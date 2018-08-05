package thread.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * @author yinweitao
 * @classname ThreadTest
 * @description
 * @date 2018/8/4/17:57
 **/

@RestController("thread")
public class ThreadTest {

    @Autowired
    MyThread myThread;
    private static final CountDownLatch latch = new CountDownLatch(2);//两个工人的协作

    @RequestMapping(value = "result",method = RequestMethod.GET)
    public String Result(){
        Executor executor =  myThread.myExecutor();

        StringBuffer info = new StringBuffer();
        Runnable syncRunnable = () -> {
            info.append(getNmae());
            latch.countDown();
        };
        Runnable syncRunnable2 = () -> {
            info.append(getAgeStr());
            latch.countDown();
        };
        Runnable syncRunnable3 = () -> {
            info.append(getClassstr());
            latch.countDown();
        };

        executor.execute(syncRunnable);
        executor.execute(syncRunnable2);
        executor.execute(syncRunnable3);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return info.toString();
    }

    public static String getNmae() {
        return "name=小马;";
    }

    public  static String getAgeStr() {
        return "age=23;";
    }

    public  static  String getClassstr(){ return "class=123班";}
}
