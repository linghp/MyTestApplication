package com.example.huanpengling.myapplication.data.provider;

import android.app.Application;
import retrofit.CashAdapter;

/**
 * 抽象数据提供者，生成各种服务
 *
 * Created by RobX on 15/4/21.
 */
public abstract class AbstractProvider<T> {
  protected final Application app;
  protected final T service;

  public AbstractProvider(Application app, CashAdapter adapter, Class<T> cls) {
    this.app = app;
    this.service = adapter.create(cls);
  }
}
