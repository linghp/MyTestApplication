package com.example.huanpengling.myapplication.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

  /**
   * 跳转页面，动画效果
   *
   * @param curActivity 当前活动
   * @param targetActivity 目标活动
   * @param finish 是否结束当前活动
   */
  public static void gotoActivity(Activity curActivity, Class<?> targetActivity, boolean finish) {
    Intent intent = new Intent();
    intent.setClass(curActivity, targetActivity);
    curActivity.startActivity(intent);
    //		curActivity.overridePendingTransition(R.anim.slide_left_in,
    //				R.anim.slide_left_out);

    if (finish) {
      curActivity.finish();
    }
  }

  /**
   * 跳转页面--带数据传递
   *
   * @param curActivity 当前活动
   * @param targetActivity 目标活动
   * @param bundle 需要传递的数据
   * @param finish 是否结束当前活动
   */
  public static void gotoActivityWithData(Activity curActivity, Class<?> targetActivity,
      Bundle bundle, boolean finish) {
    Intent intent = new Intent();
    intent.setClass(curActivity, targetActivity);
    intent.putExtras(bundle);
    curActivity.startActivity(intent);
    //		curActivity.overridePendingTransition(R.anim.slide_left_in,
    //				R.anim.slide_left_out);

    if (finish) {
      curActivity.finish();
    }
  }

  /**
   * 跳转页面--带数据传递
   *
   * @param curActivity 当前活动
   * @param targetActivity 目标活动
   * @param bundle 需要传递的数据
   * @param finish 是否结束当前活动
   */
  public static void gotoActivityWithDataForResult(Activity curActivity, Class<?> targetActivity,
      Bundle bundle, int RequestCode, boolean finish) {
    Intent intent = new Intent();
    intent.setClass(curActivity, targetActivity);
    intent.putExtras(bundle);
    curActivity.startActivityForResult(intent, RequestCode);
    //		curActivity.overridePendingTransition(R.anim.slide_left_in,
    //				R.anim.slide_left_out);

    if (finish) {
      curActivity.finish();
    }
  }

  /**
   * 跳转页面，动画效果
   *
   * @param fragment 当前fragment
   * @param targetActivity 目标活动
   * @param Code int 类型数据
   * @param finish 是否结束当前活动
   */
  public static void gotoActivityForResult_fragment(Fragment fragment, Class<?> targetActivity,
      int Code, boolean finish) {
    Intent intent = new Intent();
    intent.setClass(fragment.getActivity(), targetActivity);
    fragment.startActivityForResult(intent, Code);
    //		curActivity.overridePendingTransition(R.anim.slide_left_in,
    //				R.anim.slide_left_out);

    if (finish) {
      fragment.getActivity().finish();
    }
  }

  /**
   * 跳转页面，动画效果
   *
   * @param curActivity 当前活动
   * @param targetActivity 目标活动
   * @param Code int 类型数据
   * @param finish 是否结束当前活动
   */
  public static void gotoActivityForResult(Activity curActivity, Class<?> targetActivity, int Code,
      boolean finish) {
    Intent intent = new Intent();
    intent.setClass(curActivity, targetActivity);
    curActivity.startActivityForResult(intent, Code);
    //		curActivity.overridePendingTransition(R.anim.slide_left_in,
    //				R.anim.slide_left_out);

    if (finish) {
      curActivity.finish();
    }
  }

  /**
   * 跳转页面--结束中间活动
   *
   * @param curActivity 当前活动
   * @param targetActivity 目标活动
   * @param refresh 是否刷新要跳转界面
   */
  public static void gotoActivityWithFinishOtherAll(Activity curActivity, Class<?> targetActivity,
      boolean refresh) {
    Intent intent = new Intent();
    intent.setClass(curActivity, targetActivity);
    if (!refresh) {
      intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);// 设置不要刷新将要跳到的界面
    }
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// 它可以关掉所要到的界面中间的activity
    curActivity.startActivity(intent);
    //		curActivity.overridePendingTransition(R.anim.slide_left_in,
    //				R.anim.slide_left_out);
  }

  /**
   * dp转换为px
   */
  public static int dip2px(Context context, float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    //MyLogger.i(scale+"");
    return (int) (dpValue * scale + 0.5f);
  }

  /**
   * px装换成dp
   */
  public static int px2dip(Context context, float pxValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  /**
   * sp转换为px
   */
  public static int sp2px(Context context, float spValue) {
    final float scale = context.getResources().getDisplayMetrics().scaledDensity;
    return (int) (spValue * scale + 0.5f);
  }

  /**
   * px转换为sp
   */
  public static int px2sp(Context context, float pxValue) {
    final float scale = context.getResources().getDisplayMetrics().scaledDensity;
    return (int) (pxValue / scale + 0.5f);
  }

  /**
   * 验证字符串是否为网址
   */
  public static boolean isUrl(String url) {
    String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    Pattern patt = Pattern.compile(regex);
    Matcher matcher = patt.matcher(url);
    return matcher.matches();
  }

  /**
   * 验证字符串是否为数字
   */
  public static boolean isNumeric(String url) {
    Pattern patt = Pattern.compile("[0-9]*");
    Matcher matcher = patt.matcher(url);
    return matcher.matches();
  }

  public static boolean isTablet(Activity activity) {
    DisplayMetrics dm = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
    double diagonalPixels = Math.sqrt(Math.pow(dm.widthPixels, 2) + Math.pow(dm.heightPixels, 2));
    double physicalSize = diagonalPixels / (160 * dm.density);
    return physicalSize > 7;
  }

  /**
   * 设置margins
   */
  public static void setMargins(View v, int l, int t, int r, int b) {
    if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
      ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
      p.setMargins(l, t, r, b);
      v.requestLayout();
    }
  }

  public static void toast(String str, Context context) {
    Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
  }

  // 获取屏幕的宽度
  public static int getScreenWidth(Context context) {
    WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = manager.getDefaultDisplay();
    return display.getWidth();
  }

  // 获取屏幕的高度
  public static int getScreenHeight(Context context) {
    WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = manager.getDefaultDisplay();
    return display.getHeight();
  }

  /**
   * 获得一个UUID
   *
   * @return String UUID
   */
  public static String getUUID() {
    String s = UUID.randomUUID().toString();
    //去掉“-”符号
    return s.substring(0, 8)
        + s.substring(9, 13)
        + s.substring(14, 18)
        + s.substring(19, 23)
        + s.substring(24);
  }

  //保留两位小数
  public static String twoPointConversion(double price) {
    //MyLogger.i(">>>>>>>>>>>>>>>>>>>>>>>" + price);
    DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
    String str = decimalFormat.format(price);
    return str;
  }

  /**
   * textview倒计时
   */
  public static CountDownTimer getCountDownTimer(final TextView tv, long millisInFuture) {
    return new CountDownTimer(millisInFuture, 1000) {
      @Override public void onTick(long millisUntilFinished) {
        tv.setEnabled(false);
        tv.setText(millisUntilFinished / 1000 + "s后获取");
      }

      @Override public void onFinish() {
        tv.setText("重新获取");
        tv.setEnabled(true);
      }
    };
  }

  /**
   * 验证手机格式
   */
  public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
    //String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
    String telRegex = "(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}";
    return mobiles.matches(telRegex);
  }
}