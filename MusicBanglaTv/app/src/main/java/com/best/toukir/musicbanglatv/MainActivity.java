package com.best.toukir.musicbanglatv;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LruCache<String,String> mCacheMemory;
    SharedPreferences sharedPreferences;
    EditText etName;
    TextView txtShowName;
    public static final String KEY_NAME = "name";
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("MyPref", Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",name);
        editor.commit();

        txtShowName = (TextView) findViewById(R.id.textView);
        etName = (EditText) findViewById(R.id.editText);

        final int maxMemorySize = (int) (Runtime.getRuntime().maxMemory()/1024);
        final int cachesSize = maxMemorySize/10;

        mCacheMemory = new LruCache<String, String>(cachesSize);

    }

    public void btnShow(View view) {

        name = etName.getText().toString();
        mCacheMemory.put(KEY_NAME,name);
        txtShowName.setText(mCacheMemory.get(KEY_NAME));
        txtShowName.setText(sharedPreferences.getString("name",null));
    }
}
