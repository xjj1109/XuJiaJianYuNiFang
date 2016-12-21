package text.bwie.asus.xujiajianyunifang;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import adapter.MyFragmentAdapter;
import view.LazyViewPager;

public class MainActivity extends AutoLayoutActivity implements RadioGroup.OnCheckedChangeListener {

    public static LazyViewPager vp;
    public static RadioGroup rg;
    private RadioButton rb;
    private RadioButton rb_home_page;
    //    定义变量，点击两次退出程序
    private static boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vp = (LazyViewPager) findViewById(R.id.vp);
        rg = (RadioGroup) findViewById(R.id.rg);
        rb_home_page = (RadioButton) findViewById(R.id.rb_home_page);
        rg.setOnCheckedChangeListener(this);
        vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
    }

    //选择类型
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home_page:
                vp.setCurrentItem(0);
                break;
            case R.id.rb_category:
                vp.setCurrentItem(1);
                break;
            case R.id.rb_cart:
                vp.setCurrentItem(2);
                break;
            case R.id.rb_mine:
                vp.setCurrentItem(3);
                break;
            default:
                break;
        }
    }

    //    点击两次退出程序
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

}
