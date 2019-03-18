package com.netease.nerefactorcode;

import android.os.Handler;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpProcessor implements IHttpProcessor{

    private OkHttpClient mOkHttpClient;
    private Handler myHander;

    public OkHttpProcessor(){
        mOkHttpClient = new OkHttpClient();
        myHander = new Handler();
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callBack) {
        RequestBody requestBody = appendBody(params);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String str = response.body().string();
                    myHander.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(str);
                        }
                    });
                }
            }
        });
    }

    private RequestBody appendBody(Map<String, Object> params){
        FormBody.Builder body = new FormBody.Builder();
        if(params == null || params.isEmpty()){
            return body.build();
        }
        for (Map.Entry<String,Object> entry : params.entrySet()){
            body.add(entry.getKey(),entry.getValue().toString());
        }
        return body.build();
    }

}
