package text.bwie.asus.xujiajianyunifang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import fragment.MineFragment;

public class Settingss extends FragmentActivity {
    private ListView setting_lv;
    private ListView setting_lv2;
    private ImageView setting_img;
    private RelativeLayout rl3;
    private TextView nzhao;
    private File cacheDir;
    public static Button setting_btn;
    private RelativeLayout about_our;
    private ImageView ss_img;
    private ImageView category_back_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingss);
        about_our = (RelativeLayout) findViewById(R.id.about_our);
        setting_lv = (ListView) findViewById(R.id.setting_lv);
        setting_lv2 = (ListView) findViewById(R.id.setting_lv2);
        setting_img = (ImageView) findViewById(R.id.setting_img);
        category_back_img = (ImageView) findViewById(R.id.category_back_img);
//        清除缓存
        rl3 = (RelativeLayout) findViewById(R.id.rl3);
        nzhao = (TextView) findViewById(R.id.nzhao);
        ss_img = (ImageView) findViewById(R.id.ss_img);
        cacheDir = getCacheDir();
//           生成二维码
        about_our.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*   Bitmap qrBitmap = generateBitmap("徐嘉健",400, 400);
              Two_Code.two_code_img.setImageBitmap(qrBitmap);*/
                Intent intent = new Intent(Settingss.this, Two_Code.class);
                startActivity(intent);

            }
        });

//        获取当前缓存大小
        long cacheSize = getCacheSize(cacheDir);
        nzhao.setText(cacheSize + "Kb");
//退出
        setting_btn = (Button) findViewById(R.id.setting_btn);
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Settingss.this, "sss", Toast.LENGTH_SHORT).show();
                MineFragment.login.setText("登陆");
                MineFragment.touxiang.setImageResource(R.mipmap.a1);
                finish();
            }
        });
        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeletecacheDir(cacheDir);
                long cacheSize = getCacheSize(cacheDir);
                nzhao.setText(cacheSize + "Kb");
                Toast.makeText(Settingss.this, "清除成功", Toast.LENGTH_SHORT).show();
            }
        });
        getData();
        getData2();
        getPic();
        getPic2();
        setting_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setting_lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return datalist.size();
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
                View v = View.inflate(Settingss.this, R.layout.setting_lv_item, null);
                TextView setting_item_tv = (TextView) v.findViewById(R.id.setting_item_tv);
                ImageView setting_item_img = (ImageView) v.findViewById(R.id.setting_item_img);
                setting_item_tv.setText(datalist.get(position));
                setting_item_img.setImageResource(piclist.get(position));
                return v;
            }
        });
        setting_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Settingss.this, datalist.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        setting_lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);

                    Uri data = Uri.parse("tel:" + "18801273965");

                    intent.setData(data);

                    startActivity(intent);
                } else {
                    Toast.makeText(Settingss.this, "已是最新版本", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setting_lv2.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return datalist2.size();
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
                View v = View.inflate(Settingss.this, R.layout.settings_item_lv2, null);
                TextView setting_item2_tv = (TextView) v.findViewById(R.id.setting_item2_tv);
                TextView setting_item3_tv = (TextView) v.findViewById(R.id.setting_item3_tv);
                setting_item2_tv.setText(datalist2.get(position));
                setting_item3_tv.setText(piclist2.get(position));
                return v;
            }
        });

    }

    private void DeletecacheDir(File dir) {
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                files[i].delete();
            } else {
                // 该文件夹为空
                if (files[i].listFiles().length == 0) {
                    files[i].delete();
                } else {
//                    遍历该文件夹
                    DeletecacheDir(files[i]);
                }
            }

        }
    }

    //计算文件大小
    public String getFormatSize(long cachesize) {
        long size = cachesize / 1024;
        if (size < 1) {
            return size + "Byte";
        }
        long ksize = size / 1024;
        if (ksize < 1) {
            return ksize + "Kb";
        }
        return null;
    }


    private long getCacheSize(File dir) {
        long size = 0;
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                size = size + getCacheSize(files[i]);
            }//当前是文件
            else {
                size = size + files[i].length();
            }
        }
        return size;
    }

    ArrayList<String> piclist2 = new ArrayList<>();

    private void getPic2() {
        piclist2.add("10086");
        piclist2.add("已是最新版本");
    }

    ArrayList<String> datalist2 = new ArrayList<>();

    private void getData2() {
        datalist2.add("拨打电话");
        datalist2.add("检查更新");

    }

    ArrayList<Integer> piclist = new ArrayList<>();

    private void getPic() {
        piclist.add(R.mipmap.next_icon);
        piclist.add(R.mipmap.next_icon);
    }

    ArrayList<String> datalist = new ArrayList<>();

    private void getData() {
        datalist.add("购物须知");
        datalist.add("意见反馈");
    }

}
