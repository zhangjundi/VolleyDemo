package demo.zjd.com.volleydemo.net;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/4/004.
 */

public class HttpTask<T> implements Runnable {
    private final RequestHolder<T> mRequestHolder;
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");
    public HttpTask(RequestHolder<T> mRequestHolder) {
       this.mRequestHolder=mRequestHolder;
    }

    @Override
    public void run() {
        OkHttpClient client = new OkHttpClient.Builder().build();
//        new Request.Builder().build().url(mRequestHolder.getUrl());
        Request.Builder builder = new Request.Builder();
        Gson gson=new Gson();
        builder.url(mRequestHolder.getUrl());
        if(mRequestHolder.getRequest()!=null){
            RequestBody body= RequestBody.create(MEDIA_TYPE_MARKDOWN,gson.toJson(mRequestHolder.getRequest()));
            builder.post(body);
        }
        try {
            Response response = client.newCall(builder.build()).execute();
//            MyResposen myResposen=new MyResposen();
            if(response!=null&&(response.code()==200||response.code()==304)) {
                mRequestHolder.getmIhttpListener().success(response);
            }else
                mRequestHolder.getmIhttpListener().fail("请求失败，请求状态值是"+(response!=null?response.code():-1));
        } catch (IOException e) {
            e.printStackTrace();
            mRequestHolder.getmIhttpListener().fail(e.getMessage());
        }
    }
}
