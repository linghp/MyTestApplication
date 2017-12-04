package com.example.huanpengling.myapplication.ui.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.huanpengling.myapplication.R;
import com.example.huanpengling.myapplication.data.api.model.TypeBean;
import com.example.huanpengling.myapplication.ui.DetailActivity;
import java.util.ArrayList;
import java.util.List;
import timber.log.Timber;

/**
 * Created by ling on 2015/8/31.
 * description:
 */
public class TypeListAdapter extends RecyclerView.Adapter implements View.OnClickListener {
  private Context context;
  private List<TypeBean.ShowapiResBodyEntity.ChannelListEntity> listData = new ArrayList<>();

  public TypeListAdapter(Context context,
      List<TypeBean.ShowapiResBodyEntity.ChannelListEntity> mList) {
    super();
    this.context = context;
    if (mList != null) {
      this.listData = mList;
    }
  }

  public void refreshDate(List<TypeBean.ShowapiResBodyEntity.ChannelListEntity> listData) {
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

    View view = View.inflate(viewGroup.getContext(), R.layout.item_typelist, null);
    // 创建一个ViewHolder
    MViewHolder holder = new MViewHolder(view);
    view.setOnClickListener(this);
    Timber.i("onCreateViewHolder");
    return holder;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
    ((MViewHolder) viewHolder).tv_title.setText(listData.get(i).getName());
    viewHolder.itemView.setTag(listData.get(i).getChannelId());
  }

  @Override public void onClick(View v) {
    String id = (String) v.getTag();
    Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    DetailActivity.startThisActivity(context, id);
  }

  public class MViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_title;

    public MViewHolder(View view) {
      super(view);
      this.tv_title = (TextView) view.findViewById(R.id.tv_title);
    }
  }
}
