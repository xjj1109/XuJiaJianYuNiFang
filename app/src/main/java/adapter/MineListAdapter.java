package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import text.bwie.asus.xujiajianyunifang.Personal_Information;
import text.bwie.asus.xujiajianyunifang.R;

/**
 * Created by asus on 2016/11/29.
 * 我的适配器
 */


public class MineListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private final List<String> list;
    private final List<Integer> list2;
    private final ListView lv;
    private Context context;
    private boolean flag = false;
    private TextView tv;
    private final ArrayList<String> list111;
    private final ArrayList<String> list222;

    public MineListAdapter(Context context, List<String> list, List<Integer> list2, ListView lv) {
        this.context = context;
        this.list = list;
        this.list2 = list2;
        this.lv = lv;
        list111 = new ArrayList<>();
        list111.add("NEW");
        list222 = new ArrayList<>();
        list222.add("+2000");
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
        View v = View.inflate(context, R.layout.minefragment_lv_item, null);
        ImageView mine_fragment_img = (ImageView) v.findViewById(R.id.mine_fragment_img);
        TextView mine_fragment_tv = (TextView) v.findViewById(R.id.mine_fragment_tv);
        tv = (TextView) v.findViewById(R.id.mine_fragment_tv2);
        TextView tv2 = (TextView) v.findViewById(R.id.mine_fragment_tv3);
        mine_fragment_tv.setText(list.get(position));
        mine_fragment_img.setImageResource(list2.get(position));
        lv.setOnItemClickListener(this);
        lv.setFocusable(true);
        if (list111.size() > 0) {
            if (position == 2) {
                tv.setText("New");
                tv.setVisibility(View.VISIBLE);
            } else {
                tv.setVisibility(View.GONE);
            }
        }
        if (list222.size() > 0) {

            if (position == 3) {
                tv2.setText("+2000");
                tv2.setVisibility(View.VISIBLE);
            } else {
                tv2.setVisibility(View.GONE);
            }
        }
        return v;
    }

    //ListView监听
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(context, list.get(position), Toast.LENGTH_SHORT).show();
        //tv.setVisibility(View.GONE);
        //tv.setText("New2222");
        if (position == 2) {
            list111.clear();
        }
        if (position == 3) {
            list222.clear();
        }
        MineListAdapter.this.notifyDataSetChanged();
//        跳转详情页
        Intent intent = new Intent(context, Personal_Information.class);
        context.startActivity(intent);
    }
}
