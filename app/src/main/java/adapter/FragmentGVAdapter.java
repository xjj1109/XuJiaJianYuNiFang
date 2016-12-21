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
 * Created by asus on 2016/12/5.
 */
public class FragmentGVAdapter extends BaseAdapter {
    private final Context context;
    private final List<ZhuJieMianBean.DataBean.Ad5Bean> list;

    public FragmentGVAdapter(Context context , List<ZhuJieMianBean.DataBean.Ad5Bean> list) {
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
        View v=View.inflate(context, R.layout.fragment_gv_item_ss,null);
        ImageView img2 = (ImageView) v.findViewById(R.id.fragment_img2);
        TextView fragment_tv2 = (TextView) v.findViewById(R.id.fragment_tv2);
        ImageLoader.getInstance().displayImage(list.get(position).getImage(),img2);
        fragment_tv2.setText(list.get(position).getTitle());
        return v;
    }
}
