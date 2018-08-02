package thread.async;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by yinweitao on 2018/8/2.
 */
public class Main {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskExecutorConfig.class);

        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);

        for (int i = 0; i < 10; i++) {
//            asyncTaskService.executeAsyncTask(i);
//            asyncTaskService.executeAsyncTaskPlus(i);
            asyncTaskService.asyncInvokeReturnFuture(i);
        }
        context.close();
    }
}
