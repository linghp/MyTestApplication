package com.example.huanpengling.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.huanpengling.myapplication.BaseActivity;
import com.example.huanpengling.myapplication.R;
import com.example.huanpengling.myapplication.data.api.model.Detail;
import com.example.huanpengling.myapplication.ui.adpter.DetailListAdapter;
import com.example.huanpengling.myapplication.ui.widget.LineDividerItemDecoration;
import rx.Subscriber;
import timber.log.Timber;

public class DetailActivity extends BaseActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.recycler_list) RecyclerView recyclerView;
  @BindView(R.id.fab) FloatingActionButton fab;
  private DetailListAdapter detailListAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    Timber.i("onCreate");
  }

  public static void startThisActivity(Context context, String typeid) {
    Intent intent = new Intent(context, DetailActivity.class);
    intent.putExtra("typeid", typeid);
    context.startActivity(intent);
  }

  @Override protected void initView() {
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);

    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });
  }

  @Override protected void initData() {
    String typeid = getIntent().getStringExtra("typeid");
    if (!TextUtils.isEmpty(typeid)) {
      getDetailList(typeid);
    }
    final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.addItemDecoration(new LineDividerItemDecoration(LinearLayoutManager.VERTICAL));
  }

  @Override protected void updateView() {

  }

  private void getDetailList(String typeid) {
    dataProvider.getDetailListProvider().getDetailList(typeid).subscribe(new Subscriber<Detail>() {
      @Override public void onCompleted() {
        Log.i("onCompleted", "onCompleted");
      }

      @Override public void onError(Throwable e) {
        Log.i("onError", e.getMessage());
      }

      @Override public void onNext(Detail detail) {
        Log.i("onNext", detail.toString());
        detailListAdapter = new DetailListAdapter(DetailActivity.this,
            detail.getShowapi_res_body().getPagebean().getContentlist(), picasso);
        recyclerView.setAdapter(detailListAdapter);
      }
    });
  }
}
