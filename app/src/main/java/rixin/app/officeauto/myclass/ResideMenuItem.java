package rixin.app.officeauto.myclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import rixin.app.officeauto.R;

/**
 * Created by 胡钰 on 2016/8/3.
 */
public class ResideMenuItem extends LinearLayout{

    /** menu item  icon  */
    private TextView tv_num;
    /** menu item  title */
    private TextView tv_title;


    public ResideMenuItem(Context context) {
        super(context);
        initViews(context);
    }

    public ResideMenuItem(Context context, String title) {
        super(context);
        initViews(context);
        tv_title.setText(title);
    }

    public ResideMenuItem(Context context, int icon, int title) {
        super(context);
        initViews(context);
        //没消息就不显示，这里可以用个if判断
        tv_num.setBackgroundResource(icon);
        tv_title.setText(title);
    }

    public ResideMenuItem(Context context, int icon, String title) {
        super(context);
        initViews(context);
        //在消息提醒的这里，如果有未读消息就显示，否则不显示
        tv_num.setText("1");
        tv_num.setBackgroundResource(icon);

        tv_title.setText(title);
    }

    private void initViews(Context context){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.residemenu_item, this);
        tv_num = (TextView) findViewById(R.id.tv_num);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    /**
     * set the icon color;
     *
     * @param icon
     */
    public void setIcon(int icon){
        tv_num.setBackgroundResource(icon);
    }

    /**
     * set the title with resource
     * ;
     * @param title
     */
    public void setTitle(int title){
        tv_title.setText(title);
    }

    /**
     * set the title with string;
     *
     * @param title
     */
    public void setTitle(String title){
        tv_title.setText(title);
    }
}
