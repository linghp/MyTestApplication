package com.example.huanpengling.myapplication.data.api.service;

import com.example.huanpengling.myapplication.data.api.model.Detail;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

public interface DetailListService {

  /**
   * 获取详细列表
   */
  @Headers("apikey:4e2b3d9210492a01282154bc808cf7c0") @GET("/search_news")
  Observable<Detail> reqDetailList(@Query("channelId") String platform);
}
