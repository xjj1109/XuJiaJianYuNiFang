package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import bean.CartSqlBean;
import sql.MySqlite;

/**
 * Created by asus on 2016/12/10.
 */
public class Cart_Dao {

    private final MySqlite mySqlite;

    public Cart_Dao(Context context) {
        mySqlite = new MySqlite(context);
    }

    public void add(String name, String price, String img, String old_price) {
        SQLiteDatabase db = mySqlite.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from xinxi where name = ?", new String[]{name});
        if (cursor.moveToNext()) {
            return;
        }
        db.execSQL("insert into xinxi(name, price ,img,old_price) values(?,?,?,?)", new Object[]{
                name, price, img, old_price
        });
        db.close();
    }

    public void delete(String name) {
        SQLiteDatabase db = mySqlite.getWritableDatabase();
        db.execSQL("delete from xinxi where name=?", new String[]{name});
        db.close();

    }

    ArrayList<CartSqlBean> list = new ArrayList<>();

    public ArrayList<CartSqlBean> select() {
        SQLiteDatabase db = mySqlite.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from xinxi", null);
        list.clear();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String img = cursor.getString(cursor.getColumnIndex("img"));
            String old_price = cursor.getString(cursor.getColumnIndex("old_price"));
            list.add(new CartSqlBean(name, price, img, old_price));
        }
        return list;
    }

}
