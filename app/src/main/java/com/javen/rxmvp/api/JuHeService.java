package com.javen.rxmvp.api;

import com.javen.rxmvp.bean.HttpJuHeResult;
import com.javen.rxmvp.bean.JuHeDream;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 请求示例：
 * http://v.juhe.cn/dream/query
 * q:梦境关键字，如：黄金 需要utf8 urlencode
 * cid:指定分类，默认全部
 * full: 是否显示详细信息，1:是 0:否，默认0
 */
public interface JuHeService {
    @GET("dream/query")
    Observable<HttpJuHeResult<List<JuHeDream>>> getDreams(@QueryMap Map<String, Object> options);
}