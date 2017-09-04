package demo.zjd.com.volleydemo.net.Interface;

import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/4/004.
 */

public interface IhttpListener {
    void success(Response mResposen);
    void fail(String msg);
}
