package text.bwie.asus.xujiajianyunifang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.webkit.WebView;

public class HomeXingYunChouJIang extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_xing_yun_chou_jiang);
        WebView web = (WebView) findViewById(R.id.web);
        Intent intent = getIntent();
        String uri = intent.getStringExtra("uri");
        web.loadUrl(uri);



    }
}
