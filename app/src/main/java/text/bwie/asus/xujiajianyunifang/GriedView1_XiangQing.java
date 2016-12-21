package text.bwie.asus.xujiajianyunifang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

public class GriedView1_XiangQing extends AppCompatActivity implements View.OnClickListener {

    private ImageView gried_view1_xiangqing_img;
    private ImageView gried_view1_back_img;
    private TextView name;
    private TextView gried_view1_name_old_price;
    private TextView gried_view1_name_price;
    private ImageView gried_view1_shouchang_img;
    private TextView gried_view1_shouchang_tv;
    private  boolean flag=true;
    private ImageView gouwuche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //   去掉标题
        setContentView(R.layout.activity_gried_view1__xiang_qing);
        initView();
        Intent intent = getIntent();
        String img = intent.getStringExtra("img");
        String name2 = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String old_price = intent.getStringExtra("old_price");
        //得到传过来的图片
        ImageLoader.getInstance().displayImage(img,gried_view1_xiangqing_img);
        //获取商品名
        name.setText(name2);
        //修改价钱
        gried_view1_name_price.setText("￥"+price);
        gried_view1_name_old_price.setText("￥"+old_price);
    }
    private void initView() {
        gouwuche = (ImageView) findViewById(R.id.gouwuche);
        gried_view1_back_img = (ImageView) findViewById(R.id.gried_view1_back_img);
        gried_view1_xiangqing_img = (ImageView) findViewById(R.id.gried_view1_xiangqing_img);
        name = (TextView) findViewById(R.id.gried_view1_name_tv);
        gried_view1_name_price = (TextView) findViewById(R.id.gried_view1_name_price);
        gried_view1_name_old_price = (TextView) findViewById(R.id.gried_view1_name_old_price);
        gried_view1_shouchang_img = (ImageView) findViewById(R.id.gried_view1_shouchang_img);
        gried_view1_shouchang_tv = (TextView) findViewById(R.id.gried_view1_shouchang_tv);
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
                ShouCang();
                break;
            case R.id.gried_view1_shouchang_tv:
                ShouCang();
                break;
            case  R.id.gouwuche:
                Toast.makeText(this, "ss", Toast.LENGTH_SHORT).show();
                    MainActivity.vp.setCurrentItem(2);
                break;
        }
    }
//  收藏
    private void ShouCang() {
        if(!flag){
            gried_view1_shouchang_img.setImageResource(R.mipmap.collection);
            gried_view1_shouchang_tv.setTextColor(Color.BLACK);
            flag=true;
        }else{
            gried_view1_shouchang_img.setImageResource(R.mipmap.collectioned);
            gried_view1_shouchang_tv.setTextColor(Color.RED);
            flag=false;
        }
    }
}
