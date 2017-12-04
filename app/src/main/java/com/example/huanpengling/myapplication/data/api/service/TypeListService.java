package com.example.huanpengling.myapplication.data.api.service;

import com.example.huanpengling.myapplication.data.api.model.TypeBean;
import retrofit.http.GET;
import retrofit.http.Headers;
import rx.Observable;

public interface TypeListService {

  /**
   * 获取类型列表
   */
  @Headers("apikey:4e2b3d9210492a01282154bc808cf7c0") @GET("/channel_news")
  Observable<TypeBean> reqProductList();
}
