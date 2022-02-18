package concurrent.producerconsumer;

/**
 * Created by luque_ruby on 2022/2/17.
 */
public class ProducerConsumer1 {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{for (int i = 0 ; i < 40; i++)
            try {
                data.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(()->{for (int i = 0 ; i < 40; i++)
            try {
                data.consumerer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(()->{for (int i = 0 ; i < 40; i++)
            try {
                data.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
        new Thread(()->{for (int i = 0 ; i < 40; i++)
            try {
                data.consumerer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();
    }

    //判断等待，业务，通知
    static class Data {
        private int num = 0;

        public synchronized void producer() throws InterruptedException {
            /** 必须用while，防止虚假唤醒问题*/
            while (0 != num) {
                this.wait();
            }

            num++;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            this.notifyAll();
        }

        public synchronized void consumerer() throws InterruptedException {
            while (0 == num) {
                this.wait();
            }

            num--;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            this.notifyAll();
        }
    }
}
