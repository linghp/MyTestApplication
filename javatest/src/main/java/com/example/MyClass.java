package com.example;

public class MyClass {
  public static void main(String args[]) {
    A a = new B();
    a.show();
    B b = new C();
    b.show();

    a.show2();
    System.out.println(b.str);
  }

  static class A {
    public String str = "A";

    public void show() {
      show2();
    }

    public void show2() {
      System.out.println("1");
    }
  }

  static class B extends A {
    public String str = "B";

    public void show2() {
      System.out.println("2");
    }
  }

  static class C extends B {
    @Override public void show() {
      super.show();
    }

    public void show2() {
      System.out.println("3");
    }
  }
}
