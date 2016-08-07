package rixin.app.officeauto.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.xlf.nrl.NsRefreshLayout;

import java.util.List;

import rixin.app.officeauto.R;
import rixin.app.officeauto.adapter.ContactMessageRecycleAdapter;
import rixin.app.officeauto.myclass.Msg;
import rixin.app.officeauto.myclass.RecycleViewDivider;
import rixin.app.officeauto.myclass.XCRecyclerView;

/**
 * Created by egguncle on 16.8.3.
 */
public class ContactMessageFragment extends Fragment{
    private View view;
    private View headView;
    private Context context;
    private XCRecyclerView rcvFragContactMessage;
    private NsRefreshLayout refreshLayout;

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
        return view;
    }

    private void initViews() {
        rcvFragContactMessage = (XCRecyclerView) view.findViewById(R.id.rcv_frag_contact_message);
        rcvFragContactMessage.addHeaderView(headView);
        rcvFragContactMessage.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL));

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
    }

    private void initVars() {
        messageAdapter = new ContactMessageRecycleAdapter(context, messageData);
        rcvFragContactMessage.setAdapter(messageAdapter);
    }





}
