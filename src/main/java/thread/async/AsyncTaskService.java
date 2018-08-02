package thread.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by yinweitao on 2018/8/2.
 */
@Service
public class AsyncTaskService {

    @Async
    public void executeAsyncTask(Integer i) {
        System.out.println("执行异步任务: " + i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("执行异步任务+1: " + (i + 1));
    }

    @Async
    public Future<String> asyncInvokeReturnFuture(int i) {
        System.out.println("asyncInvokeReturnFuture, parementer={}"+ i);
        Future<String> future;
        try {
            Thread.sleep(1000 * 4);

            future = new AsyncResult<String>("success:" + i);

        } catch (InterruptedException e) {
            future = new AsyncResult<String>("error");
        }

        return future;
    }
}
