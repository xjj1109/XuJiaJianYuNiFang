package text.bwie.asus.xujiajianyunifang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class Firstnavigation extends FragmentActivity implements View.OnClickListener {
    private Button tiaoguo;
    private int recLen=3;
    Timer timer =new Timer();
    //判断重复
    private boolean repetition = true;
    private Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int arg1 = msg.arg1;
            Intent in = new Intent(Firstnavigation.this, NavigationFragmentActivity.class);
            startActivity(in);
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //   去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_firstnavigation);
        tiaoguo = (Button) findViewById(R.id.tiaoguo);
        timer.schedule(task, 1000, 1000);
        tiaoguo.setOnClickListener(this);
        SharedPreferences sp = getSharedPreferences("flag", MODE_PRIVATE);
        boolean flag = sp.getBoolean("flag", false);
        //第一次运行
        if (!flag) {
            InitData();
        } else {
            initZhuJieMian();
        }
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("flag", true);
          edit.commit();

    }
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recLen--;
                    tiaoguo.setText("还剩"+recLen+"秒");
                    if(recLen ==0){
                        tiaoguo.setText("跳过");
                    }else if(recLen<0) {
                        tiaoguo.setVisibility(View.GONE);
                    }
                }
            });
        }
    };

    //    跳转主界面
    private void initZhuJieMian() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    if (repetition) {
                        Intent in = new Intent(Firstnavigation.this, MainActivity.class);
                        startActivity(in);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private void InitData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    if (repetition) {
                        Message msg = Message.obtain();
                        Log.i("Aa", "run: sssssss" + msg);
                        int arg1 = msg.arg1;
                        h.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //   第二次登陆 点击跳转主界面
    @Override
    public void onClick(View v) {
    repetition = false;
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        finish();
    }
}
