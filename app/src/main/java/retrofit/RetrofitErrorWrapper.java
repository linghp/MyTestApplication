package retrofit;

import java.lang.reflect.Type;
import retrofit.client.Response;
import retrofit.converter.Converter;

/**
 * 包装 {@link RetrofitError}，解析API返回的错误信息
 *
 * Created by RobX on 15/6/9.
 */
public final class RetrofitErrorWrapper extends RetrofitError {
  private Error error;

  public RetrofitErrorWrapper(RetrofitError error, Converter converter) {
    this(error.getMessage(), error.getUrl(), error.getResponse(), converter, error.getSuccessType(),
        error.getKind(), error.getCause());
  }

  protected RetrofitErrorWrapper(String message, String url, Response response, Converter converter,
      Type successType, Kind kind, Throwable exception) {
    super(message, url, response, converter, successType, kind, exception);
    if (kind == Kind.HTTP) {
      try {
        error = (Error) getBodyAs(Error.class);
      } catch (Exception e) {
        e.printStackTrace();
        // ignore
      }
    }
  }

  public Error getError() {
    return error;
  }
}
