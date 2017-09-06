package demo.zjd.com.volleydemo.net.Interface;

import java.util.HashMap;
import java.util.Map;

import okhttp3.internal.http2.Header;

/**
 * Created by Administrator on 2017/9/4/004.
 */

public interface IHttpService {
    /**
     * 获取请求头
     * @return
     */
    Map<String, String> getheaders();
}
