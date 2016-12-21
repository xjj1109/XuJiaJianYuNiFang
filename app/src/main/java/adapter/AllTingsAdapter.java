package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.AllThings;
import text.bwie.asus.xujiajianyunifang.R;
import utils.ImageLoaderUtils;

/**
 * Created by asus on 2016/12/9.
 */
public class AllTingsAdapter extends BaseAdapter {
    private final Context context;
    public final List<AllThings.DataBean> list;

    public AllTingsAdapter(Context context, List<AllThings.DataBean> list) {
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
    View v=View.inflate(context, R.layout.allthings_adapter_item,null);
        TextView allthings_gv_name = (TextView) v.findViewById(R.id.allthings_gv_name);
        TextView allthings_gv_dec = (TextView) v.findViewById(R.id.allthings_gv_dec);
        ImageView allthings_gv_img = (ImageView) v.findViewById(R.id.allthings_gv_img);
        TextView allthings_gv_price = (TextView) v.findViewById(R.id.allthings_gv_price);
        TextView allthings_gv_old_price = (TextView) v.findViewById(R.id.allthings_gv_old_price);
        allthings_gv_name.setText(list.get(position).getGoods_name());
        allthings_gv_dec.setText(list.get(position).getEfficacy());
        ImageLoader.getInstance().displayImage(list.get(position).getGoods_img(),allthings_gv_img, ImageLoaderUtils.initOptions());
        allthings_gv_price.setText(String .valueOf(list.get(position).getMarket_price()));
       allthings_gv_old_price.setText(String.valueOf(list.get(position).getShop_price()+""));

        return v;
    }
}
