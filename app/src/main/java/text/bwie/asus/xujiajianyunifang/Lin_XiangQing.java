package text.bwie.asus.xujiajianyunifang;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import dao.Cart_Dao;
import fragment.CartFragment;

public class Lin_XiangQing extends FragmentActivity implements View.OnClickListener {
    private ImageView gried_view1_back_img;
    private ImageView image2;
    private TextView price2;
    private TextView name2;
    private TextView gried_view1_name_price;
    private ImageView gried_view1_xiangqing_img;
    private ImageView gried_view1_shouchang_img;
    private boolean flag = true;
    private TextView gried_view1_shouchang_tv;
    private String img;
    private String name;
    private String price;
    private String old_price;
    private ImageView gouwuche;
    private Button fenxiang;
    private View vvv;
    private PopupWindow pop, popupWindow;
    private int[] locatin2;
    private ImageView look_more_add, look_more_cutdown;
    private TextView look_more_num;
    private int a = 0;
    private Button look_more_gouwuche;
    private Button lookmore_buy;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gried_view1__xiang_qing);
        Intent intent = getIntent();
        img = intent.getStringExtra("img");
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        old_price = intent.getStringExtra("old_price");
        initView();
    }

    private void initView() {
        gried_view1_back_img = (ImageView) findViewById(R.id.gried_view1_back_img);
        price2 = (TextView) findViewById(R.id.gried_view1_name_old_price);
        name2 = (TextView) findViewById(R.id.gried_view1_name_tv);
        gried_view1_name_price = (TextView) findViewById(R.id.gried_view1_name_price);
        gried_view1_xiangqing_img = (ImageView) findViewById(R.id.gried_view1_xiangqing_img);
//        收藏
        gried_view1_shouchang_img = (ImageView) findViewById(R.id.gried_view1_shouchang_img);
        gried_view1_shouchang_tv = (TextView) findViewById(R.id.gried_view1_shouchang_tv);
        ImageLoader.getInstance().displayImage(img, gried_view1_xiangqing_img);
        gouwuche = (ImageView) findViewById(R.id.gouwuche);
        fenxiang = (Button) findViewById(R.id.fenxiang);
        look_more_cutdown = (ImageView) findViewById(R.id.look_more_cutdown);
        look_more_add = (ImageView) findViewById(R.id.look_more_add);
        look_more_gouwuche = (Button) findViewById(R.id.look_more_gouwuche);
        lookmore_buy = (Button) findViewById(R.id.lookmore_buy);
        price2.setText(old_price);
        name2.setText(name);
        price2.setText(price);
        gried_view1_back_img.setOnClickListener(this);
        gried_view1_shouchang_img.setOnClickListener(this);
        gried_view1_shouchang_tv.setOnClickListener(this);
        gouwuche.setOnClickListener(this);
        fenxiang.setOnClickListener(this);
        look_more_gouwuche.setOnClickListener(this);
        lookmore_buy.setOnClickListener(this);
       /* look_more_cutdown.setOnClickListener(this);
        look_more_add.setOnClickListener(this);*/


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gried_view1_back_img:
                finish();
                break;
            case R.id.gried_view1_shouchang_img:
                if (!flag) {
                    gried_view1_shouchang_tv.setTextColor(Color.BLACK);
                    gried_view1_shouchang_img.setImageResource(R.mipmap.collection);
                    flag = true;
                } else {
                    gried_view1_shouchang_tv.setTextColor(Color.RED);
                    gried_view1_shouchang_img.setImageResource(R.mipmap.collectioned);
                    flag = false;
                }
                break;
            case R.id.gried_view1_shouchang_tv:
                if (!flag) {
                    gried_view1_shouchang_img.setImageResource(R.mipmap.collection);
                    gried_view1_shouchang_tv.setTextColor(Color.BLACK);
                    flag = true;
                } else {
                    gried_view1_shouchang_img.setImageResource(R.mipmap.collectioned);
                    gried_view1_shouchang_tv.setTextColor(Color.RED);
                    flag = false;
                }
                break;
            case R.id.look_more_gouwuche:
                Cart_Dao cart_dao = new Cart_Dao(this);
                cart_dao.add(img, name, price, old_price);
                break;
            //            分享中的popupWindow
            case R.id.fenxiang:
                //  Toast.makeText(this, "sss", Toast.LENGTH_SHORT).show();
                vvv = View.inflate(Lin_XiangQing.this, R.layout.fenxiang_popwindow, null);
                //QQ好友分享
                TextView qq_friends = (TextView) vvv.findViewById(R.id.qq_friends);
                Button fenxiang_dis = (Button) vvv.findViewById(R.id.fenxiang_dis);
                fenxiang_dis.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
                qq_friends.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ShareAction(Lin_XiangQing.this).setPlatform(SHARE_MEDIA.QQ)
                                .withText("hello")
                                .setCallback(umShareListener)
                                .share();
                    }
                });
                pop = new PopupWindow(vvv, ViewGroup.LayoutParams.MATCH_PARENT, 600);
                pop.setBackgroundDrawable(new BitmapDrawable());
                pop.setOutsideTouchable(true);
                pop.setAnimationStyle(R.style.Popupwindow);
                locatin2 = new int[2];
                v.getLocationOnScreen(locatin2);
//                点击popWindow后背景变暗
                if (pop.isShowing()) {
                    pop.dismiss();// 关闭
                } else {
                    pop.showAtLocation(v, Gravity.LEFT | Gravity.BOTTOM, 0, -locatin2[1]);
                    backgroundAlpha(0.3f);
                }
                pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //popupwindow消失的时候恢复成原来的透明度
                        backgroundAlpha(1f);
                    }
                });
                break;
            case R.id.lookmore_buy:
                //Toast.makeText(this, "sss", Toast.LENGTH_SHORT).show();
                View vv = View.inflate(this, R.layout.look_more_pop_item, null);
//               信息
                TextView look_more_pop_price = (TextView) vv.findViewById(R.id.look_more_pop_price);
                look_more_pop_price.setText(name + "\r\n" + "￥50元" + "\r\n" + "库存736件" + "\r\n" + "限购5件");
                ImageView look_more_pop_img = (ImageView) vv.findViewById(R.id.look_more_pop_img);
//                设置图片信息
                ImageLoader.getInstance().displayImage(img, look_more_pop_img);
                ImageView look_more_close = (ImageView) vv.findViewById(R.id.look_more_close);
                look_more_add = (ImageView) vv.findViewById(R.id.look_more_add);
                look_more_num = (TextView) vv.findViewById(R.id.look_more_num);
                look_more_cutdown = (ImageView) vv.findViewById(R.id.look_more_cutdown);
                popupWindow = new PopupWindow(vv, ViewGroup.LayoutParams.MATCH_PARENT, 400);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setFocusable(true);
                popupWindow.setAnimationStyle(R.style.Popupwindow);

                //    popupWindow 显示在最底下
                int[] locatin = new int[2];
                v.getLocationOnScreen(locatin);
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();// 关闭
                } else {
                    backgroundAlpha(0.3f);
                    popupWindow.showAtLocation(v, Gravity.LEFT | Gravity.BOTTOM, 0, -locatin[1]);
                }
                // 立即购买退出键
                look_more_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //popupwindow消失的时候恢复成原来的透明度
                        backgroundAlpha(1f);
                    }
                });
//                增加
                look_more_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Add();
                    }

                    private void Add() {
                        if (a < 5) {
                            a = a + 1;
                            look_more_num.setText(a + "");
                        }
                        if (a == 5) {
                            look_more_add.setImageResource(R.mipmap.i5);
                            look_more_cutdown.setImageResource(R.mipmap.i2);
                        }
                    }
                });
                //减少
                look_more_cutdown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 减少的逻辑
                        JianShao();
                    }

                    private void JianShao() {
                        if (a > 0) {
                            a = a - 1;
                            look_more_num.setText(a + "");
                        }
                        if (a == 0) {
                            look_more_cutdown.setImageResource(R.mipmap.i1);
                            look_more_add.setImageResource(R.mipmap.i4);
                        }
                    }
                });
                break;
            case R.id.gouwuche:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CartFragment cartfragment = new CartFragment();
                fragmentTransaction.add(R.id.goods_fram, cartfragment);
                //fragmentTransaction.addToBackStack("carFragment");
                fragmentTransaction.commit();
                break;

        }
    }
    // 分享
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            Toast.makeText(Lin_XiangQing.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(Lin_XiangQing.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(Lin_XiangQing.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    //popupWindow背景
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

}
