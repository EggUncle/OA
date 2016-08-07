package rixin.app.officeauto.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rixin.app.officeauto.R;
import rixin.app.officeauto.myclass.CircleImageView;
import rixin.app.officeauto.myclass.PersonBean;

/**
 * Created by egguncle on 16.8.5.
 * 用于联系人界面的适配器
 */
public class ContactBookRecycleAdapter extends RecyclerView.Adapter<ContactBookRecycleAdapter.ViewHolder> {
    List<PersonBean> data;

    public ContactBookRecycleAdapter(List<PersonBean> data) {
        this.data = data;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTag;
        private CircleImageView ivContactBookIcon;
        private TextView tvContactBookName;


        public ViewHolder(View itemView) {
            super(itemView);
            tvTag = (TextView) itemView.findViewById(R.id.tag);
            ivContactBookIcon = (CircleImageView) itemView.findViewById(R.id.iv_contact_book_icon);
            tvContactBookName = (TextView) itemView.findViewById(R.id.tv_contact_book_name);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_contact_book, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PersonBean person = data.get(position);
        // 获取首字母的assii值
        int selection = person.getFirstPinYin().charAt(0);
        // 通过首字母的assii值来判断是否显示字母
        int positionForSelection = getPositionForSelection(selection);
        if (position == positionForSelection) {// 相等说明需要显示字母
            holder.tvTag.setVisibility(View.VISIBLE);
            holder.tvTag.setText(person.getFirstPinYin());
        } else {
            holder.tvTag.setVisibility(View.GONE);

        }
        holder.tvContactBookName.setText(data.get(position).getName());
    }

    public int getPositionForSelection(int selection) {
        for (int i = 0; i < data.size(); i++) {
            String Fpinyin = data.get(i).getFirstPinYin();

            char first = Fpinyin.toUpperCase().charAt(0);
            if (first == selection) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}