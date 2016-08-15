package rixin.app.officeauto.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import rixin.app.officeauto.R;
import rixin.app.officeauto.myclass.News;
import rixin.app.officeauto.myclass.XCRecyclerView;

/**
 * Created by egguncle on 16.8.13.
 */
public class HomeRecycleAdapter extends XCRecyclerView.Adapter<HomeRecycleAdapter.ViewHolder> {

    private List<News> listNews;
    private Context context;
    private LinearLayout popWindow;
    private AlertDialog.Builder builder;

    private TextView tvPopwindowHomeTitle;
    private TextView tvPopwindowHomeDate;
    private TextView tvHomePopwindowAuthor;
    private TextView tvHomePopwindowContent;
    private TextView tvHomePopwindowForm;

    public HomeRecycleAdapter(Context context, List<News> listNews) {
        this.listNews = listNews;
        this.context = context;

    }

    @Override
    public HomeRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home,
                parent, false));
    }

    @Override
    public void onBindViewHolder(final HomeRecycleAdapter.ViewHolder holder, final int position) {
        final String title=listNews.get(position).getStrNewTitle();
        final String date =listNews.get(position).getStrNewDate();
        final String author=listNews.get(position).getStrNewAuthor();
        final String content=listNews.get(position).getStrNewContent();
        final String form=listNews.get(position).getStrNewForm();


        holder.tvItemHomeTitle.setText(title);
        holder.tvItemHomeDate.setText(date);
        holder.tvHomeItemAuthor.setText(author);
        holder.tvHomeItemContent.setText(content);
        holder.tvHomeItemForm.setText(form);

        holder.homeLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popWindow = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.home_popwindow,null,false );//弹窗
                tvPopwindowHomeTitle = (TextView) popWindow.findViewById(R.id.tv_popwindow_home_title);
                tvPopwindowHomeDate = (TextView) popWindow.findViewById(R.id.tv_popwindow_home_date);
                tvHomePopwindowAuthor = (TextView) popWindow.findViewById(R.id.tv_home_popwindow_author);
                tvHomePopwindowContent = (TextView) popWindow.findViewById(R.id.tv_home_popwindow_content);
                tvHomePopwindowForm = (TextView) popWindow.findViewById(R.id.tv_home_popwindow_form);


                tvPopwindowHomeTitle.setText(title);
                tvPopwindowHomeDate.setText(date);
                tvHomePopwindowAuthor.setText(author);
                tvHomePopwindowContent.setText(content);
                tvHomePopwindowForm .setText(form);

                builder = new AlertDialog.Builder(context).setView(popWindow);
                builder.create();
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listNews == null ? 0 : listNews.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItemHomeTitle;
        private TextView tvItemHomeDate;
        private TextView tvHomeItemAuthor;
        private TextView tvHomeItemContent;
        private TextView tvHomeItemForm;
        private LinearLayout homeLine;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItemHomeTitle = (TextView) itemView.findViewById(R.id.tv_item_home_title);
            tvItemHomeDate = (TextView) itemView.findViewById(R.id.tv_item_home_date);
            tvHomeItemAuthor = (TextView) itemView.findViewById(R.id.tv_home_item_author);
            tvHomeItemContent = (TextView) itemView.findViewById(R.id.tv_home_item_content);
            tvHomeItemForm = (TextView) itemView.findViewById(R.id.tv_home_item_form);
            homeLine = (LinearLayout) itemView.findViewById(R.id.item_home_line);
        }

    }
}
