package demo.zjd.com.volleydemo.net.Interface;

/**
 * Created by Administrator on 2017/9/4/004.
 */

public interface IDataCallBack<T> {
    void success(T t);
    void fail(String msg);
}
