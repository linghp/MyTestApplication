package com.example.huanpengling.myapplication.data.provider;

import android.app.Application;
import com.example.huanpengling.myapplication.data.api.model.TypeBean;
import com.example.huanpengling.myapplication.data.api.service.TypeListService;
import retrofit.CashAdapter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public final class TypeListProvider extends AbstractProvider<TypeListService> {

  public TypeListProvider(Application app, CashAdapter adapter) {
    super(app, adapter, TypeListService.class);
  }

  public Observable<TypeBean> getType() {
    return service.reqProductList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
