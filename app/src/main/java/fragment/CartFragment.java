package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.MyCartDaoLvAdapter;
import base.BaseFragment;
import bean.AllThings;
import bean.CartSqlBean;
import dao.Cart_Dao;
import text.bwie.asus.xujiajianyunifang.MainActivity;
import text.bwie.asus.xujiajianyunifang.R;
import view.ShowingPage;

/**
 * Created by xujiajian on 16/9/28.
 */
public class CartFragment extends BaseFragment implements View.OnClickListener {
    private String s;
    private RelativeLayout rl;
    private  CartFragment fragment;
    private Cart_Dao cart_dao;
    ArrayList<AllThings.DataBean> list = new ArrayList();

    public static ArrayList<Integer> li=new ArrayList<>();
    private MyCartDaoLvAdapter myCartDaoLvAdapter;
    private View v;
    public static Button delete_cart;
    public static Button quanxuan_btn;
    public static Button fanxuan_btn;

    @Override
    protected void onLoad() {
        CartFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    public View createSuccessView() {
        final Cart_Dao cart_dao = new Cart_Dao(getActivity());
        final ArrayList<CartSqlBean> select = cart_dao.select();
        Log.i("TAG***********", select.size() + "" + select.toString());

        if (select.size() == 0) {
            v = View.inflate(getActivity(), R.layout.gouwuche_view, null);
            Button guangyiguang = (Button) v.findViewById(R.id.guangyiguang);
            guangyiguang.setOnClickListener(this);
        } else {
            v = View.inflate(getActivity(), R.layout.cart_dao_lv, null);
//            删除按钮
            delete_cart = (Button) v.findViewById(R.id.delete_cart);
            quanxuan_btn = (Button) v.findViewById(R.id.quanxuan_btn);
            fanxuan_btn = (Button) v.findViewById(R.id.fanxuan_btn);
            final ListView cart_dao_lv2 = (ListView) v.findViewById(R.id.cart_dao_lv2);
            myCartDaoLvAdapter = new MyCartDaoLvAdapter(getActivity(), select);
            cart_dao_lv2.setAdapter(myCartDaoLvAdapter);
            cart_dao_lv2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    cart_dao.delete(select.get(position).getName());
                    select.remove(select.get(position));
                    myCartDaoLvAdapter.notifyDataSetChanged();
                    if (select.size() == 0) {
                        Toast.makeText(getActivity(), "请去商城中挑选", Toast.LENGTH_SHORT).show();
                     //   v=View.inflate(getActivity(),R.layout.gouwuche_view,null);
                       CartFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_EMPTY);
                    } else {
                        Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                    }

                    return false;
                }
            });
        }
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
     fragment =new CartFragment();
    }

    @Override
    public void onClick(View v) {
        MainActivity.vp.setCurrentItem(0);
        RadioButton rb = (RadioButton) MainActivity.rg.getChildAt(0);
        rb.setChecked(true);
    }
}
