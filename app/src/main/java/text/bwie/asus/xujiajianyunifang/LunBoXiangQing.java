package text.bwie.asus.xujiajianyunifang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.webkit.WebView;

public class LunBoXiangQing extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lun_bo_xiang_qing);
        Intent intent = getIntent();
        WebView web2 = (WebView) findViewById(R.id.web2);
        String uri = intent.getStringExtra("uri");
        web2.loadUrl(uri);

    }
}
