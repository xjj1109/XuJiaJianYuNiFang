package fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.ArrayList;

import adapter.FragmentGVAdapter;
import adapter.HomeBottomAdapter;
import adapter.MyListViewAdapter;
import adapter.MyLunBoAdapter;
import adapter.YouHuiAdapter;
import base.BaseData;
import base.BaseFragment;
import bean.ZhuJieMianBean;
import text.bwie.asus.xujiajianyunifang.GriedView1_XiangQing;
import text.bwie.asus.xujiajianyunifang.GriedView6_XiangQing;
import text.bwie.asus.xujiajianyunifang.HomeXingYunChouJIang;
import text.bwie.asus.xujiajianyunifang.Look_More;
import text.bwie.asus.xujiajianyunifang.Personal_Information;
import text.bwie.asus.xujiajianyunifang.R;
import utils.URLUtils;
import view.ShowingPage;
/**
 * Created by xujiajian on 16/9/28.
 */
public class HomeFragment extends BaseFragment implements SpringView.OnFreshListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private ViewPager vp3;
    //轮播图
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getLunBo(msg);
        }

        private void getLunBo(Message msg) {
            super.handleMessage(msg);
            int currentItem = vp3.getCurrentItem();
            currentItem++;
            vp3.setCurrentItem(currentItem);
            sendEmptyMessageDelayed(0, 2000);
        }
    };
    // 解析数据
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getNets();
        }
    };
    private GridView home_fragment_gv;
    private ListView lv;
    private Button look_more;
    private LinearLayout home_lin;

    private void getNets() {
        Gson gson = new Gson();
        zhuJieMianBean = gson.fromJson(data, ZhuJieMianBean.class);
        gv6.setAdapter(new HomeBottomAdapter(getActivity(), zhuJieMianBean.getData().getDefaultGoodsList()));
        vp3.setAdapter(new MyLunBoAdapter(getActivity(), zhuJieMianBean.getData().getAd1(), mHandler));
        vp3.setCurrentItem(5000);


        vp3.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <doclist.size() ; i++) {
                    if (position%doclist.size() == i) {
                        doclist.get(i).setImageResource(R.drawable.dot_focuse);
                    }else {
                        doclist.get(i).setImageResource(R.drawable.dot_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp4.setAdapter(new YouHuiAdapter(getActivity(), zhuJieMianBean.getData().getActivityInfo().getActivityInfoList()));
        vp4.setCurrentItem(3000);
        vp4.setPageTransformer(true, new ScaleInTransformer());
        vp4.setPageMargin(50);
        vp4.setOffscreenPageLimit(3);
        lv.setAdapter(new MyListViewAdapter(getActivity(), zhuJieMianBean.getData().getSubjects(), zhuJieMianBean));
  //      lv.setOnItemClickListener(this);
        home_fragment_gv.setAdapter(new FragmentGVAdapter(getActivity(), zhuJieMianBean.getData().getAd5()));
        /*
         ScrollView为什么加载页面直接显示底部
         进行处理
        * */
        Runnable scrollViewRunable = new Runnable() {
            @Override
            public void run() {
                ((ScrollView)v.findViewById(R.id.scrollview2)).scrollTo(10, 10) ;
            }
        };
        mHandler.post(scrollViewRunable);
    }
    ArrayList<ImageView> doclist=new ArrayList<>();
    private void getDoc() {
        for (int i = 0; i <doclist.size() ; i++) {
            ImageView img=new ImageView(getActivity());
                if(i==0){
                    img.setImageResource(R.drawable.dot_focuse);
                }else {
                    img.setImageResource(R.drawable.dot_normal);
                }
            LinearLayout.LayoutParams  layoutParams =new LinearLayout.LayoutParams(100,100);
            layoutParams.setMargins(10,0,10,0);
            home_lin.addView(img,layoutParams);
            doclist.add(img);
        }
    }


    private ViewPager vp4;

    private SpringView springView;

    private View v;
    private ZhuJieMianBean zhuJieMianBean;

    private GridView gv6;

    @Override
    protected void onLoad() {
        //    请求成功
        MyHomeData myHomeData = new MyHomeData();
        myHomeData.getData(URLUtils.homeUrl, URLUtils.homeArgs, 0, BaseData.NORMALTIME);
        MyHomeXiangQing myHomeXiangQing = new MyHomeXiangQing();
        myHomeXiangQing.getData(URLUtils.xiangqing_Url, URLUtils.xiangqingArgs, 0, BaseData.NORMALTIME);
    }

    @Override
    public View createSuccessView() {
        v = View.inflate(getActivity(), R.layout.home_fragment, null);
        InitView(v);
        return v;
    }

    private void InitView(final View v) {
        vp3 = (ViewPager) v.findViewById(R.id.vp3);
        vp4 = (ViewPager) v.findViewById(R.id.vp4);
        lv = (ListView) v.findViewById(R.id.lv);
//        查看更多
        look_more = (Button) v.findViewById(R.id.look_more);
        look_more.setOnClickListener(this);
        gv6 = (GridView) v.findViewById(R.id.gv6);
        home_lin = (LinearLayout) v.findViewById(R.id.home_lin);
        getDoc();
        springView = (SpringView) v.findViewById(R.id.springView);
        home_fragment_gv = (GridView) v.findViewById(R.id.home_fragment_gv);
        home_fragment_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private AlertDialog.Builder builder;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 || position == 2) {
                    builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("登陆");
                    builder.setMessage("此功能仅限手机号登录用户使用，请先登录");
                    builder.setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getActivity(), Personal_Information.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                } else {
                    Intent in4 = new Intent(getActivity(), HomeXingYunChouJIang.class);
                    in4.putExtra("uri", zhuJieMianBean.getData().getAd5().get(position).getAd_type_dynamic_data());
                    getActivity().startActivity(in4);
                }
            }
        });
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(this);
        // springView.setFooter(new DefaultFooter(getActivity()));
        springView.setHeader(new DefaultHeader(getActivity()));
        //initPic();
        handler.sendEmptyMessageDelayed(1, 2000);
        mHandler.sendEmptyMessageDelayed(0, 2000);
        // gv.setOnItemClickListener(this);
        gv6.setOnItemClickListener(this);
    }
    //下拉刷新
    @Override
    public void onRefresh() {
    }
    //上啦加载
    @Override
    public void onLoadmore() {
    }
//    查看更多
    @Override
    public void onClick(View v) {
        Intent in=new Intent(getActivity(), Look_More.class);
        startActivity(in);
    }

    class MyHomeData extends BaseData {
        @Override
        public void setResultData(String data) {
            //先设置数据
            HomeFragment.this.data = data;
            //data有可能为空

            //再展示
            HomeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }

        @Override
        protected void setResulttError(ShowingPage.StateType stateLoadError) {
            //失败
            HomeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }
    }
    class MyHomeXiangQing extends BaseData {
        @Override
        public void setResultData(String data) {
            //先设置数据
            HomeFragment.this.data11 = data;

            //data有可能为空

            //再展示
            HomeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }

        @Override
        protected void setResulttError(ShowingPage.StateType stateLoadError) {
            //失败
            HomeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }

    }
        @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {

            case R.id.gv6:
                Intent intent3 = new Intent(getActivity(), GriedView6_XiangQing.class);
                intent3.putExtra("img", zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().get(position).getGoods_img());
                intent3.putExtra("name", zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().get(position).getGoods_name());
                intent3.putExtra("price", zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().get(position).getMarket_price());
                intent3.putExtra("old_price", zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().get(position).getShop_price());
                startActivity(intent3);
                break;
        }
    }

    private void Intent_gv1(int position) {
        Intent in = new Intent(getActivity(), GriedView1_XiangQing.class);
        in.putExtra("img", zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().get(position).getGoods_img());
        in.putExtra("name", zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().get(position).getGoods_name());
        in.putExtra("price", zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().get(position).getShop_price());
        in.putExtra("old_price", zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().get(position).getMarket_price());
        startActivity(in);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacksAndMessages(null);
    }

}
