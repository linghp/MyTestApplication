package com.example.huanpengling.myapplication.data.provider;

import android.app.Application;
import com.example.huanpengling.myapplication.data.api.model.Detail;
import com.example.huanpengling.myapplication.data.api.service.DetailListService;
import retrofit.CashAdapter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public final class DetailListProvider extends AbstractProvider<DetailListService> {

  public DetailListProvider(Application app, CashAdapter adapter) {
    super(app, adapter, DetailListService.class);
  }

  public Observable<Detail> getDetailList(String id) {
    return service.reqDetailList("")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
