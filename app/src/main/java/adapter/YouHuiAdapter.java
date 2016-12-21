package adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.ZhuJieMianBean;
import text.bwie.asus.xujiajianyunifang.R;
import utils.ImageLoaderUtils;

/**
 * Created by asus on 2016/12/1.
 */
public class YouHuiAdapter extends PagerAdapter {
    private final Context context;
    private final List<ZhuJieMianBean.DataBean.ActivityInfoBean.ActivityInfoListBean> list;

    public YouHuiAdapter(Context context, List<ZhuJieMianBean.DataBean.ActivityInfoBean.ActivityInfoListBean> list) {
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       View v=View.inflate(context, R.layout.home_fragment_img,null);
        ImageView img = (ImageView) v.findViewById(R.id.img);
        img.setPadding(2,2,2,2);
        if(list.size()>0&&list.get(position%list.size())!=null){
            ImageLoader.getInstance().displayImage(list.get(position%list.size()).getActivityImg(),img, ImageLoaderUtils.initOptions());
            container.addView(v);
        }
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
