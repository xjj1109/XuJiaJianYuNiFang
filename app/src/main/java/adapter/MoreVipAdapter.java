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
 * Created by asus on 2016/12/1.
 */
public class MoreVipAdapter extends BaseAdapter {
    private final Context context;
    private final List<ZhuJieMianBean.DataBean.SubjectsBean> list;
    private ViewHolder viewHolder;

    public MoreVipAdapter(Context context , List<ZhuJieMianBean.DataBean.SubjectsBean> list) {
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
            convertView =View.inflate(context, R.layout.more_vip_gv_item,null);
            viewHolder = new ViewHolder();
             viewHolder.img= (ImageView) convertView.findViewById(R.id.more_vip_img);
               viewHolder.tv= (TextView) convertView.findViewById(R.id.more_vip_tv);
               viewHolder.tv2= (TextView) convertView.findViewById(R.id.more_vip_tv2);
               viewHolder.tv3= (TextView) convertView.findViewById(R.id.more_vip_tv3);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(list.get(position).getImage(),viewHolder.img);
      // x.image().bind(viewHolder.img,list.get(position).getImage());
        viewHolder.tv.setText(list.get(position).getTitle());
        viewHolder.tv3.setText(list.get(position).getShow_number());
        viewHolder.tv2.setText(list.get(position).getId());
        return convertView;
    }
    public  class ViewHolder{
        ImageView img;
        TextView tv,tv2,tv3;
    }
}
