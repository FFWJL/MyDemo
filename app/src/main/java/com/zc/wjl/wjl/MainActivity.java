package com.zc.wjl.wjl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zc.wjl.wjl.adapterdesignpattern.Output220Volt;
import com.zc.wjl.wjl.adapterdesignpattern.VoltClassAdapter;
import com.zc.wjl.wjl.adapterdesignpattern.VoltObjAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        VoltObjAdapter voltObjAdapter = new VoltObjAdapter(new Output220Volt());
//        Toast.makeText(this, "对象适配器模式，结果===" + voltObjAdapter.output5V(), Toast.LENGTH_SHORT).show();
        VoltClassAdapter voltClassAdapter = new VoltClassAdapter();
        Toast.makeText(this, "类适配器模式，结果===" + voltClassAdapter.output5V(), Toast.LENGTH_SHORT).show();
    }
}
