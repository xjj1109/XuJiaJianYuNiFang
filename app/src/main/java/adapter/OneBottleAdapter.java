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
import utils.ImageLoaderUtils;

/**
 * Created by asus on 2016/12/2.
 */
public class OneBottleAdapter extends BaseAdapter {
    private final Context context;
    private final List<ZhuJieMianBean.DataBean.SubjectsBean.GoodsListBean> list;
    private ViewHolder viewHodler;

    public OneBottleAdapter(Context context, List<ZhuJieMianBean.DataBean.SubjectsBean.GoodsListBean> list) {
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
            if(convertView == null){
                convertView =View.inflate( context, R.layout.one_bottle_item,null);
                viewHodler = new ViewHolder();
                 viewHodler.img= (ImageView) convertView.findViewById(R.id.onebottle_item_img);
                  viewHodler.tv1= (TextView) convertView.findViewById(R.id.onebottle_item_tv);
                  viewHodler.tv2= (TextView) convertView.findViewById(R.id.onebottle_item_tv2);
                 viewHodler.tv3= (TextView) convertView.findViewById(R.id.onebottle_item_tv3);
                convertView.setTag(viewHodler);
            }else {
              viewHodler= (ViewHolder) convertView.getTag();
            }
        ImageLoader.getInstance().displayImage(list.get(position).getGoods_img(),viewHodler.img,ImageLoaderUtils.initOptions());
        viewHodler.tv1.setText(list.get(position).getGoods_name());
        viewHodler.tv2.setText(list.get(position).getMarket_price());
        viewHodler.tv3.setText(list.get(position).getShop_price());

       return convertView;
    }
    public  class  ViewHolder{
        ImageView img;
        TextView tv1,tv2,tv3;
    }
}
