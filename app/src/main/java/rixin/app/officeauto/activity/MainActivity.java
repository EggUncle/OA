package rixin.app.officeauto.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import rixin.app.officeauto.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton imgHome;
    private ImageButton imgTools;
    private ImageButton imgContact;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        getSupportActionBar().hide();
        initViews();
    }

    /**
     * 加载layout布局文件；
     * 初始化控件；
     * 为控件附加相应事件方法。
     */
    private void initViews(){
        imgHome = (ImageButton) findViewById(R.id.img_home);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHome.setImageDrawable(getResources().getDrawable(R.drawable.tabbar_home_highlighted));
                imgContact.setImageDrawable(getResources().getDrawable(R.drawable.tabbar_contact));
            }
        });

        imgTools = (ImageButton) findViewById(R.id.img_tools);
        imgContact = (ImageButton) findViewById(R.id.img_contact);
        imgContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgContact.setImageDrawable(getResources().getDrawable(R.drawable.tabbar_contact_highlighted));
                imgHome.setImageDrawable(getResources().getDrawable(R.drawable.tabbar_home));
            }
        });
    }
}
