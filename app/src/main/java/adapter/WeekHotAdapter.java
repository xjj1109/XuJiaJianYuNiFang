package adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import bean.ZhuJieMianBean;
import text.bwie.asus.xujiajianyunifang.R;

/**
 * Created by asus on 2016/11/30.
 */
public class WeekHotAdapter  extends BaseAdapter{
    private final Context context;
    private final List<ZhuJieMianBean.DataBean.SubjectsBean.GoodsListBean> list;


    public WeekHotAdapter(Context context, List<ZhuJieMianBean.DataBean.SubjectsBean.GoodsListBean> list) {
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
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
        ViewHodler viewHodler;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.week_hot_gv_item,null);
            viewHodler = new ViewHodler();
            viewHodler.image = (ImageView) convertView.findViewById(R.id.weekhot_gv_item_img);
            viewHodler.tv  = (TextView) convertView.findViewById(R.id.weekhot_gv_item_tv);
            viewHodler.tv2  = (TextView) convertView.findViewById(R.id.weekhot_gv_item_tv2);
            viewHodler.tv3  = (TextView) convertView.findViewById(R.id.weekhot_gv_item_tv3);
            convertView.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) convertView.getTag();
        }
        Log.i("TAG", "getView: --------"+list.get(position).getGoods_img());
        x.image().bind(viewHodler.image,list.get(position).getGoods_img());
        viewHodler.tv.setText(list.get(position).getGoods_name());
        viewHodler.tv2.setText( "￥"+list.get(position).getMarket_price());
        viewHodler.tv3.setText("￥"+list.get(position).getShop_price());
        return convertView;
    }
 public    class  ViewHodler{
     ImageView image;
     TextView tv;
     TextView tv2 ;
     TextView tv3;
 }
}
