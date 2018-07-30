package demo.zjd.com.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import demo.zjd.com.volleydemo.net.Interface.IDataCallBack;
import demo.zjd.com.volleydemo.net.Volley;

public class MainActivity extends AppCompatActivity {
    public static final String url = "http://v.juhe.cn/toutiao/index?type=top&key=29da5e8be9ba88b932394b7261092f71";
    private static final String TAG = "zjd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = (TextView) findViewById(R.id.tv);
        for (int i = 0; i < 100; i++) {
            final int a = i;
            new Volley<Object, NewsPager>().sendRequest(null, url, NewsPager.class, new IDataCallBack<NewsPager>() {

                @Override
                public void success(NewsPager newsPager) {
                    Log.i(TAG, a + newsPager.toString());
                    tv.append(a+"\n");
                }

                @Override
                public void fail(String msg) {
                    Log.i(TAG, msg);
                    tv.append(a+msg+"\n");
                }
            });
        }
    }
}
