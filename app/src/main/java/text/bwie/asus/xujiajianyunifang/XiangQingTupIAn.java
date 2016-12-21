package text.bwie.asus.xujiajianyunifang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import bean.ZhuJieMianBean;
import utils.GetNetUtils;

public class XiangQingTupIAn extends FragmentActivity {

    private TextView xiangqing_title;
    private TextView xiangqing_end_time;
    private TextView xiangqing_datil;
    private GridView xiangqing_gv;
    private String title;
    private String end_time;
    private String detail;
    private String img;
    private String name;
    private String price;
    private String old_price;
    private List<ZhuJieMianBean.DataBean.SubjectsBean.GoodsListBean> list2=new ArrayList<>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String ss = (String) msg.obj;
                Gson gson = new Gson();
                zhuJieMianBean = gson.fromJson(ss, ZhuJieMianBean.class);
                list2.addAll(0,zhuJieMianBean.getData().getSubjects().get(0).getGoodsList());
             /*   Cart_Dao dao=new Cart_Dao(XiangQingTupIAn.this);
                dao.add(list2.get(0).getGoods_name(),list2.get(0).getGoods_img(),list2.get(0).getMarket_price(),list2.get(0).getShop_price());*/
                xiangqing_gv.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        if (convertView == null) {
                            convertView = View.inflate(XiangQingTupIAn.this, R.layout.things_xiangqing, null);
                            vh = new ViewHolder();
                            vh.name = (TextView) convertView.findViewById(R.id.things_name);
                            vh.price = (TextView) convertView.findViewById(R.id.things_price);
                            vh.old_price = (TextView) convertView.findViewById(R.id.things_old_price);
                            vh.img= (ImageView) convertView.findViewById(R.id.things_img);
                            convertView.setTag(vh);
                        }
                        else {
                             vh=(ViewHolder) convertView.getTag();
                        }
                        vh.name.setText(zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().get(position).getGoods_name());
                        vh.price.setText(zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().get(position).getMarket_price());
                        vh.old_price.setText(zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().get(position).getShop_price());
                       // vh.des.setText(zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().get(position).getEfficacy());
                        ImageLoader.getInstance().displayImage(zhuJieMianBean.getData().getSubjects().get(0).getGoodsList().get(position).getGoods_img(),vh.img);
                        return convertView;
                    }



                });
            }

        }
    };
    private ViewHolder vh;
    private ZhuJieMianBean zhuJieMianBean;

    public       class ViewHolder {
    ImageView img;
    TextView name, des, price, old_price;
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       requestWindowFeature (Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing_tup_ian);
        JieShou();
        InitView();
        getNet();
    }

    private void getNet() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String path = "http://m.yunifang.com/yunifang/mobile/home?random=59676&encode=62d458fefce9c740359873cc19b05188";
                String nets = GetNetUtils.getNets(path);
                mHandler.obtainMessage(0, nets).sendToTarget();
            }
        }.start();
    }

    //接受跳转传值
    private void JieShou() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        end_time = intent.getStringExtra("end_time");
        detail = intent.getStringExtra("detail");
    }

    // 找控件
    private void InitView() {
        xiangqing_title = (TextView) findViewById(R.id.xiangqing_title);
        xiangqing_end_time = (TextView) findViewById(R.id.xiangqing_end_time);
        xiangqing_datil = (TextView) findViewById(R.id.xiangqing_datil);
        xiangqing_gv = (GridView) findViewById(R.id.xiangqing_gv);
        xiangqing_title.setText(title);
        xiangqing_end_time.setText(end_time);
        xiangqing_datil.setText(detail);
    }
}
