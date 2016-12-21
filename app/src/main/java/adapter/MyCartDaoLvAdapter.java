package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import bean.CartSqlBean;
import dao.Cart_Dao;
import fragment.CartFragment;
import text.bwie.asus.xujiajianyunifang.R;

/**
 * Created by asus on 2016/12/10.
 */
public class MyCartDaoLvAdapter extends BaseAdapter implements View.OnClickListener {
    private final Context context;
    private final ArrayList<CartSqlBean> select;
    public static CheckBox gouwu_che;
        int tag=0;

    public MyCartDaoLvAdapter(Context context , ArrayList<CartSqlBean> select) {
        this.context=context;
        this.select=select;
    }

    @Override
    public int getCount() {
        return select.size();
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
        View v=View.inflate(context, R.layout.cart_dao_lv_item,null);
        ImageView cart_dao_lv_item_img = (ImageView) v.findViewById(R.id.cart_dao_lv_item_img);
        TextView cart_dao_lv_item_name = (TextView) v.findViewById(R.id.cart_dao_lv_item_name);
        gouwu_che = (CheckBox) v.findViewById(R.id.gouwu_che);
        gouwu_che.setFocusable(false);
        gouwu_che.setChecked(select.get(position).isFlag());
        gouwu_che.setId(position);
        gouwu_che.setOnClickListener(this);
        ImageLoader.getInstance().displayImage(select.get(position).getName(),cart_dao_lv_item_img);
        cart_dao_lv_item_name.setText(select.get(position).getImg());
//        全选
        CartFragment .quanxuan_btn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "全选", Toast.LENGTH_SHORT).show();
                for (int i = 0; i <select.size() ; i++) {
                    tag++;
                    gouwu_che.isChecked();
                    select.get(i).setFlag(true);
                    CartFragment.delete_cart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Cart_Dao dao=new Cart_Dao(context);
                            for (int i = 0; i < select.size(); i++) {
                                if(select.get(i).isFlag()){
                                    dao.delete(select.get(i).getName());
                                    select.remove(i);
                                }
                            }
                        }
                    });
                 CartFragment .delete_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "全部删除", Toast.LENGTH_SHORT).show();
                        Cart_Dao dao=new Cart_Dao(context);
                        for (int i = 0; i < MyCartDaoLvAdapter.this.select.size() ; i++) {
                           dao.delete(MyCartDaoLvAdapter.this.select.get(i).getName());
                            MyCartDaoLvAdapter.this.select.removeAll(MyCartDaoLvAdapter.this.select);
                            MyCartDaoLvAdapter.this.notifyDataSetChanged();
           
                        }
                    }
                });
                    MyCartDaoLvAdapter.this.notifyDataSetChanged();
                }
            }
        });
//        反选
        CartFragment .fanxuan_btn. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "反选", Toast.LENGTH_SHORT).show();
                for (int i = 0; i <select.size() ; i++) {
                    tag--;
                    select.get(i).setFlag(false);
                    MyCartDaoLvAdapter.this.notifyDataSetChanged();
                }
            }
        });
        return v;
    }

    @Override
    public void onClick(View v) {
       select.get(v.getId()).setFlag(true);
        CartFragment.delete_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart_Dao dao=new Cart_Dao(context);
                for (int i = 0; i < select.size(); i++) {
                    if(select.get(i).isFlag()){
                        dao.delete(select.get(i).getName());
                    select.remove(i);
                    }
                    MyCartDaoLvAdapter.this.notifyDataSetChanged();
                    if(select.size()==0){
                       Toast.makeText(context, "请到商城中选购", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });

    }
}
