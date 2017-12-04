package com.example.huanpengling.myapplication.data.api;

import android.app.Application;
import java.net.HttpURLConnection;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.RetrofitErrorWrapper;
import retrofit.client.Response;
import retrofit.converter.Converter;
import timber.log.Timber;

/**
 * HTTP错误助手
 *
 * Created by RobX on 15/5/7.
 */
public final class ApiErrorHandler implements ErrorHandler {
  private static final int HTTP_UNPROCESSABLE_ENTITY = 422;

  private final Application app;
  private final Converter converter;

  public ApiErrorHandler(Application app, Converter converter) {
    this.app = app;
    this.converter = converter;
  }

  @Override public Throwable handleError(RetrofitError cause) {
    final RetrofitError.Kind kind = cause.getKind();
    final Response response = cause.getResponse();
    switch (kind) {
      case CONVERSION:
        if (response != null) {
          // 上报错误日志
          Timber.e(cause, "Failed to convert response body: %s", response.getUrl());
        }
        break;
      case HTTP:
        if (response != null) {
          final int status = response.getStatus();
          if (status >= HttpURLConnection.HTTP_BAD_REQUEST && status != HTTP_UNPROCESSABLE_ENTITY) {
            // 上报错误日志
            Timber.e(cause, "Failed to load url: %s", response.getUrl());
          }
          // 认证失效
          if (status == HttpURLConnection.HTTP_UNAUTHORIZED) {
            Timber.e(cause, "HTTP_UNAUTHORIZED", response.getUrl());
          }
        }
        return new RetrofitErrorWrapper(cause, converter);
      case NETWORK:
        break;
      case UNEXPECTED:
        // 上报错误日志
        Timber.e(cause, "Failed to unknown error: %s" + cause.getMessage());
        break;
      default:
        break;
    }
    return cause;
  }
}
