package concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by luque_ruby on 2022/2/22.
 *
 */
public class FutureDemo {
    public static void main(String[] args) {
        //没有返回值的异步回调
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsync=>void");
        });

        System.out.println("1111");
        try {
            completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(()-> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync=>void");
//            int i = 10 / 0;
            return 1024;
        });

        try {
            System.out.println(completableFuture1.whenComplete((t, u) -> {
                System.out.println("t=>" + t); //正常的返回结果
                System.out.println("u=>" + u); //错误信息，如果没错误就是null，如果有错误就返回错误结果
            }).exceptionally((e) -> {
                System.out.println(e.getMessage());
                return 233;
            }).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
