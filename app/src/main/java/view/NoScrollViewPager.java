package view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by asus on 2016/11/28.
 */
public class NoScrollViewPager extends LazyViewPager{
    public NoScrollViewPager(Context context) {
        super(context);
    }
    public  NoScrollViewPager(Context context, AttributeSet attrs){
        super(context,attrs);

    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
