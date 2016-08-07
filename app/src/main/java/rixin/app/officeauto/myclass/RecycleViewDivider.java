package rixin.app.officeauto.myclass;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by egguncle on 16.8.4.
 * 自定义recycleview分割线
 */
public class RecycleViewDivider extends RecyclerView.ItemDecoration {

    private Paint paint;
    private Drawable divider;
    private int dividerHeight = 2;//分割线高度，默认为2px
    private int orientation;//列表的方向：LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    /**
     * 默认分割线：高度为2px，颜色为灰色
     *
     * @param context
     * @param orientation 列表方向
     */
    public RecycleViewDivider(Context context, int orientation) {
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager
                .HORIZONTAL) {
            throw new IllegalArgumentException("请输入正确的参数！");
        }
        this.orientation = orientation;

        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        divider = a.getDrawable(0);
        a.recycle();
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation 列表方向
     * @param drawableId  分割线图片，高度默认为图片原始高度
     */
    public RecycleViewDivider(Context context, int orientation, int drawableId) {
        this(context, orientation);
        divider = ContextCompat.getDrawable(context, drawableId);
        dividerHeight = divider.getIntrinsicHeight();
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation 列表方向
     * @param drawable    分割线图片，高度默认为图片原始高度
     */
    public RecycleViewDivider(Context context, int orientation, Drawable drawable) {
        this(context, orientation);
        divider = drawable;
        dividerHeight = divider.getIntrinsicHeight();
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation    列表方向
     * @param drawable       分割线图片
     * @param drawableHeight 分割线图片高度
     */
    public RecycleViewDivider(Context context, int orientation, Drawable drawable, int
            drawableHeight) {
        this(context, orientation);
        divider = drawable;
        dividerHeight = drawableHeight;
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation   列表方向
     * @param dividerHeight 分割线高度
     * @param dividerColor  分割线颜色
     */
    public RecycleViewDivider(Context context, int orientation, int dividerHeight, int
            dividerColor) {
        this(context, orientation);
        this.dividerHeight = dividerHeight;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(dividerColor);
        paint.setStyle(Paint.Style.FILL);
    }


    //获取分割线尺寸
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, dividerHeight);
    }

    //绘制分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (orientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    //绘制横向 item 分割线
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
    //    final int left = parent.getPaddingLeft();
        final int left = parent.getPaddingLeft()+200;
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight()-30;
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + dividerHeight;
            if (divider != null) {
                divider.setBounds(left, top, right, bottom);
                divider.draw(canvas);
            }
            if (paint != null) {
                canvas.drawRect(left, top, right, bottom, paint);
            }
        }
    }

    //绘制纵向 item 分割线
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + dividerHeight;
            if (divider != null) {
                divider.setBounds(left, top, right, bottom);
                divider.draw(canvas);
            }
            if (paint != null) {
                canvas.drawRect(left, top, right, bottom, paint);
            }
        }
    }
}