package demo.zjd.com.volleydemo.net;

import demo.zjd.com.volleydemo.net.Interface.IHttpService;
import demo.zjd.com.volleydemo.net.Interface.IhttpListener;

/**
 * Created by Administrator on 2017/9/4/004.
 * 封装请求类
 */
public class RequestHolder<T> {
    /**
     * 请求数据
     */
    private T request;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求处理类
     */
    private IHttpService mIHttpService;
    /**
     * 请求结果处理类
     */
    private IhttpListener mIhttpListener;

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public IHttpService getmIHttpService() {
        return mIHttpService;
    }

    public void setmIHttpService(IHttpService mIHttpService) {
        this.mIHttpService = mIHttpService;
    }

    public IhttpListener getmIhttpListener() {
        return mIhttpListener;
    }

    public void setmIhttpListener(IhttpListener mIhttpListener) {
        this.mIhttpListener = mIhttpListener;
    }
}
