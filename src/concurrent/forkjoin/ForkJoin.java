package concurrent.forkjoin;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Created by luque_ruby on 2022/2/22.
 * 并行执行任务，提高效率，大数据量，拆分为多个子任务(将大事化为多个小事)
 * 工作窃取：A、B两个线程均有5个任务，但A只执行到第2个，B全部执行完了，B将A未执行完的任务取出来执行
 * 如何使用：1.通过forkjoinPool来执行；2.计算任务fork
 */
public class ForkJoin {
    public static void main(String[] args) {
//        test1();
        try {
            test2();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        test3();
    }

    public static void test1() {
        long start = System.currentTimeMillis();

        Long sum = 0L;
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }

        long end = System.currentTimeMillis();
        System.out.println("normal add: sum = " + sum + "时间: " + (end - start));
    }

    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();


        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("forkJoin: sum = " + sum + "时间: " + (end - start));
    }

    public static void test3() {
        long start = System.currentTimeMillis();

        Long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(1, Long::sum);

        long end = System.currentTimeMillis();
        System.out.println("forkJoin: sum = " + sum + "时间: " + (end - start));
    }
}


class ForkJoinDemo extends RecursiveTask<Long> {
    private Long start;
    private Long end;

    //临界值
    private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start < temp) {
            /** 普通循环*/
            Long sum = 0L;
            for (Long i = 0L; i <= 60000L; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork();//拆分任务，把任务压入线程队列

            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();
            
            return task1.join() + task2.join();
        }
    }
}

