package demo.zjd.com.volleydemo.net;

import java.io.InputStream;

/**
 * Created by Administrator on 2017/9/4/004.
 */

public class MyResposen {
    /**
     * 返回状态吗
     */
    private int code;
    /**
     * 返回流
     */
    private InputStream mInputStream;
    /**
     * 返回字符【串
     */
    private String content;
    /**
     * 返回内容长度
     */
    private long content_length;
    /**
     * 返回头文件
     */
    private String[] headers;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public InputStream getmInputStream() {
        return mInputStream;
    }

    public void setmInputStream(InputStream mInputStream) {
        this.mInputStream = mInputStream;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getContent_length() {
        return content_length;
    }

    public void setContent_length(long content_length) {
        this.content_length = content_length;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }
}
