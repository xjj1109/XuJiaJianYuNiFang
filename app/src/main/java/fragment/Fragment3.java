package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import text.bwie.asus.xujiajianyunifang.MainActivity;
import text.bwie.asus.xujiajianyunifang.R;
/**
 * Created by asus on 2016/11/28.
 */
public class Fragment3 extends Fragment implements View.OnClickListener, Animation.AnimationListener {
    private ImageView navigation_img;
    private boolean startAnim = true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, null);
        navigation_img = (ImageView) view.findViewById(R.id.navigation_img);
        navigation_img.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        if (startAnim) {
            startAnim = false;
            TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
            translateAnimation.setDuration(3000);
            navigation_img.startAnimation(translateAnimation);
            translateAnimation.setAnimationListener(this);
            translateAnimation.setFillAfter(true);
        }

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        Intent in = new Intent(getActivity(), MainActivity.class);
        startActivity(in);
        getActivity().finish();

    }


    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}