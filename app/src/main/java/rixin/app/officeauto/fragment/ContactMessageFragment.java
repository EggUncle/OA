package rixin.app.officeauto.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import rixin.app.officeauto.R;
import rixin.app.officeauto.adapter.ContactMessageRecycleAdapter;
import rixin.app.officeauto.myclass.Msg;
import rixin.app.officeauto.myclass.RecycleViewDivider;

/**
 * Created by egguncle on 16.8.3.
 */
public class ContactMessageFragment extends Fragment {
    private View view;
    private Context context;
    private RecyclerView rcvFragContactMessageNotice;
    private RecyclerView rcvFragContactMessage;

    private ContactMessageRecycleAdapter noticeAdapter;
    private ContactMessageRecycleAdapter messageAdapter;
    private List<Msg> noticeData;
    private List<Msg> messageData;

//    public ContactMessageFragment() {
//
//    }


    public ContactMessageFragment(Context context, List<Msg> noticeData, List<Msg> messageData) {
        this.context = context;
        this.noticeData = noticeData;
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
        initViews();
        initVars();
        return view;
    }

    private void initViews() {
        rcvFragContactMessageNotice = (RecyclerView) view.findViewById(R.id.rcv_frag_contact_message_notice);
        rcvFragContactMessage = (RecyclerView) view.findViewById(R.id.rcv_frag_contact_message);

        rcvFragContactMessageNotice.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL));
        rcvFragContactMessage.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL));
    }

    private void initVars() {
        noticeAdapter = new ContactMessageRecycleAdapter(context, noticeData);
        messageAdapter = new ContactMessageRecycleAdapter(context, messageData);

        rcvFragContactMessageNotice.setAdapter(noticeAdapter);
        rcvFragContactMessage.setAdapter(messageAdapter);
    }


}
