package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.ZhuJieMianBean;
import text.bwie.asus.xujiajianyunifang.R;

/**
 * Created by asus on 2016/12/2.
 */
public class HomeBottomAdapter extends BaseAdapter {
    private  List<ZhuJieMianBean.DataBean.DefaultGoodsListBean> list;
    private Context context;
    private ViewHodler viewHolder;
    public HomeBottomAdapter(Context context, List<ZhuJieMianBean.DataBean.DefaultGoodsListBean> list) {
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
        if(convertView == null ){
            convertView =View.inflate(context, R.layout.home_bottom_gv_item,null);
            viewHolder = new ViewHodler();
            viewHolder.img= (ImageView) convertView.findViewById(R.id.home_bottom_gv_img);
            viewHolder.tv1= (TextView) convertView.findViewById(R.id.home_bottom_gv_tv);
            viewHolder.tv2= (TextView) convertView.findViewById(R.id.home_bottom_gv_tv2);
            viewHolder.tv3= (TextView) convertView.findViewById(R.id.home_bottom_gv_tv3);
                convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHodler) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(position).getGoods_img(),viewHolder.img);
        viewHolder.tv1.setText(list.get(position).getGoods_name());
        viewHolder.tv2.setText(list.get(position).getMarket_price());
        viewHolder.tv3.setText(list.get(position).getShop_price());
        return convertView;
    }
    public  class ViewHodler{
        ImageView img;
        TextView tv1,tv2,tv3;
    }
}