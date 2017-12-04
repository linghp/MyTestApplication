package com.example.huanpengling.myapplication.data.api;

import com.example.huanpengling.myapplication.BuildConfig;
import retrofit.RestAdapter;
import timber.log.Timber;

/**
 * Retrofit 日志
 *
 * Created by RobX on 15/5/7.
 */
public final class RetrofitLog implements RestAdapter.Log {

  @Override public void log(String message) {
    if (BuildConfig.DEBUG) Timber.i(message);
  }
}
