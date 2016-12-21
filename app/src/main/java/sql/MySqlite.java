package sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus on 2016/12/10.
 */
public class MySqlite extends SQLiteOpenHelper {
    public  MySqlite (Context context){
        super(context, "bawei", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  xinxi(name varchar(20),img varchar(20), price varchar(20),old_price varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
