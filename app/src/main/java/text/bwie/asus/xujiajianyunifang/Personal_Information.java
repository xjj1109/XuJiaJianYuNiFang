package text.bwie.asus.xujiajianyunifang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import fragment.MineFragment;

/*
* 个人信息
* 注册账号
* */
public class Personal_Information extends FragmentActivity implements View.OnClickListener {
    private ImageView img;
    private TextView num_login;
    private EditText phone_login;
    private EditText phone_zhuce;
    private TextView froget_tv;
    private Button mine_login;
    private TextView quickly_zuche;
    private TextView disanfang;
    private ImageView disanfang_img;
    private ImageView manjian;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int a=  msg.arg1;
            manjian.setVisibility(View.VISIBLE);
        }
    };
    private ImageView qq;
    public static String profile_image_url;
    public static String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal__information);
        initView();

    }
    private void initView() {
        img = (ImageView) findViewById(R.id.closes);
        //账号登陆
        num_login = (TextView) findViewById(R.id.num_login);
        phone_login = (EditText) findViewById(R.id.phone_login);
        phone_zhuce = (EditText) findViewById(R.id.phone_zhuce);
        froget_tv = (TextView) findViewById(R.id.froget_tv);
        mine_login = (Button) findViewById(R.id.mine_login);
        quickly_zuche = (TextView) findViewById(R.id.quickly_zuche);
        manjian = (ImageView) findViewById(R.id.manjian);
//        第三方
        disanfang = (TextView) findViewById(R.id.disanfang);
        disanfang_img = (ImageView) findViewById(R.id.disanfang_img);

        initData();
        num_login.setOnClickListener(this);
        quickly_zuche.setOnClickListener(this);
        img.setOnClickListener(this);
        mine_login.setOnClickListener(this);
        disanfang.setOnClickListener(this);
        disanfang_img.setOnClickListener(this);
    
    }
    private void initData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1000);
                    Message msg =Message.obtain();
                    int arg1 = msg.arg1;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.closes:
                finish();
                break;
            case R.id.num_login:
                phone_login.setHint("手机号");
                froget_tv.setVisibility(View.VISIBLE);
                phone_zhuce.setVisibility(View.VISIBLE);
                mine_login.setText("登陆");
                Toast.makeText(Personal_Information.this, "请登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.quickly_zuche:
                phone_login.setHint("输入验证码");
                Toast.makeText(Personal_Information.this, "欢迎注册", Toast.LENGTH_SHORT).show();
                froget_tv.setVisibility(View.GONE);
                phone_zhuce.setVisibility(View.GONE);
                mine_login.setText("注册");
                break;
            case R.id.mine_login:
                if (mine_login.getText().toString().equals("登陆")) {
                    if (phone_login.getText().toString().trim().equals("")) {
                        Toast.makeText(Personal_Information.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    } else if (phone_zhuce.getText().toString().trim().equals("")) {
                        Toast.makeText(Personal_Information.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //点击获取验证码
                    Toast.makeText(Personal_Information.this, "正在获取", Toast.LENGTH_SHORT).show();

                    //打开注册页面
                    RegisterPage registerPage = new RegisterPage();
                    registerPage.setRegisterCallback(new EventHandler() {
                        public void afterEvent(int event, int result, Object data) {
                            // 解析注册结果
                            if (result == SMSSDK.RESULT_COMPLETE) {
                                @SuppressWarnings("unchecked")
                                HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                                String country = (String) phoneMap.get("country");
                                String phone = (String) phoneMap.get("phone");
                                // 提交用户信息（此方法可以不调用）
                                // registerUser(country, phone);
                            }
                        }
                    });
                    registerPage.show(this);
                }
                break;
            case R.id.disanfang:
                DiSanFang_Q();
                break;
            case R.id.disanfang_img:
                DiSanFang_Q();
                break;
//            qq登陆

        }
    }
    //显示AlertDialog第三方
    private void DiSanFang_Q() {
        View vv=View.inflate(Personal_Information.this, R.layout.disanfang_diarlog,null);
        ImageView img = (ImageView) vv.findViewById(R.id.qq);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Personal_Information.this,"ss",Toast.LENGTH_SHORT).show();
                UMShareAPI mShareAPI = UMShareAPI.get(Personal_Information.this);
//        mShareAPI.doOauthVerify(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                mShareAPI.getPlatformInfo(Personal_Information.this, SHARE_MEDIA.QQ, umAuthListener);
            }
        });
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setView(vv);
        builder.create();
        builder.show();
    }
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //Toast.makeText(getApplicationContext(), "Authorize succeed" + data.toString(), Toast.LENGTH_SHORT).show();
           // Toast.makeText(getApplicationContext(), "....."+data.get("profile_image_url"), Toast.LENGTH_SHORT).show();
            //GetXinxi(data);
          // MineFragment.getQqImg(data.get("profile_image_url"));
           // Log.i("kkkkkk", "GetXinxi: ....."+data.get("profile_image_url"));
//           //图片
            profile_image_url = data.get("profile_image_url");
            ImageLoader.getInstance().displayImage(profile_image_url,MineFragment.touxiang);
            s = data.get("screen_name");
           MineFragment.login.setText(s);
            /* x.image().bind(img, profile_image_url);
            tv.setText(s);*/

//            BitmapUtils

        }
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

/*    public static void GetXinxi(Map<String, String> data) {
        profile_image_url = data.get("profile_image_url");

        MineFragment.getQqImg(data.get("profile_image_url"));

    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
