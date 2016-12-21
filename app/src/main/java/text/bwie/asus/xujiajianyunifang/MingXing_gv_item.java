package text.bwie.asus.xujiajianyunifang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MingXing_gv_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gried_view1__xiang_qing);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        TextView gried_view1_name_tv = (TextView) findViewById(R.id.gried_view1_name_tv);
        gried_view1_name_tv.setText(name);
   }
}
