package demo.zjd.com.volleydemo.net;

import java.util.concurrent.FutureTask;

import demo.zjd.com.volleydemo.net.Interface.IDataCallBack;
import demo.zjd.com.volleydemo.net.Interface.IHttpService;
import demo.zjd.com.volleydemo.net.Interface.IhttpListener;

/**
 * Created by Administrator on 2017/9/4/004.
 */

public class Volley<T,M> {
    /**
     *
     * @param request 请求参数
     * @param url 请求地址
     * @param resposen 响应结果字节码
     * @param mIDataCallBack 响应结果回调
     */
    public void sendRequest(T request, String url, Class<M> resposen, IDataCallBack<M> mIDataCallBack){
        RequestHolder<T> mRequestHolder=new RequestHolder<>();
        mRequestHolder.setRequest(request);
        mRequestHolder.setUrl(url);
        IhttpListener mIhttpListener=new JsonhttpListener(resposen,mIDataCallBack);
        mRequestHolder.setmIhttpListener(mIhttpListener);
        IHttpService mIHttpService =new JsonHttpService();
        mRequestHolder.setmIHttpService(mIHttpService);
        HttpTask<T>  task=new HttpTask<>(mRequestHolder);
        ThreadPoolExecutorManager.getinstance().execute(new FutureTask<>(task,null));
    }
}
