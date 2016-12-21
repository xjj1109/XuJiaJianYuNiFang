package text.bwie.asus.xujiajianyunifang;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import adapter.Cate_gv_ItemAdapter;
import base.BaseData;
import bean.FenLeiBean;
import bean.ShopFenLeiBean;
import fragment.CateGory_XiangQing_Fragment;
import utils.URLUtils;
import view.ShowingPage;

public class CategoryXiangQingActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private String id;
    private String title;
    private TextView cate_mianmo;
    private GridView cate_gv;
    private ShopFenLeiBean shopFenLeiBean;
    private LinearLayout cate_lin;
    private LinearLayout cate_xianshi;
    private String s;
    private List<FenLeiBean.DataBean.CategoryBean.ChildrenBean> list;
    private ImageView category_back_img;
    private ViewPager cate_vp;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        //接受id
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        list = (List<FenLeiBean.DataBean.CategoryBean.ChildrenBean>) getIntent().getSerializableExtra("list");
        initView();
        getNet();
    }
    private void initView() {
        category_back_img = (ImageView) findViewById(R.id.category_back_img);
        cate_mianmo = (TextView) findViewById(R.id.cate_mianmo);
        cate_gv = (GridView) findViewById(R.id.cate_gv);
        cate_xianshi = (LinearLayout) findViewById(R.id.cate_xianshi);
        cate_lin = (LinearLayout) findViewById(R.id.cate_lin);
        cate_vp = (ViewPager) findViewById(R.id.cate_vp);
        category_back_img.setOnClickListener(this);
        //如果点击的是面膜 显示
        if (list != null) {
            cate_xianshi.setVisibility(View.VISIBLE);
            for (int i = 0; i < list.size(); i++) {
                textView = new TextView(CategoryXiangQingActivity.this);
                textView.setId(i);
                if (i == 0) {
                    textView.setTextColor(Color.RED);
                }
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i =0; i <list.size() ;i++) {
                            if (v.getId() == i) {
                                Toast.makeText(CategoryXiangQingActivity.this, "sss" + textView.getId(), Toast.LENGTH_SHORT).show();
                                cate_vp.setCurrentItem(i);
                            }
                        }
                    }
                });
                LinearLayout.LayoutParams lin = new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lin.setMargins(50, 5, 50, 5);
                textView.setText(list.get(i).getCat_name());
                cate_lin.addView(textView, lin);
            }
            cate_vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return CateGory_XiangQing_Fragment.getArgs(list.get(position).getId());
                }
                @Override
                public int getCount() {
                    return list.size();
                }
            });
            cate_vp.setOnPageChangeListener(this);
        } else {
            cate_xianshi.setVisibility(View.GONE);
        }
    }
    //请求数据
    public void getNet() {
        new BaseData() {
            @Override
            public void setResultData(String data) {
                this.data = data;
                Gson gson = new Gson();
                shopFenLeiBean = gson.fromJson(data, ShopFenLeiBean.class);
                cate_mianmo.setText(title);
                cate_gv.setAdapter(new Cate_gv_ItemAdapter(CategoryXiangQingActivity.this, shopFenLeiBean.getData()));
            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {

            }
        }.getData(URLUtils.shop_fenlei_Url, URLUtils.getShop_fenlei_Args + id, 0, BaseData.NOTIME);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //退出按钮
            case R.id.category_back_img:
                finish();
                break;
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < list.size(); i++) {
            TextView tv = (TextView) cate_lin.getChildAt(i);
            if (position == i) {
                tv.setTextColor(Color.RED);
            } else {
                tv.setTextColor(Color.BLACK);
            }
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
