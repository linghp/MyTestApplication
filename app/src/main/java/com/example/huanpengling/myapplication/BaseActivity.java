package com.example.huanpengling.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.huanpengling.myapplication.data.DataProvider;
import com.squareup.picasso.Picasso;

public abstract class BaseActivity extends AppCompatActivity {
  protected CashApp cashApp;
  protected DataProvider dataProvider;
  protected Picasso picasso;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    cashApp = CashApp.get(this);
    dataProvider = cashApp.getDataProvider();
    picasso = dataProvider.getPicasso();
  }

  @Override public void setContentView(int layoutResID) {
    super.setContentView(layoutResID);
    initView();
    initData();
    updateView();
  }

  abstract protected void initView();

  abstract protected void initData();

  abstract protected void updateView();

  @Override protected void onResume() {
    super.onResume();
    picasso.resumeTag(this);
  }

  @Override protected void onPause() {
    super.onPause();
    picasso.pauseTag(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    picasso.cancelTag(this);
  }
}
