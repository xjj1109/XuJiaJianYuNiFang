package fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import adapter.FenLeiAdapter;
import base.BaseData;
import base.BaseFragment;
import bean.FenLeiBean;
import text.bwie.asus.xujiajianyunifang.CategoryXiangQingActivity;
import text.bwie.asus.xujiajianyunifang.R;
import utils.URLUtils;
import view.ShowingPage;


/**
 * Created by xujiajian on 16/9/28.
 */
public class CategoryFragment extends BaseFragment implements View.OnClickListener{
    private FenLeiBean fenLeiBean;
    private TextView fenlei_tv1;
    private TextView fenlei_tv2;
    private TextView fenlei_tv3;
    private TextView fenlei_tv4;
    private TextView fenlei_tv5;
    private TextView fenlei_fuzhi;
    private TextView fenlei_mingxing;
    private TextView fenlei_fuzhu1;
    private TextView fenlei_fuzhu2;
    private TextView fenlei_fuzhu3;
    private TextView fenlei_fuzhu4;
    private TextView fenlei_fuzhu5;
    private TextView fenlei_fuzhu6;
    private GridView fenlei_gv;
    private View v;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private TextView fenlei_gongxiao;
    ArrayList<FenLeiBean.DataBean.CategoryBean.ChildrenBean> list = new ArrayList<>();
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s = (String) msg.obj;
            Gson gson = new Gson();
            fenLeiBean = gson.fromJson(s, FenLeiBean.class);
            Log.e("sssssss", "handleMessage: ________" + s);
            fenlei_gongxiao.setText("        " + fenLeiBean.getData().getCategory().get(0).getCat_name());
            fenlei_fuzhi.setText("        " + fenLeiBean.getData().getCategory().get(2).getCat_name());
//            肤质
            fenlei_fuzhu1.setText("#" + fenLeiBean.getData().getCategory().get(2).getChildren().get(2).getCat_name() + "#");
            fenlei_fuzhu2.setText("#" + fenLeiBean.getData().getCategory().get(2).getChildren().get(1).getCat_name() + "#");
            fenlei_fuzhu3.setText("#" + fenLeiBean.getData().getCategory().get(2).getChildren().get(0).getCat_name() + "#");
            fenlei_fuzhu4.setText("#" + fenLeiBean.getData().getCategory().get(2).getChildren().get(3).getCat_name() + "#");
            fenlei_fuzhu5.setText("#" + fenLeiBean.getData().getCategory().get(2).getChildren().get(4).getCat_name() + "#");
            fenlei_fuzhu6.setText("#" + fenLeiBean.getData().getCategory().get(2).getChildren().get(5).getCat_name() + "#");
//            明星产品GriedView的适配器
            fenlei_gv.setAdapter(new FenLeiAdapter(getActivity(), fenLeiBean.getData().getGoodsBrief()));
            Runnable scrollViewRunable = new Runnable() {
                @Override
                public void run() {
                    ((ScrollView) v.findViewById(R.id.scrollView3)).scrollTo(10, 10);
                }
            };

            mHandler.post(scrollViewRunable);
        }
    };
    private TextView fenlei_tv11;
    private TextView fenlei_tv111;

    //解析路径
    @Override
    protected void onLoad() {
        CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        MyHomeData myHomeData = new MyHomeData();
        myHomeData.getData(URLUtils.homeUrl, URLUtils.homeArgs, 0, BaseData.NORMALTIME);
        FenLei fenLei = new FenLei();
        fenLei.getData(URLUtils.fenlei_Url, URLUtils.fenlei_Args, 0, BaseData.NORMALTIME);
    }
    @Override
    public View createSuccessView() {
        v = View.inflate(getActivity(), R.layout.category_fragment, null);
        InitView();
        return v;
    }
    private void InitView() {
        fenlei_gongxiao = (TextView) v.findViewById(R.id.fenlei_gongxiao);
        //按功效
        fenlei_tv1 = (TextView) v.findViewById(R.id.fenlei_tv1);
        fenlei_tv2 = (TextView) v.findViewById(R.id.fenlei_tv2);
        fenlei_tv3 = (TextView) v.findViewById(R.id.fenlei_tv3);
        fenlei_tv4 = (TextView) v.findViewById(R.id.fenlei_tv4);
        fenlei_tv5 = (TextView) v.findViewById(R.id.fenlei_tv5);
        fenlei_fuzhi = (TextView) v.findViewById(R.id.fenlei_fuzhi);
        // 皮肤质量
        fenlei_fuzhu1 = (TextView) v.findViewById(R.id.fenlei_fuzhu1);
        fenlei_fuzhu2 = (TextView) v.findViewById(R.id.fenlei_fuzhu2);
        fenlei_fuzhu3 = (TextView) v.findViewById(R.id.fenlei_fuzhu3);
        fenlei_fuzhu4 = (TextView) v.findViewById(R.id.fenlei_fuzhu4);
        fenlei_fuzhu5 = (TextView) v.findViewById(R.id.fenlei_fuzhu5);
        fenlei_fuzhu6 = (TextView) v.findViewById(R.id.fenlei_fuzhu6);
        //面膜
        img1 = (ImageView) v.findViewById(R.id.img1);
        img2 = (ImageView) v.findViewById(R.id.img2);
        img3 = (ImageView) v.findViewById(R.id.img3);
        img4 = (ImageView) v.findViewById(R.id.img4);
        img5 = (ImageView) v.findViewById(R.id.img5);
        img6 = (ImageView) v.findViewById(R.id.img6);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);
        fenlei_tv1.setOnClickListener(this);
        fenlei_tv2.setOnClickListener(this);
        fenlei_tv3.setOnClickListener(this);
        fenlei_tv4.setOnClickListener(this);
        fenlei_tv5.setOnClickListener(this);
        fenlei_fuzhu1.setOnClickListener(this);
        fenlei_fuzhu2.setOnClickListener(this);
        fenlei_fuzhu3.setOnClickListener(this);
        fenlei_fuzhu4.setOnClickListener(this);
        fenlei_fuzhu5.setOnClickListener(this);
        fenlei_fuzhu6.setOnClickListener(this);
        //明星产品
        fenlei_gv = (GridView) v.findViewById(R.id.fenlei_gv);
        SpringView springView2 = (SpringView) v.findViewById(R.id.springView2);
        springView2.setHeader(new DefaultHeader(getActivity()));
        springView2.setType(SpringView.Type.FOLLOW);
    }

    //    面膜监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img1:
                Intent inn = new Intent(getActivity(), CategoryXiangQingActivity.class);
                inn.putExtra("id", fenLeiBean.getData().getCategory().get(1).getChildren().get(6).getId());
                inn.putExtra("title", fenLeiBean.getData().getCategory().get(1).getChildren().get(6).getCat_name());
                ArrayList<FenLeiBean.DataBean.CategoryBean.ChildrenBean> shoplist = new ArrayList<>();
                shoplist.add(fenLeiBean.getData().getCategory().get(1).getChildren().get(6));
                shoplist.add(fenLeiBean.getData().getCategory().get(1).getChildren().get(7));
                shoplist.add(fenLeiBean.getData().getCategory().get(1).getChildren().get(8));
                inn.putExtra("list", (Serializable) shoplist);
                getActivity().startActivity(inn);
                break;
            case R.id.img2:
                //intentActivity(fenLeiBean.getData().getCategory().get(1).getChildren().get(1).getCat_name(),null,0,Integer.valueOf(fenLeiBean.getData().getCategory().get(1).getChildren().get(1).getId()));
                Intent inn2 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                inn2.putExtra("id", fenLeiBean.getData().getCategory().get(1).getChildren().get(1).getId());
                inn2.putExtra("title", fenLeiBean.getData().getCategory().get(1).getChildren().get(1).getCat_name());
                getActivity().startActivity(inn2);
                break;
            case R.id.img3:
                //intentActivity(fenLeiBean.getData().getCategory().get(1).getChildren().get(2).getCat_name(),null,0,Integer.valueOf(fenLeiBean.getData().getCategory().get(1).getChildren().get(2).getId()));
                Intent inn3 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                inn3.putExtra("id", fenLeiBean.getData().getCategory().get(1).getChildren().get(2).getId());
                inn3.putExtra("title", fenLeiBean.getData().getCategory().get(1).getChildren().get(2).getCat_name());
                getActivity().startActivity(inn3);
                break;
            case R.id.img4:
                //intentActivity(fenLeiBean.getData().getCategory().get(1).getChildren().get(3).getCat_name(),null,0,Integer.valueOf(fenLeiBean.getData().getCategory().get(1).getChildren().get(3).getId()));
                Intent inn4 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                inn4.putExtra("id", fenLeiBean.getData().getCategory().get(1).getChildren().get(3).getId());
                inn4.putExtra("title", fenLeiBean.getData().getCategory().get(1).getChildren().get(3).getCat_name());
                getActivity().startActivity(inn4);
                break;
            case R.id.img5:
                //intentActivity(fenLeiBean.getData().getCategory().get(1).getChildren().get(4).getCat_name(),null,0,Integer.valueOf(fenLeiBean.getData().getCategory().get(1).getChildren().get(4).getId()));
                Intent inn5 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                inn5.putExtra("title", fenLeiBean.getData().getCategory().get(1).getChildren().get(4).getCat_name());
                inn5.putExtra("id", fenLeiBean.getData().getCategory().get(1).getChildren().get(4).getId());
                getActivity().startActivity(inn5);
                break;
            case R.id.img6:
                //intentActivity(fenLeiBean.getData().getCategory().get(1).getChildren().get(5).getCat_name(),null,0,Integer.valueOf(fenLeiBean.getData().getCategory().get(1).getChildren().get(5).getId()));
                Intent inn6 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                inn6.putExtra("id", fenLeiBean.getData().getCategory().get(1).getChildren().get(5).getId());
                inn6.putExtra("title", fenLeiBean.getData().getCategory().get(1).getChildren().get(5).getCat_name());
                getActivity().startActivity(inn6);
                break;
            // 补水保湿
            case R.id.fenlei_tv1:
                Intent inn7 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                inn7.putExtra("id", fenLeiBean.getData().getCategory().get(0).getChildren().get(0).getId());
                inn7.putExtra("title", fenLeiBean.getData().getCategory().get(0).getChildren().get(0).getCat_name());
                startActivity(inn7);
                break;
//            舒缓修复
            case R.id.fenlei_tv2:
                Intent inn8 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                inn8.putExtra("id", fenLeiBean.getData().getCategory().get(0).getChildren().get(1).getId());
                inn8.putExtra("title", fenLeiBean.getData().getCategory().get(0).getChildren().get(1).getCat_name());
                startActivity(inn8);
                break;
            case R.id.fenlei_tv3:
                Intent inn9 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                inn9.putExtra("id", fenLeiBean.getData().getCategory().get(0).getChildren().get(2).getId());
                inn9.putExtra("title", fenLeiBean.getData().getCategory().get(0).getChildren().get(2).getCat_name());
                startActivity(inn9);
                break;
            case R.id.fenlei_tv4:
                Intent inn10 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                inn10.putExtra("id", fenLeiBean.getData().getCategory().get(0).getChildren().get(3).getId());
                inn10.putExtra("title", fenLeiBean.getData().getCategory().get(0).getChildren().get(3).getCat_name());
                startActivity(inn10);
                break;
            case R.id.fenlei_tv5:
                Intent inn11 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                inn11.putExtra("id", fenLeiBean.getData().getCategory().get(0).getChildren().get(4).getId());
                inn11.putExtra("title", fenLeiBean.getData().getCategory().get(0).getChildren().get(4).getCat_name());
                startActivity(inn11);
                break;
            case R.id.fenlei_fuzhu1:
                Intent in = new Intent(getActivity(), CategoryXiangQingActivity.class);
                in.putExtra("id", fenLeiBean.getData().getCategory().get(2).getChildren().get(2).getId());
                in.putExtra("title", fenLeiBean.getData().getCategory().get(2).getChildren().get(2).getCat_name());
                startActivity(in);
                break;
            case R.id.fenlei_fuzhu2:
                Intent in2 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                in2.putExtra("id", fenLeiBean.getData().getCategory().get(2).getChildren().get(1).getId());
                in2.putExtra("title", fenLeiBean.getData().getCategory().get(2).getChildren().get(1).getCat_name());
                startActivity(in2);
                break;
            case R.id.fenlei_fuzhu3:
                Intent in3 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                in3.putExtra("id", fenLeiBean.getData().getCategory().get(2).getChildren().get(0).getId());
                in3.putExtra("title", fenLeiBean.getData().getCategory().get(2).getChildren().get(0).getCat_name());
                startActivity(in3);
                break;
            case R.id.fenlei_fuzhu4:
                Intent in4 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                in4.putExtra("id", fenLeiBean.getData().getCategory().get(2).getChildren().get(3).getId());
                in4.putExtra("title", fenLeiBean.getData().getCategory().get(2).getChildren().get(3).getCat_name());
                startActivity(in4);
                break;

            case R.id.fenlei_fuzhu5:
                Intent in5 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                in5.putExtra("id", fenLeiBean.getData().getCategory().get(2).getChildren().get(4).getId());
                in5.putExtra("title", fenLeiBean.getData().getCategory().get(2).getChildren().get(4).getCat_name());
                startActivity(in5);
                break;
            case R.id.fenlei_fuzhu6:
                Intent in6 = new Intent(getActivity(), CategoryXiangQingActivity.class);
                in6.putExtra("id", fenLeiBean.getData().getCategory().get(2).getChildren().get(5).getId());
                in6.putExtra("title", fenLeiBean.getData().getCategory().get(2).getChildren().get(5).getCat_name());
                startActivity(in6);
                break;

        }
    }



    class MyHomeData extends BaseData {
        @Override
        public void setResultData(String data) {
            //先设置数据
            CategoryFragment.this.data = data;
            //data有可能为空
            //再展示
            CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }

        @Override
        protected void setResulttError(ShowingPage.StateType stateLoadError) {
            //失败
            CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }
    }

    class MyHomeXiangQing extends BaseData {
        @Override
        public void setResultData(String data) {
            //先设置数据
            CategoryFragment.this.data11 = data;
            //data有可能为空
            //再展示
            CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }

        @Override
        protected void setResulttError(ShowingPage.StateType stateLoadError) {
            //失败
            CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }
    }

    class FenLei extends BaseData {
        @Override
        public void setResultData(String data) {
            //先设置数据
            CategoryFragment.this.data13 = data;
            Message msg = new Message();
            msg.obj = data;
            mHandler.sendMessage(msg);
            //data有可能为空
            //再展示
            CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        }

        @Override
        protected void setResulttError(ShowingPage.StateType stateLoadError) {
            //失败
            CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }
    }

    private void intentActivity(String title, List<FenLeiBean.DataBean.CategoryBean.ChildrenBean> list, int position, int id) {
        Intent intent2 = new Intent(getActivity(), CategoryXiangQingActivity.class);
        intent2.putExtra("list", (Serializable) list);
        intent2.putExtra("title", title);
        intent2.putExtra("position", position);
        intent2.putExtra("id", id);
        getActivity().startActivity(intent2);
    }
}
