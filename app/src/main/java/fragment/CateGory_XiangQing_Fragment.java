package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.gson.Gson;

import adapter.Category_ItemAdapter;
import base.BaseData;
import bean.ShopFenLeiBean;
import text.bwie.asus.xujiajianyunifang.R;
import utils.URLUtils;
import view.ShowingPage;

/**
 * Created by asus on 2016/12/15.
 */
public class CateGory_XiangQing_Fragment extends Fragment {

    private GridView gridView;
    private String id;
    private ShopFenLeiBean shopFenLeiBean;
    private GridView category_fragment1_gv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.category_fragment1, null);
        id = getArguments().getString("id");
        getNet(id);
        category_fragment1_gv = (GridView) v.findViewById(R.id.category_fragment1_gv);
        return v;
    }
    private void getNet(String id) {
        new BaseData(){
            @Override
            public void setResultData(String data) {
                Gson gson=new Gson();
                shopFenLeiBean = gson.fromJson(data, ShopFenLeiBean.class);
                category_fragment1_gv.setAdapter(new Category_ItemAdapter(getActivity(),shopFenLeiBean.getData()));
            }
            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {
            }
        }.getData(URLUtils.shop_fenlei_Url,URLUtils.getShop_fenlei_Args+id,0,BaseData.NOTIME);
    }
    public static Fragment getArgs(String id){
        CateGory_XiangQing_Fragment ff=new CateGory_XiangQing_Fragment();
        Bundle bundler=new Bundle();
        bundler.putString("id",id);
        ff.setArguments(bundler);
        return ff;
    }
}
