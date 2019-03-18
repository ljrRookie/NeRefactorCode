package com.netease.nerefactorcode;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HttpCallBack<Result> implements ICallBack{

    @Override
    public void onSuccess(String result) {
        // 将网络访问框架得到的数据转换成JSON对象
        Gson gson = new Gson();
        Class<?> clz = analysisClassInfo(this);
        Result objResult = (Result)gson.fromJson(result,clz);
        onSuccess(objResult);
    }

    private Class<?> analysisClassInfo(Object obj){
        // 可以得到包含原始类型，参数化类型，类型变量，基本类型
        Type genType = obj.getClass().getGenericSuperclass();
        Type[] actualTypeArguments =((ParameterizedType)genType).getActualTypeArguments();
        return (Class<?>)actualTypeArguments[0];
    }

    public abstract void onSuccess(Result objResult);

    @Override
    public void onFailure() {

    }

}
