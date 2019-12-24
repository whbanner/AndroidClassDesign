package com.example.classdesign.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="ClassDesign_db";
    //带全部参数的构造函数，此构造函数必不可少
    public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库sql语句 并 执行
        //两个属性 先创一个ContentValues 之后给相应 K，V赋值即可
        String sql = "create table login(username varchar(20),password varchar(20))";
        db.execSQL(sql);
        ContentValues values = new ContentValues();
        values.put("username","wh");
        values.put("password","123");
        //数据库执行插入命令
        db.insert("login", null, values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
