package rixin.app.officeauto.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import rixin.app.officeauto.R;
import rixin.app.officeauto.myclass.CircleImageView;
import rixin.app.officeauto.myclass.Msg;

/**
 * Created by egguncle on 16.8.4.
 * 用于消息界面的适配器
 */
public class ContactMessageRecycleAdapter extends RecyclerView.Adapter<ContactMessageRecycleAdapter.MyViewHolder> {

    private Context context;
    private List<Msg> dataMsg;

    public ContactMessageRecycleAdapter(Context context, List<Msg> dataNews) {
        this.context = context;
        this.dataMsg = dataNews;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact_message,
                parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvContactName.setText(dataMsg.get(position).getStrName());
        holder.tvContactContent.setText(dataMsg.get(position).getStrContent());
        holder.tvContactTime.setText(dataMsg.get(position).getStrDate());
        holder.tvContactMessageNum.setText(11+"");


    }


    @Override
    public int getItemCount() {

        return dataMsg == null ? 0 : dataMsg.size();

    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView ivContactIcon;     //头像
        private TextView tvContactName;            //名字
        private TextView tvContactTime;            //消息时间
        private TextView tvContactContent;         //消息内容
        private TextView tvContactMessageNum;      //消息数目
        private LinearLayout lineMessageItem;      //整个列表项

        public MyViewHolder(View itemView) {
            super(itemView);
            ivContactIcon = (CircleImageView) itemView.findViewById(R.id.iv_contact_icon);
            tvContactName = (TextView) itemView.findViewById(R.id.tv_contact_name);
            tvContactTime = (TextView) itemView.findViewById(R.id.tv_contact_time);
            tvContactContent = (TextView) itemView.findViewById(R.id.tv_contact_content);
            tvContactMessageNum = (TextView) itemView.findViewById(R.id.tv_contact_message_num);
            lineMessageItem = (LinearLayout) itemView.findViewById(R.id.line_message_item);
        }

    }

}
