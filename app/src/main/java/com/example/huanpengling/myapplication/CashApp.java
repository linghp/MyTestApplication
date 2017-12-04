package com.example.huanpengling.myapplication;

import android.app.Application;
import android.content.Context;
import com.example.huanpengling.myapplication.data.DataProvider;
import timber.log.Timber;

/**
 * 现金贷应用
 *
 * Created by RobX on 15/4/12.
 */
public final class CashApp extends Application {
  private DataProvider dataProvider;

  @Override public void onCreate() {
    super.onCreate();
    dataProvider = new DataProvider(this);
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  /**
   * 根据上下文引用 {@link Context} 返回 {@link CashApp}
   */
  public static CashApp get(Context context) {
    return (CashApp) context.getApplicationContext();
  }

  public DataProvider getDataProvider() {
    return dataProvider;
  }
}
