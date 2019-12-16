package com.example.classdesign.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classdesign.Dao.DatabaseHelper;
import com.example.classdesign.R;

import java.security.PrivateKey;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mlogin;
    private Button mlogin_clear;
    private String check="用户名或密码错误";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题 myapplication
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        mUsername=(EditText) findViewById(R.id.login_username);
        mPassword=(EditText)findViewById(R.id.login_password);
        mlogin=(Button) findViewById(R.id.login_login);
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=mUsername.getText().toString();
                String password=mPassword.getText().toString();
                //依靠DatabaseHelper的构造函数创建数据库
                DatabaseHelper dbHelper = new DatabaseHelper(LoginActivity.this, null,null,1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("login", new String[]{"username","password"}, null, null, null, null, null);
//                利用游标遍历所有数据对象
                while(cursor.moveToNext()){
                    String username2 = cursor.getString(cursor.getColumnIndex("username"));
                    String password2 = cursor.getString(cursor.getColumnIndex("password"));
                    if (username.equals(username)&&password.equals(password2)){
                        check="正确";
                        db.close();
                    }
                }
                // 关闭游标，释放资源
                cursor.close();

                Toast.makeText(LoginActivity.this,check,Toast.LENGTH_LONG).show();
                //初始化
                check="用户名或密码错误";
//                Intent i = new Intent(LoginActivity.this,)

            }
        });
        mlogin_clear=(Button) findViewById(R.id.login_clear);
        mlogin_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}
