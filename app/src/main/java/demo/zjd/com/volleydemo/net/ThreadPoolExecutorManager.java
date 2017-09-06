package demo.zjd.com.volleydemo.net;

import android.util.Log;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/9/5/005.
 */

public class ThreadPoolExecutorManager {

    private ThreadPoolExecutor mThreadPoolExecutor;
    private volatile static ThreadPoolExecutorManager mThreadPoolExecutorManager;
    private final static int COREPOOLSIZE=5;
    private final static int MAXIMUMPOOLSIZE=10;
    private final static long KEEPALIVETIME=30;
    private final static TimeUnit UNIT=TimeUnit.SECONDS;
    private static LinkedBlockingQueue<Future<?>> taskQuene=new LinkedBlockingQueue<>();

    private ThreadPoolExecutorManager() {
        mThreadPoolExecutor=new ThreadPoolExecutor(COREPOOLSIZE,MAXIMUMPOOLSIZE,KEEPALIVETIME,UNIT, new LinkedBlockingDeque<Runnable>(COREPOOLSIZE),mRejectedExecutionHandler);
        mThreadPoolExecutor.execute(runnable);
    }

    public static ThreadPoolExecutorManager getinstance() {
        if (mThreadPoolExecutorManager == null) {
            synchronized (ThreadPoolExecutorManager.class) {
                if (mThreadPoolExecutorManager == null) {
                    mThreadPoolExecutorManager = new ThreadPoolExecutorManager();
                }
            }
        }
        return mThreadPoolExecutorManager;
    }

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            while(true){
                    FutureTask<?> future=null;
                    try {
                        future = (FutureTask<?>) taskQuene.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(future!=null){
                        mThreadPoolExecutor.execute(future);
                    }else{
                        Log.i("zjd","当前线程池大小"+mThreadPoolExecutor.getPoolSize()+"核心数："+mThreadPoolExecutor.getCorePoolSize());
                    }
            }
        }
    };


    public static void execute(Future<?> future) {
        try {
            taskQuene.put(future);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private RejectedExecutionHandler mRejectedExecutionHandler=new RejectedExecutionHandler(){

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if(r==null)
                return;
            Log.i("zjd","线程过多被移除掉了");
            try {
                taskQuene.put(new FutureTask<Runnable>(r,null));
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.i("zjd1","添加失败");
            }
            Log.i("zjd","当前线程池大小"+mThreadPoolExecutor.getPoolSize()+"核心数："+mThreadPoolExecutor.getCorePoolSize());
        }
    };
}
