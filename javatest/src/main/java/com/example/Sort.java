package com.example;

/**
 * @author huanpeng.ling (421620330@qq.com) on 2017/11/24.
 */

public class Sort {
  public static void main(String args[]) {
    int[] numbers = { 10, 20, 15, 0, 6, 7, 2, 1, -5, 55 };
    bubbleSort(numbers);
    printArr(numbers);
    binarySearch(55, numbers);
  }

  private static void bubbleSort(int[] numbers) {
    for (int i = 0; i < numbers.length - 1; i++) {
      for (int j = 0; j < numbers.length - 1 - i; j++) {
        if (numbers[j] > numbers[j + 1]) {
          int temp = numbers[j];
          numbers[j] = numbers[j + 1];
          numbers[j + 1] = temp;
        }
      }
    }
  }

  private static void printArr(int[] numbers) {
    for (int i = 0; i < numbers.length; i++) {
      System.out.print(numbers[i] + ",");
    }
    System.out.println("");
  }

  private static void customSort(int[] numbers) {

  }

  private static void binarySearch(int target, int[] numbers) {
    int left = 0, right = numbers.length - 1;
    while (left <= right) {
      int middle = (left + right) / 2;
      if (target == numbers[middle]) {
        System.out.println("index--" + middle);
        return;
      } else if (target < numbers[middle]) {
        right = middle - 1;
      } else {
        left = middle + 1;
      }
    }
    System.out.println("no such int");
  }
}
