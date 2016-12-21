package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.ZhuJieMianBean;
import text.bwie.asus.xujiajianyunifang.Lin_XiangQing;
import text.bwie.asus.xujiajianyunifang.R;
import text.bwie.asus.xujiajianyunifang.XiangQingTupIAn;
import utils.ImageLoaderUtils;

/**
 * Created by asus on 2016/12/6.
 */
public class MyListViewAdapter extends BaseAdapter {
    private final Context context;
    private final List<ZhuJieMianBean.DataBean.SubjectsBean> list;
    private final ZhuJieMianBean bean;
    private ViewHolder viewHolder;

    public MyListViewAdapter(Context context, List<ZhuJieMianBean.DataBean.SubjectsBean> list, ZhuJieMianBean bean) {
        this.context = context;
        this.list = list;
        this.bean = bean;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.lv_item, null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.lv_img);
            viewHolder.lin7 = (LinearLayout) convertView.findViewById(R.id.lin7);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent =new Intent(context, XiangQingTupIAn.class);
                intent.putExtra("title",list.get(position).getTitle());
                intent.putExtra("end_time",list.get(position).getEnd_time());
                intent.putExtra("detail",list.get(position).getDetail());
                intent.putExtra("img",list.get(position).getGoodsList().get(position).getGoods_img());
                intent.putExtra("name",list.get(position).getGoodsList().get(position).getGoods_name());
                intent.putExtra("price",list.get(position).getGoodsList().get(position).getMarket_price());
                intent.putExtra("old_price",list.get(position).getGoodsList().get(position).getShop_price());

             context.startActivity(intent);
            }
        });
        ImageLoader.getInstance().displayImage(list.get(position).getImage(), viewHolder.img);
        for ( int i=0; i < 6; i++) {
            View vv = View.inflate(context, R.layout.lv_lin_item, null);
            ImageView lv_lin_img = (ImageView) vv.findViewById(R.id.lv_lin_img);
            TextView lv_lin_name = (TextView) vv.findViewById(R.id.lv_lin_name);
            TextView lv_lin_price = (TextView) vv.findViewById(R.id.lv_lin_price);
            TextView lv_lin_old_price = (TextView) vv.findViewById(R.id.lv_lin_old_price);
            if(bean.getData().getSubjects().get(position).getGoodsList().get(i).getGoods_name().length()>10){
                lv_lin_name.setText(bean.getData().getSubjects().get(position).getGoodsList().get(i).getGoods_name().substring(0,10)+"\r\n"+bean.getData().getSubjects().get(position).getGoodsList().get(i).getGoods_name().substring(10,bean.getData().getSubjects().get(position).getGoodsList().get(i).getGoods_name().length()-1)+"\r\n");
            }
            lv_lin_price.setText(bean.getData().getSubjects().get(position).getGoodsList().get(i).getMarket_price());
            lv_lin_old_price.setText(bean.getData().getSubjects().get(position).getGoodsList().get(i).getShop_price());
            ImageLoader.getInstance().displayImage(bean.getData().getSubjects().get(position).getGoodsList().get(i).getGoods_img(), lv_lin_img, ImageLoaderUtils.initOptions());
            final int finalI = i;
            vv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(context, Lin_XiangQing.class);
                    in.putExtra("img",bean.getData().getSubjects().get(position).getGoodsList().get(finalI).getGoods_img()) ;
                    in.putExtra("name",bean.getData().getSubjects().get(position).getGoodsList().get(finalI).getGoods_name());
                    in.putExtra("price",bean.getData().getSubjects().get(position).getGoodsList().get(finalI).getMarket_price());
                    in.putExtra("old_price",bean.getData().getSubjects().get(position).getGoodsList().get(finalI).getShop_price());
                    context.startActivity(in);
                }
            });
            viewHolder.lin7.addView(vv);
        }

        return convertView;
    }

    public class ViewHolder {
        ImageView img;
        LinearLayout lin7;

    }


}
