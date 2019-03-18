package com.netease.nerefactorcode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class HttpHelper implements IHttpProcessor{

    private static HttpHelper instance;

    private HttpHelper(){}

    public static HttpHelper getInstance(){
        synchronized (HttpHelper.class){
            if(instance == null){
                instance = new HttpHelper();
            }
        }
        return instance;
    }

    // 需要持有一个有房的人，针对这个例子，相当于持有某个第三方网络访问框架
    private static IHttpProcessor mIHttpProcessor;
    public static void init(IHttpProcessor httpProcessor){
        mIHttpProcessor = httpProcessor;
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {
        String finalUrl = appendParams(url,params);
        mIHttpProcessor.post(finalUrl,params,callBack);
    }

    public static String appendParams(String url,Map<String,Object> params){
        if(params == null || params.isEmpty()){
            return url;
        }
        StringBuilder urlBuilder = new StringBuilder(url);
        if(urlBuilder.indexOf("?") <= 0 ){
            urlBuilder.append("?");
        }else{
            if(!urlBuilder.toString().endsWith("?")){
                urlBuilder.append("&");
            }
        }
        for(Map.Entry<String,Object> entry:params.entrySet()){
            urlBuilder.append("&"+entry.getKey()).append("=").append(encode(entry.getValue().toString()));
        }
        return urlBuilder.toString();
    }

    private static String encode(String str){
        try {
            return URLEncoder.encode(str,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
