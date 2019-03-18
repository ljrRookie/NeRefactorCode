package com.netease.nerefactorcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String url = "http://v.juhe.cn/historyWeather/citys";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tvHW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> params = new HashMap<>();
                params.put("province_id",1);
                params.put("key","bb52107206585ab074f5e59a8c73875b");
                HttpHelper.getInstance().post(url, params, new HttpCallBack<ReponseClass>() {
                    @Override
                    public void onSuccess(ReponseClass objResult) {
                        Toast.makeText(MainActivity.this,objResult.getReason()+" "+objResult.getError_code(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
