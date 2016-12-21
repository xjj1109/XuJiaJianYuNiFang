package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.FenLeiBean;
import text.bwie.asus.xujiajianyunifang.R;

/**
 * Created by asus on 2016/12/5.
 */
public class FenLeiAdapter extends BaseAdapter {
    private final Context context;
    private final List<FenLeiBean.DataBean.GoodsBriefBean> list;
    private ViewHodler2 viewHodler;

    public FenLeiAdapter(Context context, List<FenLeiBean.DataBean.GoodsBriefBean> list) {
        this.context = context;
        this.list = list;
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
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.fenlei_gv_item, null);
            viewHodler = new ViewHodler2();
            viewHodler.fenlei_gv_item_img = (ImageView) convertView.findViewById(R.id.fenlei_gv_item_img);
            viewHodler.fenlei_gv_item_name = (TextView) convertView.findViewById(R.id.fenlei_gv_item_name);
            viewHodler.fenlei_gv_item_dec = (TextView) convertView.findViewById(R.id.fenlei_gv_item_dec);
            viewHodler.fenlei_gv_item_price = (TextView) convertView.findViewById(R.id.fenlei_gv_item_price);
            viewHodler.fenlei_gv_item_oldprice = (TextView) convertView.findViewById(R.id.fenlei_gv_item_oldprice);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler2) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(position).getGoods_img(), viewHodler.fenlei_gv_item_img);
        viewHodler.fenlei_gv_item_name.setText(list.get(position).getGoods_name());
        viewHodler.fenlei_gv_item_dec.setText(list.get(position).getEfficacy());
        viewHodler.fenlei_gv_item_price.setText(list.get(position).getMarket_price());
        viewHodler.fenlei_gv_item_oldprice.setText(list.get(position).getShop_price());
        return convertView;
    }

    public class ViewHodler2 {
        ImageView fenlei_gv_item_img;
        TextView fenlei_gv_item_name, fenlei_gv_item_dec, fenlei_gv_item_price, fenlei_gv_item_oldprice;


    }
}
