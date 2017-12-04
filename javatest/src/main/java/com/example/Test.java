package com.example;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by huanpeng.ling on 2016/1/5.
 */
public class Test {
  public static void main(String args[]) {
    int money = (int) (Double.parseDouble("512.18") * 100);
    System.out.println("money--" + money);

    double money1 = Double.parseDouble("512.18") * 100;
    System.out.println("money--" + money1);

    String money2 = new BigDecimal("512.18").multiply(new BigDecimal(100)).intValue() + "";
    System.out.println("money2--" + money2);

    String money3 = "5124";
    //System.out.println("money3--"+money3.substring(1,3));

    System.out.println(512.18 * 100);
    System.out.println(Double.parseDouble("2.18123123131212212121"));
    System.out.println(1.2 - 0.3);
    for (int i = 0; i < 20; i++) {
      System.out.println(new Random().nextBoolean());
    }

    byte a = 'a';
    String hash = Integer.toHexString(0xff & a);
    System.out.println("hash--" + hash);

    System.out.println((char) (a << 1));
  }

  public static String format(double number) {
    String result = "0.00";
    try {
      DecimalFormat format = new DecimalFormat("#0.00");
      result = format.format(number);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}
