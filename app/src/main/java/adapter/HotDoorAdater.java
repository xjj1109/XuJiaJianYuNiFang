package adapter;

import android.content.Context;
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
 * Created by asus on 2016/12/1.
 */
public class HotDoorAdater extends BaseAdapter {
    private final Context context;
    private final List<ZhuJieMianBean.DataBean.DefaultGoodsListBean> list;
    private ViewHolder viewholder;

    public HotDoorAdater(Context context, List<ZhuJieMianBean.DataBean.DefaultGoodsListBean> list) {
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
        if(convertView==null){
            convertView =View.inflate(context, R.layout.hot_door_gv_item,null);
            viewholder = new ViewHolder();
            viewholder.img= (ImageView) convertView.findViewById(R.id.hot_door_gv_item_img);
           viewholder.tv1= (TextView) convertView.findViewById(R.id.hot_door_gv_item_tv);
            viewholder.tv2= (TextView) convertView.findViewById(R.id.hot_door_gv_item_tv2);
            viewholder.tv3= (TextView) convertView.findViewById(R.id.hot_door_gv_item_tv3);
                convertView.setTag(viewholder);
        }else {
            viewholder= (ViewHolder) convertView.getTag();
        }
        x.image().bind(viewholder.img,list.get(position).getGoods_img());
        viewholder.tv1.setText(list.get(position).getGoods_name());
        viewholder.tv2.setText( "￥"+list.get(position).getMarket_price());
        viewholder.tv3.setText("￥"+list.get(position).getShop_price());
        return convertView;
    }
    public  class  ViewHolder{
        ImageView img;
        TextView tv1;
        TextView tv2;
        TextView tv3;
    }
}
