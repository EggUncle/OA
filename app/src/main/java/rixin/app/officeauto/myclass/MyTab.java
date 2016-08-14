package rixin.app.officeauto.myclass;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import rixin.app.officeauto.R;

/**
 * Created by egguncle on 16.8.13.
 * 自定义控件 用于广告栏底部
 */
public class MyTab extends LinearLayout {

    private Context context;
    private ImageView myTab1;
    private ImageView myTab2;
    private ImageView myTab3;
    private ImageView myTab4;
    private ImageView myTab5;

    private List<ImageView> imageViews = new ArrayList<>();
    //private ImageView[] imageViews = new ImageView[]{myTab1,myTab2,myTab3,myTab4,myTab5};

    public MyTab(Context context) {
        super(context, null);
    }

    public MyTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.my_tab, this, true);
        this.myTab1 = (ImageView) findViewById(R.id.my_tab_1);
        this.myTab2 = (ImageView) findViewById(R.id.my_tab_2);
        this.myTab3 = (ImageView) findViewById(R.id.my_tab_3);
        this.myTab4 = (ImageView) findViewById(R.id.my_tab_4);
        this.myTab5 = (ImageView) findViewById(R.id.my_tab_5);
        imageViews.add(myTab1);
        imageViews.add(myTab2);
        imageViews.add(myTab3);
        imageViews.add(myTab4);
        imageViews.add(myTab5);
    }

    public void changeTab(int position) {
        position = position%imageViews.size();
        for(int i = 0 ;i<imageViews.size();i++){
            imageViews.get(i).setImageResource(R.drawable.home_banner_slider);
        }
        imageViews.get(position).setImageResource(R.drawable.home_banner_slider_highlighted);
//        for(int i=0;i<imageViews.length;i++){
//            imageViews[i].setImageResource(R.drawable.home_banner_slider);
//        }
//        imageViews[position].setImageResource(R.drawable.home_banner_slider_highlighted);
    }

}
