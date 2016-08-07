package rixin.app.officeauto.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.xlf.nrl.NsRefreshLayout;

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
    private NsRefreshLayout refreshLayout;
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
        headView = inflater.inflate(R.layout.farg_contact_message_title,null);
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

        refreshLayout = (NsRefreshLayout) view.findViewById(R.id.contact_message_nrl);
        refreshLayout.setRefreshLayoutController(new NsRefreshLayout.NsRefreshLayoutController() {
            @Override
            public boolean isPullRefreshEnable() {
                return true;
            }

            @Override
            public boolean isPullLoadEnable() {
                return false;
            }
        });
        refreshLayout.setRefreshLayoutListener(new NsRefreshLayout.NsRefreshLayoutListener() {
            @Override
            public void onRefresh() {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishPullRefresh();

                        //  Toast.makeText(getActivity(), "下拉刷新", Toast.LENGTH_LONG).show();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishPullLoad();

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
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                messageAdapter.notifyItemRemoved(position);
                messageData.remove(position);
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
            refreshLayout.setVisibility(View.VISIBLE);
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
