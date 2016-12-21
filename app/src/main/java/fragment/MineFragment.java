package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapter.MineListAdapter;
import base.BaseFragment;
import text.bwie.asus.xujiajianyunifang.Personal_Information;
import text.bwie.asus.xujiajianyunifang.R;
import text.bwie.asus.xujiajianyunifang.Settingss;
import utils.ImageLoaderUtils;


/**
 * Created by xujiajian on 16/9/28.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    private ListView lv;
    private ArrayList<String> list2;
    private ArrayList<Integer> list;
    private Map<String,Integer> map =new HashMap<>();
    private RelativeLayout rl;
    public static ImageView touxiang;
    public static Button login;
    private ImageView shezhi;

    @Override
    protected void onLoad() {

    }

    @Override
    public View createSuccessView() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mine_layout, null);
        lv = (ListView) v.findViewById(R.id.lv);
        rl = (RelativeLayout) v.findViewById(R.id.rl);
        touxiang = (ImageView) v.findViewById(R.id.touxiang);
        shezhi = (ImageView) v.findViewById(R.id.shezhi);
        rl.setFocusable(true);
        login = (Button) v.findViewById(R.id.login);
        shezhi.setOnClickListener(this);
        rl.setOnClickListener(this);
        touxiang.setOnClickListener(this);
        login.setOnClickListener(this);
        return v;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        lv.setPadding(10,20,10,20);
        lv.setAdapter(new MineListAdapter(getActivity(),list2,list,lv));

       // Toast.makeText(getActivity(),"....."+Personal_Information.profile_image_url,Toast.LENGTH_SHORT).show();
    }
    int i=0;
    @Override
    public void onResume() {
        super.onResume();
       if(Personal_Information.profile_image_url!=null){
            ImageLoader.getInstance().displayImage(Personal_Information.profile_image_url,MineFragment.touxiang, ImageLoaderUtils.initCircleOptions());
            login.setText(Personal_Information.s);
        }
    }
    //获取图片和内容
    private void initData() {
        list=new ArrayList<>();
        list.add(R.mipmap.my_order_icon);
        list.add(R.mipmap.ratingbar_progress);
        list.add(R.mipmap.my_vip_icon);
        list.add(R.mipmap.coupons);
        list.add(R.mipmap.my_lottery_icon);
        list.add(R.mipmap.my_collection_icon);
        list.add(R.mipmap.personal_center_contact_service_icon);
        list2=new ArrayList<>();
        list2.add("我的订单");
        list2.add("邀请有礼");
        list2.add("刷脸测尺寸");
        list2.add("我的现金卷");
        list2.add("我的抽奖单");
        list2.add("我收藏的商品");
        list2.add("联系客服");


    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case  R.id.rl:
            getIntents();
            break;
        case  R.id.touxiang:
            getIntents();
            break;
        case R.id.login:
            getIntents();
            break;
        case R.id.shezhi:
            Intent in=new Intent(getActivity(), Settingss.class);
            startActivity(in);
            break;
    }
    }
//跳转
    private void getIntents() {
        Intent in=new Intent(getActivity(), Personal_Information.class);
        startActivity(in);
    }
}
