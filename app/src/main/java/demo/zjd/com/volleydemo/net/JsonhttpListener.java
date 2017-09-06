package demo.zjd.com.volleydemo.net;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;

import demo.zjd.com.volleydemo.net.Interface.IDataCallBack;
import demo.zjd.com.volleydemo.net.Interface.IhttpListener;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/5/005.
 */

public class JsonhttpListener<T> implements IhttpListener {
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Class<T> clazz;
    private IDataCallBack mIDataCallBack;
    private Gson gson;

    public JsonhttpListener(Class<T> clazz, IDataCallBack mIDataCallBack) {
        this.clazz = clazz;
        this.mIDataCallBack = mIDataCallBack;
        gson = new Gson();
    }

    @Override
    public void success(final Response mResposen) {
        try {
           final T t = gson.fromJson(mResposen.body().string(), clazz);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mIDataCallBack.success(t);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            final String msg = e.getMessage();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mIDataCallBack.fail(msg);
                }
            });
        }



    }

    @Override
    public void fail(final String msg) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mIDataCallBack.fail(msg);
            }
        });
    }
}
