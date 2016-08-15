package rixin.app.officeauto.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xlf.nrl.NsRefreshLayout;
import java.util.Collections;
import java.util.List;

import rixin.app.officeauto.R;
import rixin.app.officeauto.adapter.ContactMessageRecycleAdapter;
import rixin.app.officeauto.myclass.Msg;
import rixin.app.officeauto.myclass.RecycleViewDivider;
import rixin.app.officeauto.myclass.XCRecyclerView;


/**
 * Created by egguncle on 16.8.3.
 * 消息界面
 */
public class ContactMessageFragment extends Fragment{
    private View view;
    private View headView;
    private Context context;
    private XCRecyclerView rcvFragContactMessage;
    private NsRefreshLayout nrlMessage;
    private ProgressBar pbContactMessage;
    private ItemTouchHelper itemTouchHelper;

    private ContactMessageRecycleAdapter messageAdapter;
    private List<Msg> messageData;

    public ContactMessageFragment() {

    }


    public ContactMessageFragment(Context context, List<Msg> messageData) {
        this.context = context;
        this.messageData = messageData;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_contact_message, null);
        headView = inflater.inflate(R.layout.frag_contact_message_title,null);
        initViews();
        initVars();
        MyAyncTask myAyncTask = new MyAyncTask();
        myAyncTask.execute();
        return view;
    }

    private void initViews() {
        pbContactMessage = (ProgressBar) view.findViewById(R.id.contact_message_progress);
        rcvFragContactMessage = (XCRecyclerView) view.findViewById(R.id.rcv_frag_contact_message);
        rcvFragContactMessage.addHeaderView(headView);
        rcvFragContactMessage.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL));

        rcvFragContactMessage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });


        nrlMessage = (NsRefreshLayout) view.findViewById(R.id.contact_message_nrl);
        nrlMessage.setRefreshLayoutController(new NsRefreshLayout.NsRefreshLayoutController() {
            @Override
            public boolean isPullRefreshEnable() {
                return true;
            }

            @Override
            public boolean isPullLoadEnable() {
                return false;
            }
        });
        nrlMessage.setRefreshLayoutListener(new NsRefreshLayout.NsRefreshLayoutListener() {
            @Override
            public void onRefresh() {
                nrlMessage.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nrlMessage.finishPullRefresh();

                        //  Toast.makeText(getActivity(), "下拉刷新", Toast.LENGTH_LONG).show();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                nrlMessage.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nrlMessage.finishPullLoad();

                        //     Toast.makeText(getActivity(), "上拉加载更多", Toast.LENGTH_LONG).show();
                    }
                }, 1000);
            }
        });

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                final int dragFlags;
                final int swipeFlags;
                if (viewHolder.getAdapterPosition() == 0) {   //阻止 小助手 系统通知项被滑动删除
                    return 0;
                }

                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    swipeFlags = 0;
                } else {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                }
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                // 释放View时回调，清除背景颜色，隐藏图标
                // 默认是操作ViewHolder的itemView，这里调用ItemTouchUIUtil的clearView方法传入指定的view
                getDefaultUIUtil().clearView(((ContactMessageRecycleAdapter.ItemViewHolder) viewHolder).lineMessageItem);
                ((ContactMessageRecycleAdapter.ItemViewHolder) viewHolder).vBackground.setBackgroundColor(Color.TRANSPARENT);
                ((ContactMessageRecycleAdapter.ItemViewHolder) viewHolder).ivDelete.setVisibility(View.GONE);
          //      ((ContactMessageRecycleAdapter.ItemViewHolder) viewHolder).ivDone.setVisibility(View.GONE);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (position!=messageAdapter.getItemCount()) {
                    messageAdapter.notifyItemRemoved(position);
                    messageData.remove(position);
                }else{
                    messageAdapter.notifyItemRemoved(position);
                    messageData.remove(position-1);
                }

            }

//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                // 处理滑动事件回调
//                final int pos = viewHolder.getAdapterPosition();
//                final Msg item = messageData.get(pos);
//                messageData.remove(pos);
//                messageAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//                String text;
//                // 判断方向，进行不同的操作
//                if (direction == ItemTouchHelper.RIGHT) {
//                    text = "删除一项";
//                } else {
//                    text = "延迟一项";
//                }
////                Snackbar.make(viewHolder.itemView, text, Snackbar.LENGTH_LONG)
////                        .setAction("撤销", new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v) {
////                                mData.add(pos, item);
////                                mSampleAdapter.notifyItemInserted(pos);
////                            }
////                        }).show();
//            }

//            @Override
//            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//                    //滑动时改变Item的透明度
//                    final float alpha = 1 - Math.abs(dX) / (float)viewHolder.itemView.getWidth();
//                    viewHolder.itemView.setAlpha(alpha);
//                    viewHolder.itemView.setTranslationX(dX);
//                }
//            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                // 当viewHolder的滑动或拖拽状态改变时回调
                if (viewHolder != null) {
                    // 默认是操作ViewHolder的itemView，这里调用ItemTouchUIUtil的clearView方法传入指定的view
                    getDefaultUIUtil().onSelected(((ContactMessageRecycleAdapter.ItemViewHolder) viewHolder).lineMessageItem);
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                // ItemTouchHelper的onDraw方法会调用该方法，可以使用Canvas对象进行绘制，绘制的图案会在RecyclerView的下方
                // 默认是操作ViewHolder的itemView，这里调用ItemTouchUIUtil的clearView方法传入指定的view
                getDefaultUIUtil().onDraw(c, recyclerView, ((ContactMessageRecycleAdapter.ItemViewHolder) viewHolder).lineMessageItem, dX, dY, actionState, isCurrentlyActive);
                if (dX > 0) { // 向左滑动是的提示
//                    ((SampleAdapter.ItemViewHolder) viewHolder).vBackground.setBackgroundResource(R.color.colorDone);
//                    ((SampleAdapter.ItemViewHolder) viewHolder).ivDone.setVisibility(View.VISIBLE);
//                    ((SampleAdapter.ItemViewHolder) viewHolder).ivSchedule.setVisibility(View.GONE);
                }
                if (dX < 0) { // 向右滑动时的提示
                    ((ContactMessageRecycleAdapter.ItemViewHolder) viewHolder).vBackground.setBackgroundResource(R.color.colorDelete);
                    ((ContactMessageRecycleAdapter.ItemViewHolder) viewHolder).ivDelete.setVisibility(View.VISIBLE);
            //        ((ContactMessageRecycleAdapter.ItemViewHolder) viewHolder).ivDone.setVisibility(View.GONE);
                }
            }

            @Override
            public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                // ItemTouchHelper的onDrawOver方法会调用该方法，可以使用Canvas对象进行绘制，绘制的图案会在RecyclerView的上方
                // 默认是操作ViewHolder的itemView，这里调用ItemTouchUIUtil的clearView方法传入指定的view
                getDefaultUIUtil().onDrawOver(c, recyclerView, ((ContactMessageRecycleAdapter.ItemViewHolder) viewHolder).lineMessageItem, dX, dY, actionState, isCurrentlyActive);
            }
        });
        itemTouchHelper.attachToRecyclerView(rcvFragContactMessage);
    }


    private void initVars() {
        messageAdapter = new ContactMessageRecycleAdapter(context, messageData);
    }






    //使用异步加载来加载联系人列表
    //第一个参数：启动任务时输入的参数类型.
    //第二个参数：后台任务执行中返回进度值的类型.
    //第三个参数：后台任务执行完成后返回结果的类型.
    class MyAyncTask extends AsyncTask<Void, Integer, Void> {


        public MyAyncTask() {
            super();
        }


        @Override
        protected void onPostExecute(Void v) {
            //执行后返回值
            super.onPostExecute(v);
            rcvFragContactMessage.setAdapter(messageAdapter);
            pbContactMessage.setVisibility(View.GONE);
            nrlMessage.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPreExecute() {
            //执行前的初始化操作
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //更新时调用的操作

            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... params) {
            //后台加载时的操作

            initVars();

            return null;
        }

    }

}
