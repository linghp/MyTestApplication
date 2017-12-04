package com.example.huanpengling.myapplication.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.huanpengling.myapplication.BaseActivity;
import com.example.huanpengling.myapplication.R;
import com.example.huanpengling.myapplication.data.api.model.TypeBean;
import com.example.huanpengling.myapplication.ui.adpter.TypeListAdapter;
import com.example.huanpengling.myapplication.ui.widget.LineDividerItemDecoration;
import com.example.huanpengling.myapplication.util.CommonUtil;
import com.example.huanpengling.myapplication.util.QRCodeUtil;
import rx.Subscriber;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.recycler_list) RecyclerView recyclerView;
  @BindView(R.id.fab) FloatingActionButton fab;
  @BindView(R.id.iv_qr) ImageView ivQr;
  @BindView(R.id.root_view) View rootView;
  private TypeListAdapter typeListAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //    .setAction("Action", null)
        //    .show();
        ivQr.setVisibility(View.VISIBLE);
        //ivQr.setImageBitmap(QRCodeUtil.createImage("123456789", CommonUtil.dip2px(MainActivity.this,200), CommonUtil.dip2px(MainActivity.this,200),
        //    BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        ivQr.setImageBitmap(
            QRCodeUtil.getBarcode("123456789", CommonUtil.dip2px(MainActivity.this, 200),
                CommonUtil.dip2px(MainActivity.this, 50)));
      }
    });

    getTypeList();
    final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.addItemDecoration(new LineDividerItemDecoration(LinearLayoutManager.VERTICAL));
  }

  @Override protected void initView() {

  }

  @Override protected void initData() {

  }

  @Override protected void updateView() {

  }

  private void getTypeList() {
    dataProvider.getTypeListProvider().getType().subscribe(new Subscriber<TypeBean>() {
      @Override public void onCompleted() {
        Log.i("onCompleted", "onCompleted");
        Timber.i("Timber_onCompleted");
      }

      @Override public void onError(Throwable e) {
        Log.i("onError", e.getMessage());
      }

      @Override public void onNext(TypeBean typeBean) {
        Log.i("onNext", typeBean.toString());
        typeListAdapter =
            new TypeListAdapter(MainActivity.this, typeBean.getShowapi_res_body().getChannelList());
        recyclerView.setAdapter(typeListAdapter);
      }
    });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    switch (id) {
      case R.id.action_settings:
        startActivity(new Intent(this, WebViewSample.class));
        break;
      case R.id.action_settings2:
        showPic();
        break;
      case R.id.constraint_demo:
        startActivity(new Intent(this, ConstraintActivity.class));
        break;
    }

    return super.onOptionsItemSelected(item);
  }

  private void showPic() {
    int width = rootView.getMeasuredWidth();
    int height = rootView.getMeasuredHeight();
    Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
    Canvas canvas = new Canvas(b);
    rootView.draw(canvas);
    ivQr.setImageBitmap(b);
    ivQr.setVisibility(View.VISIBLE);
  }
}
