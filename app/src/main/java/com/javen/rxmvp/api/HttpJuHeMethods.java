package com.javen.rxmvp.api;

import com.javen.rxmvp.bean.HttpJuHeResult;
import com.javen.rxmvp.bean.JuHeDream;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HttpJuHeMethods {

    public static final String BASE_URL = "http://v.juhe.cn/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;

    private JuHeService juheService;

    //构造方法私有
    private HttpJuHeMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        juheService = retrofit.create(JuHeService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpJuHeMethods INSTANCE = new HttpJuHeMethods();
    }

    //获取单例
    public static HttpJuHeMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }


    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpJuHeResult<T>, T> {

        @Override
        public T call(HttpJuHeResult<T> httpResult) {
            if (httpResult.getError_code() != 0) {
                throw new ApiException(httpResult.getError_code());
            }
            return httpResult.getResult();
        }
    }

    private <T> void toSubscribe(Observable<T> observable, Subscriber<T> subscriber){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 用于获取聚合笑话的数据
     * @param subscriber 由调用者传过来的观察者对象
     * @param options 访问参数
     */
    public void getJokesByHttpResultMap(Subscriber<List<JuHeDream>> subscriber, Map<String, Object> options){
//        juheService.getJokesByRxJavaHttpResult(options)
//                .map(new HttpResultFunc<JuHeDream>())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
        Observable<List<JuHeDream>> observable = juheService.getDreams(options)
                .map(new HttpResultFunc<List<JuHeDream>>());
        toSubscribe(observable,subscriber);

    }
}