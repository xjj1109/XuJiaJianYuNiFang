package text.bwie.asus.xujiajianyunifang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;

import fragment.Fragment1;
import fragment.Fragment2;
import fragment.Fragment3;

public class NavigationFragmentActivity extends FragmentActivity {
    private ArrayList<Fragment> list;
    private ImageView navigation_img;

    //导航动画页
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_navigation_fragment);
        ViewPager vp2 = (ViewPager) findViewById(R.id.vp2);
        initData();
        vp2.setAdapter(new NavigationAdapter(getSupportFragmentManager()));
    }


    class  NavigationAdapter extends FragmentPagerAdapter{

        public NavigationAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }
        @Override
        public int getCount() {
            return list.size();
        }
    }
    private void initData() {
        list = new ArrayList<>();
        Fragment1 f1=new Fragment1();
        Fragment2 f2=new Fragment2();
        Fragment3 f3=new Fragment3();
        list.add(f1);
        list.add(f2);
        list.add(f3);
    }
}
