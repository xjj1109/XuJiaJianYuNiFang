package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

import bean.CartSqlBean;
import dao.Cart_Dao;
import text.bwie.asus.xujiajianyunifang.R;

/**
 * Created by asus on 2016/12/26.
 */

public class Mine_lv_Adapter extends BaseAdapter implements View.OnClickListener {
    private final Context context;
    private List<CartSqlBean> list;
    private final Adapter adapter;
    private TextView mine_lv_item_name;

    public Mine_lv_Adapter(Context context, List<CartSqlBean> list, Adapter adapter) {
        this.context = context;
        this.list = list;
        this.adapter =adapter;
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
        View v = View.inflate(context, R.layout.mine_lv_adapter_item, null);
        ImageView mine_lv_item_img = (ImageView) v.findViewById(R.id.mine_lv_item_img);
        mine_lv_item_name = (TextView) v.findViewById(R.id.mine_lv_item_name);
        TextView mine_lv_item_price = (TextView) v.findViewById(R.id.mine_lv_item_price);
        Button shanchus = (Button) v.findViewById(R.id.shanchus);
        shanchus.setOnClickListener(this);
        shanchus.setTag(position+"");
        ImageLoader.getInstance().displayImage(list.get(position).getName(),mine_lv_item_img);
        mine_lv_item_name.setText(list.get(position).getImg());
        mine_lv_item_price.setText("￥80.8");
        return v;
    }
    @Override
    public void onClick(View v) {
        String tag= (String) v.getTag();
        Cart_Dao dao =new Cart_Dao(context);
        ArrayList<CartSqlBean> select = dao.select();
        for (int i = 0; i <list.size() ; i++) {
            if(Integer.parseInt(tag)==i){
            dao.delete(list.get(i).getName());
            list.remove(list.get(i).getName());
            }
        }
        list=dao.select();
        Toast.makeText(context, "...."+list.size(), Toast.LENGTH_SHORT).show();
       this.notifyDataSetChanged();
    }

}
