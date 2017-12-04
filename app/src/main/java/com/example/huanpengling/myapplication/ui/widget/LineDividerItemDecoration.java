package com.example.huanpengling.myapplication.ui.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 添加{@link RecyclerView} 实线分割线
 *
 * Created by golden on 2015/5/8.
 */
public class LineDividerItemDecoration extends RecyclerView.ItemDecoration {

  private int orientation = LinearLayoutManager.VERTICAL;

  /**
   * item之间分割线的size，默认为2
   */
  private int itemSize = 2;

  /**
   * 绘制item分割线的画笔，和设置其属性 来绘制个性分割线
   */
  private Paint paint;

  /**
   * 构造方法传入布局方向，不可不传
   *
   * @param orientation 分割线绘制方向
   */
  public LineDividerItemDecoration(int orientation) {
    this.orientation = orientation;
    if (orientation != LinearLayoutManager.VERTICAL
        && orientation != LinearLayoutManager.HORIZONTAL) {
      throw new IllegalArgumentException("The orientation is invalid.");
    }
    paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(0xffe8e8e8);
  }

  @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    if (orientation == LinearLayoutManager.VERTICAL) {
      drawVertical(c, parent);
    } else {
      drawHorizontal(c, parent);
    }
  }

  /**
   * 绘制纵向 item 分割线
   *
   * @param canvas 画布
   */
  private void drawVertical(Canvas canvas, RecyclerView parent) {
    final int left = parent.getPaddingLeft();
    final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
    final int childSize = parent.getChildCount();
    for (int i = 0; i < childSize; i++) {
      final View child = parent.getChildAt(i);
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
      final int top = child.getBottom() + layoutParams.bottomMargin;
      final int bottom = top + itemSize;
      canvas.drawRect(left, top, right, bottom, paint);
    }
  }

  /**
   * 绘制横向 item 分割线
   *
   * @param canvas 画布
   */
  private void drawHorizontal(Canvas canvas, RecyclerView parent) {
    final int top = parent.getPaddingTop();
    final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
    final int childSize = parent.getChildCount();
    for (int i = 0; i < childSize; i++) {
      final View child = parent.getChildAt(i);
      RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
      final int left = child.getRight() + layoutParams.rightMargin;
      final int right = left + itemSize;
      canvas.drawRect(left, top, right, bottom, paint);
    }
  }

  /**
   * 设置item分割线的size
   */
  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    if (orientation == LinearLayoutManager.VERTICAL) {
      outRect.set(0, 0, 0, itemSize);
    } else {
      outRect.set(0, 0, itemSize, 0);
    }
  }
}
