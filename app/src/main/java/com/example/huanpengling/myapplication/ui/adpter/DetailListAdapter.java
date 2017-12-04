package com.example.huanpengling.myapplication.ui.adpter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.huanpengling.myapplication.R;
import com.example.huanpengling.myapplication.data.api.model.Detail;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ling on 2015/8/31.
 * description:
 */
public class DetailListAdapter extends RecyclerView.Adapter implements View.OnClickListener {

  private Context context;
  private Picasso picasso;
  private List<Detail.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity> listData =
      new ArrayList<>();

  public DetailListAdapter(Context context,
      List<Detail.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity> mList, Picasso picasso) {
    super();
    this.context = context;
    this.picasso = picasso;
    if (mList != null) {
      this.listData = mList;
    }
  }

  public void refreshDate(
      List<Detail.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity> listData) {
    if (listData != null && listData.size() > 0) {
      this.listData.clear();
      this.listData.addAll(listData);
      notifyDataSetChanged();
    }
  }

  @Override public int getItemCount() {
    // TODO Auto-generated method stub
    return listData.size();
  }

  @Override public MViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {

    View view = View.inflate(viewGroup.getContext(), R.layout.item_detaillist, null);
    // 创建一个ViewHolder
    MViewHolder holder = new MViewHolder(view);
    view.setOnClickListener(this);
    return holder;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
    ((MViewHolder) viewHolder).setData(listData.get(i));
    viewHolder.itemView.setTag(listData.get(i).getLink());
  }

  @Override public void onClick(View v) {
    String link = (String) v.getTag();
    Toast.makeText(context, link, Toast.LENGTH_SHORT).show();
    Uri uri = Uri.parse(link);
    Intent it = new Intent(Intent.ACTION_VIEW, uri);
    context.startActivity(it);
  }

  public class MViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.iv_01) ImageView iv01;
    @BindView(R.id.tv_desc) TextView tvDesc;
    @BindView(R.id.tv_source) TextView tvSource;
    @BindView(R.id.tv_date) TextView tvDate;

    public MViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, itemView);
    }

    void setData(Detail.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity contentlistEntity) {
      tvTitle.setText(contentlistEntity.getTitle());
      tvDesc.setText(contentlistEntity.getDesc());
      tvSource.setText(contentlistEntity.getSource());
      tvDate.setText(contentlistEntity.getPubDate());
      Log.i("setData", contentlistEntity.toString());
      if (contentlistEntity.getImageurls().size() > 0) {
        String imgurl = contentlistEntity.getImageurls().get(0).getUrl();
        Log.i("imgurl", imgurl);
        picasso.load(imgurl)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.error)
            .tag(context)
            .into(iv01);
      } else {
        iv01.setImageResource(R.mipmap.ic_launcher);
      }
    }
  }
}
