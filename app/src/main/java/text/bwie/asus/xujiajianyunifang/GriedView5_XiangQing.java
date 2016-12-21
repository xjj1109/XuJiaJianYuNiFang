package text.bwie.asus.xujiajianyunifang;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class GriedView5_XiangQing extends AppCompatActivity implements View.OnClickListener {
    private ImageView gried_view1_back_img;
    private ImageView image2;
    private TextView price2;
    private TextView name2;
    private TextView gried_view1_name_price;
    private ImageView gried_view1_xiangqing_img;
    private ImageView gried_view1_shouchang_img;
    private  boolean flag=true;
    private TextView gried_view1_shouchang_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gried_view1__xiang_qing);
        Intent intent = getIntent();
        String img = intent.getStringExtra("img");
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String old_price = intent.getStringExtra("old_price");
        gried_view1_back_img = (ImageView) findViewById(R.id.gried_view1_back_img);
        price2 = (TextView) findViewById(R.id.gried_view1_name_old_price);
        name2 = (TextView) findViewById(R.id.gried_view1_name_tv);
        gried_view1_name_price = (TextView) findViewById(R.id.gried_view1_name_price);
        gried_view1_xiangqing_img = (ImageView) findViewById(R.id.gried_view1_xiangqing_img);
//        收藏
        gried_view1_shouchang_img = (ImageView) findViewById(R.id.gried_view1_shouchang_img);
        gried_view1_shouchang_tv = (TextView) findViewById(R.id.gried_view1_shouchang_tv);
        ImageLoader.getInstance().displayImage(img,gried_view1_xiangqing_img);
        price2.setText(old_price);
        name2.setText(name);
        price2.setText(price);
        gried_view1_back_img.setOnClickListener(this);
        gried_view1_shouchang_img.setOnClickListener(this);
        gried_view1_shouchang_tv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gried_view1_back_img:
                finish();
                break;
            case R.id.gried_view1_shouchang_img:
                if(!flag){
                    gried_view1_shouchang_tv.setTextColor(Color.BLACK);
                    gried_view1_shouchang_img.setImageResource(R.mipmap.collection);
                    flag=true;
                }else {
                    gried_view1_shouchang_tv.setTextColor(Color.RED);
                    gried_view1_shouchang_img.setImageResource(R.mipmap.collectioned);
                    flag=false;
                }
                break;
            case R.id.gried_view1_shouchang_tv:
                if(!flag){
                    gried_view1_shouchang_img.setImageResource(R.mipmap.collection);
                    gried_view1_shouchang_tv.setTextColor(Color.BLACK);
                    flag=true;
                }else {
                    gried_view1_shouchang_img.setImageResource(R.mipmap.collectioned);
                    gried_view1_shouchang_tv.setTextColor(Color.RED);
                    flag=false;
                }
                break;
        }
    }
}
