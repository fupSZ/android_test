package com.androidtest.fup.dextest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {
    private File dexFile;
    DexClassLoader mLoader;
    private TextView mTextView;
    private final static String ASSERT_PATH = "dexDir/fsdex.dex";
    private final static String CACHE_PATH = "/dexDirOut/fsdex.dex";
    private final static String OPTIMIZED_PATH = "/optimizedOut/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
    }

    private void initCopyDexTask() {
        LoadDexTask task = new LoadDexTask();
        task.execute();
    }

    public void onButtonClick(View view) {
        if(dexFile == null) {
            initCopyDexTask();
            Toast.makeText(this, "loading task...", Toast.LENGTH_SHORT).show();
            return;
        }
        DexUtils.mkdir(getFilesDir() + OPTIMIZED_PATH);
        mLoader = new DexClassLoader(getFilesDir() + CACHE_PATH, getFilesDir() + OPTIMIZED_PATH, null, getClassLoader());
        Class c;
        Class c1;
        try {
            c = mLoader.loadClass("com.fxiaoke.lib.pay.utils.MD5Util");
            //调用public方法
            Method method1 = c.getMethod("md5String", String.class);
            method1.setAccessible(true);
            String res = (String) method1.invoke(c.newInstance(), "hello world");
//            mTextView.setText(res);

            //获取静态成员
            Field field = c.getDeclaredField("hexDigits");
            field.setAccessible(true);
            String res1[] = (String[]) field.get(c.newInstance());
//            mTextView.setText(covertStr(res1));

            c1 = mLoader.loadClass("com.fxiaoke.lib.pay.bean.result.CommonResult");
            Field field1 = c1.getDeclaredField("errorMessage");
            JSONField annotation = field1.getAnnotation(JSONField.class);
            boolean a = annotation.serialize();

            mTextView.setText("" + a);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String covertStr(String[] strings) {
        String res = "";
        for(String str : strings) {
            res += str;
        }
        return res;
    }


    class LoadDexTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            dexFile = DexUtils.copyAssert2Cache(MainActivity.this, ASSERT_PATH, DexUtils.getDexPath(MainActivity.this, CACHE_PATH));
            return null;
        }
    }
}
