package com.example.huanpengling.myapplication.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Hashtable;

/**
 * @author micro4code@163.com
 * @code: create：9/18/15
 * class: com.google.zxing.client.android.encode.QRCodeUtil
 */
public class QRCodeUtil {
  private static final String CODE = "utf-8";
  private static final int BLACK = 0xff000000;
  private static final int WHITE = 0xFFFFFFFF;

  /**
   * 生成一维码（128）
   *
   * @author wuhongbo
   */
  public static Bitmap getBarcode(String str, Integer width, Integer height) {

    if (width == null || width < 200) {
      width = 200;
    }

    if (height == null || height < 50) {
      height = 50;
    }

    try {
      // 文字编码
      Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
      hints.put(EncodeHintType.CHARACTER_SET, CODE);

      BitMatrix bitMatrix =
          new MultiFormatWriter().encode(str, BarcodeFormat.CODE_128, width, height, hints);

      return toBitmap(bitMatrix);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static Bitmap toBitmap(BitMatrix bitMatrix) {
    int width = bitMatrix.getWidth();
    int height = bitMatrix.getHeight();
    int[] pixels = new int[width * height];
    for (int y = 0; y < height; y++) {
      int offset = y * width;
      for (int x = 0; x < width; x++) {
        pixels[offset + x] = bitMatrix.get(x, y) ? BLACK : WHITE;
      }
    }

    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
    return bitmap;
  }

  /**
   * 生成图片,并添加logo
   */
  public static Bitmap createImage(String text, int w, int h, Bitmap logo) {
    try {
      Bitmap scaleLogo = getScaleLogo(logo, w, h);
      int offsetX = 0;
      int offsetY = 0;
      if (scaleLogo != null) {
        offsetX = (w - scaleLogo.getWidth()) / 2;
        offsetY = (h - scaleLogo.getHeight()) / 2;
      }
      Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
      hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
      BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, w, h, hints);
      int[] pixels = new int[w * h];
      for (int y = 0; y < h; y++) {
        for (int x = 0; x < w; x++) {
          //判断是否在logo图片中
          if (offsetX != 0
              && offsetY != 0
              && x >= offsetX
              && x < offsetX + scaleLogo.getWidth()
              && y >= offsetY
              && y < offsetY + scaleLogo.getHeight()) {
            int pixel = scaleLogo.getPixel(x - offsetX, y - offsetY);
            //如果logo像素是透明则写入二维码信息
            if (pixel == 0) {
              if (bitMatrix.get(x, y)) {
                pixel = 0xff000000;
              } else {
                pixel = 0xffffffff;
              }
            }
            pixels[y * w + x] = pixel;
          } else {
            if (bitMatrix.get(x, y)) {
              pixels[y * w + x] = 0xff000000;
            } else {
              pixels[y * w + x] = 0xffffffff;
            }
          }
        }
      }
      Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
      bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
      return bitmap;
    } catch (WriterException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 缩放logo到二维码的1/5
   */
  private static Bitmap getScaleLogo(Bitmap logo, int w, int h) {
    if (logo == null) return null;
    Matrix matrix = new Matrix();
    float scaleFactor = Math.min(w * 1.0f / 5 / logo.getWidth(), h * 1.0f / 5 / logo.getHeight());
    matrix.postScale(scaleFactor, scaleFactor);
    Bitmap result =
        Bitmap.createBitmap(logo, 0, 0, logo.getWidth(), logo.getHeight(), matrix, true);
    return result;
  }
}
