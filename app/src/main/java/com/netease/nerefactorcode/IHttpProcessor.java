package com.netease.nerefactorcode;

import java.util.Map;

public interface IHttpProcessor {

    // 该类相当于房产公司 拥有买房能力 对应网络请求，具有访问网络的能力
    void post(String url, Map<String,Object> params,ICallBack callBack);

}
