package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.ZhuJieMianBean;
import text.bwie.asus.xujiajianyunifang.LunBoXiangQing;
import utils.ImageLoaderUtils;

/**
 * Created by asus on 2016/11/30.
 */
public class MyLunBoAdapter extends PagerAdapter {
    private final Context context;
    private final List<ZhuJieMianBean.DataBean.Ad1Bean> list;
    private final Handler handler;

    public MyLunBoAdapter(Context context , List<ZhuJieMianBean.DataBean.Ad1Bean> list, Handler handler) {
        this.context=context;
        this.list=list;
        this.handler=handler;
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
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView img=new ImageView(context);
        if(list.size()>0&&list.get(position%list.size())!=null){
            ImageLoader.getInstance().displayImage(list.get(position%list.size()).getImage(),img, ImageLoaderUtils.initOptions());
            container.addView(img);
            img.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch ( event.getAction()){
                        case MotionEvent.ACTION_UP:
                            handler.sendEmptyMessageDelayed(0,2000);
                            Intent intent =new Intent(context, LunBoXiangQing.class);
                            intent.putExtra("uri",list.get(position%list.size()).getAd_type_dynamic_data());
                            context.startActivity(intent);
                            break;
//                        点击跳转
                        case MotionEvent.ACTION_DOWN:
                            handler.removeCallbacksAndMessages(null);

                            break;
                        case  MotionEvent.ACTION_CANCEL:
                            handler.sendEmptyMessageDelayed(0,2000);

                            break;
                    }
                    return true;
                }
            });
        }

        return img;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }
}
