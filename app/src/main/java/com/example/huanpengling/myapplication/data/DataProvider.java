package com.example.huanpengling.myapplication.data;

import android.app.Application;
import android.net.Uri;
import com.example.huanpengling.myapplication.BuildConfig;
import com.example.huanpengling.myapplication.CashApp;
import com.example.huanpengling.myapplication.data.api.ApiErrorHandler;
import com.example.huanpengling.myapplication.data.api.RetrofitLog;
import com.example.huanpengling.myapplication.data.provider.AbstractProvider;
import com.example.huanpengling.myapplication.data.provider.DetailListProvider;
import com.example.huanpengling.myapplication.data.provider.TypeListProvider;
import com.example.huanpengling.myapplication.net.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import retrofit.CashAdapter;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import timber.log.Timber;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 数据提供者
 *
 * Created by RobX on 15/4/21.
 */
public final class DataProvider {
  /**
   * 首页两次刷新的时间间隔
   */
  public static final long REFRESH_SPACE_MAX_TIME = 2 * 60;

  private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
  private final CashApp app;
  private final CashAdapter restAdapter;
  private final Picasso picasso;
  private final Gson gson;

  public DataProvider(CashApp app) {
    this.app = app;
    final OkHttpClient client = createOkHttpClient(app);
    picasso = createPicasso(app, client);
    final GsonBuilder builder = new GsonBuilder();
    gson = builder.create();

    restAdapter = createRestAdapter(app, client, gson);
  }

  public Gson getGson() {
    return gson;
  }

  /**
   * 返回 {@link Picasso} 图片加载器
   */
  public Picasso getPicasso() {
    return picasso;
  }

  WeakReference appProviderWeakRef;
  WeakReference appProviderWeakRef2;

  /**
   * 返回种类列表提供者
   */
  public TypeListProvider getTypeListProvider() {
    return createProvider(appProviderWeakRef, app, restAdapter, TypeListProvider.class);
  }

  /**
   * 返回详细列表提供者
   */
  public DetailListProvider getDetailListProvider() {
    return createProvider(appProviderWeakRef2, app, restAdapter, DetailListProvider.class);
  }

  /**
   * 返回提供者
   */
  private static <T extends AbstractProvider<?>> T createProvider(WeakReference<T> weakRef,
      Application app, CashAdapter adapter, Class<T> cls) {
    weakRef = createProviderWeakRef(weakRef, app, adapter, cls);
    return weakRef.get();
  }

  /**
   * 创建并返回弱引用的提供者
   */
  private static <T extends AbstractProvider<?>> WeakReference<T> createProviderWeakRef(
      WeakReference<T> weakRef, Application app, CashAdapter adapter, Class<T> cls) {
    if (weakRef == null) {
      T provider = createProvider(app, adapter, cls);
      weakRef = new WeakReference<>(provider);
    } else {
      T provider = weakRef.get();
      if (provider == null) {
        provider = createProvider(app, adapter, cls);
        weakRef = new WeakReference<>(provider);
      }
    }
    return weakRef;
  }

  /**
   * 创建并返回抽象的提供者
   */
  private static <T extends AbstractProvider<?>> T createProvider(Application app,
      CashAdapter adapter, Class<T> cls) {
    T provider = null;
    try {
      Constructor<T> constructor = cls.getConstructor(Application.class, CashAdapter.class);
      provider = constructor.newInstance(app, adapter);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return provider;
  }

  /**
   * 创建并返回 {@link CashAdapter}
   */
  private static CashAdapter createRestAdapter(Application app, OkHttpClient client, Gson gson) {
    String endpoint = Config.BASE_URL;
    Converter converter = new GsonConverter(gson);
    return new CashAdapter.Builder().setEndpoint(endpoint)
        .setClient(new OkClient(client))
        .setConverter(converter)
        .setLogLevel(BuildConfig.DEBUG ? CashAdapter.LogLevel.FULL : CashAdapter.LogLevel.NONE)
        .setLog(new RetrofitLog())
        .setErrorHandler(new ApiErrorHandler(app, converter))
        .build();
  }

  /**
   * 创建并返回 {@link Picasso}
   */
  private static Picasso createPicasso(Application app, OkHttpClient client) {
    return new Picasso.Builder(app).downloader(new OkHttpDownloader(client))
        .listener(new Picasso.Listener() {
          @Override public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
            Timber.e(e, "Failed to load image: %s", uri);
          }
        })
        .build();
  }

  /**
   * 创建并返回 {@link OkHttpClient}
   */
  private static OkHttpClient createOkHttpClient(Application app) {
    OkHttpClient client = new OkHttpClient();
    client.setConnectTimeout(15, SECONDS);
    client.setReadTimeout(15, SECONDS);
    client.setWriteTimeout(15, SECONDS);

    // Install an HTTP cache in the application cache directory.
    File cacheDir = new File(app.getCacheDir(), "http");
    Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
    client.setCache(cache);
    return client;
  }
}
