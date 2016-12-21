package adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.ShopFenLeiBean;
import text.bwie.asus.xujiajianyunifang.R;
import utils.ImageLoaderUtils;

/**
 * Created by asus on 2016/12/16.
 */
public class Cate_gv_ItemAdapter extends BaseAdapter {
    private final Context context;
    private final List<ShopFenLeiBean.DataBean> list;
    private ImageView cate_gv_item_img;
    private TextView cate_gv_item_title;
    private TextView cate_gv_item_dec;
    private TextView cate_gv_item_price;
    private TextView cate_gv_item_old_price;


    public Cate_gv_ItemAdapter(Context  context , List<ShopFenLeiBean.DataBean> list) {
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
        View inflate = View.inflate(context, R.layout.category_gv_item, null);
        cate_gv_item_img = (ImageView) inflate.findViewById(R.id.cate_gv_item_img);
        cate_gv_item_title = (TextView) inflate.findViewById(R.id.cate_gv_item_title);
        cate_gv_item_dec = (TextView) inflate.findViewById(R.id.cate_gv_item_dec);
        cate_gv_item_price = (TextView) inflate.findViewById(R.id.cate_gv_item_price);
        cate_gv_item_old_price = (TextView) inflate.findViewById(R.id.cate_gv_item_old_price);
        ImageLoader.getInstance().displayImage(list.get(position).getGoods_img(),cate_gv_item_img, ImageLoaderUtils.initOptions());
        cate_gv_item_title.setText(list.get(position).getGoods_name());
        cate_gv_item_dec.setText(list.get(position).getEfficacy());
        cate_gv_item_price.setText("￥"+list.get(position).getMarket_price());
        cate_gv_item_old_price.setText("￥"+list.get(position).getShop_price());
        cate_gv_item_old_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG |Paint.ANTI_ALIAS_FLAG);
        return inflate;
    }
}
