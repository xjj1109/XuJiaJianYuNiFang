package text.bwie.asus.xujiajianyunifang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adapter.AllTingsAdapter;
import base.BaseData;
import bean.AllThings;
import view.ShowingPage;

public class Look_More extends FragmentActivity implements SpringView.OnFreshListener {

    private GridView gv9;
    private List<AllThings.DataBean> list = new ArrayList<>();
    private List<AllThings.DataBean> list2 = new ArrayList<>();
    private List<AllThings.DataBean> list3 = new ArrayList<>();
    private List<AllThings.DataBean> orgin = new ArrayList<>();
    private Handler mHandler = new Handler() {
        private AllThings allThings;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s = (String) msg.obj;
            //   Log.i("vvvv", "handleMessage: ++++++++++*"+s);
            Gson gson = new Gson();
            allThings = gson.fromJson(s, AllThings.class);
            jiangxu = (RadioButton) findViewById(R.id.jiangxu);
            moren = (RadioButton) findViewById(R.id.moren);
            shengxu = (RadioButton) findViewById(R.id.shengxu);
            allTingsAdapter = new AllTingsAdapter(Look_More.this, list);
            list.addAll(allThings.getData());
            list2.addAll(allThings.getData());
            list3.addAll(allThings.getData());
            orgin.addAll(allThings.getData());
            Collections.sort(list2);
            Collections.sort(list3);
            Collections.reverse(list3);
            gv9.setAdapter(allTingsAdapter);
            moren.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //显示所有商品
                    list.clear();
                    list.addAll(orgin);
                    allTingsAdapter.notifyDataSetChanged();
                }
            });
            shengxu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //显示所有商品
                    list.clear();
                    list.addAll(list2);
                    allTingsAdapter.notifyDataSetChanged();

                }
            });
            jiangxu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //显示所有商品
                    list.clear();
                    list.addAll(list3);
                    allTingsAdapter.notifyDataSetChanged();
                }
            });
            gv9.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent in=new Intent(Look_More.this,Look_More_XiangQing.class);
                    in.putExtra("img",list.get(position).getGoods_img());
                    in.putExtra("name",list.get(position).getGoods_name());
                    in.putExtra("des",list.get(position).getEfficacy());
                    in.putExtra("price",list.get(position).getMarket_price());
                    in.putExtra("old_price",list.get(position).getShop_price());

                    startActivity(in);
                }
            });

        }

    };
    private AllThings allThings;
    private RadioGroup rg2;
    private TextView paixu_tv;
    private TextView paixu_tv2;
    private TextView paixu_tv3;
    private RadioButton jiangxu;
    private RadioButton moren;
    private RadioButton shengxu;
    private TextView shengxu_line;
    private TextView jiangxu_line;
    private TextView moren_line;
    private ImageView look_more_back;
    private AllTingsAdapter allTingsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look__more);
        SpringView springView3 = (SpringView) findViewById(R.id.springView3);
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        gv9 = (GridView) findViewById(R.id.gv9);
        look_more_back = (ImageView) findViewById(R.id.look_more_back);
        shengxu_line = (TextView) findViewById(R.id.shengxu_line);
        jiangxu_line = (TextView) findViewById(R.id.jiangxu_line);
//        退出界面
        moren_line = (TextView) findViewById(R.id.moren_line);
        look_more_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < rg2.getChildCount(); i++) {
                    if(rg2.getChildAt(i).getId()==checkedId){
                        if(i==0){
                            setColor(moren_line,shengxu_line,jiangxu_line);
                        }else if(i==1){
                            setColor(shengxu_line,jiangxu_line,moren_line);
                        }else{
                            setColor(jiangxu_line,shengxu_line,moren_line);
                        }
                    }
                }
            }
        });

        springView3.setHeader(new DefaultHeader(Look_More.this));
        springView3.setFooter(new DefaultFooter(Look_More.this));
        springView3.setType(SpringView.Type.FOLLOW);
        springView3.setFocusable(false);
        springView3.setListener(this);
        getDate();
    }









    private void setColor(TextView shengxu_line, TextView jiangxu_line, TextView moren_line) {
        shengxu_line.setBackgroundColor(Color.RED);
        jiangxu_line.setBackgroundColor(Color.BLACK);
        moren_line.setBackgroundColor(Color.BLACK);
    }

    private void getDate() {
        new BaseData() {
            @Override
            public void setResultData(String data) {
                mHandler.obtainMessage(0, data).sendToTarget();
            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {

            }
        }.getData("http://m.yunifang.com/yunifang/mobile/goods/getall", "random=87749&encode=ac6bd45b8f50b626a6843b294af8fed5", 0, BaseData.NORMALTIME);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {

    }

}
