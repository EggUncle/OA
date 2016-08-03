package rixin.app.officeauto.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import rixin.app.officeauto.R;
import rixin.app.officeauto.fragment.ContactFragment;

public class MainActivity extends AppCompatActivity {

    private ImageButton imgHome;
    private ImageButton imgTools;
    private ImageButton imgContact;
    private FrameLayout mainFragment;

    private FragmentManager fm;
    private FragmentTransaction transaction;
    private ContactFragment contactFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        getSupportActionBar().hide();
        initViews();
        initFragments();
    }

    /**
     * 加载layout布局文件；
     * 初始化控件；
     * 为控件附加相应事件方法。
     */
    private void initViews(){
        mainFragment = (FrameLayout) findViewById(R.id.main_fragment);
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


    /**
     * 初始化各种要使用fragment
     */

    private void initFragments() {
        contactFragment = new ContactFragment();

        FragmentManager fm = getSupportFragmentManager();

        transaction = fm.beginTransaction();
        transaction.replace(R.id.main_fragment, contactFragment);
        transaction.commit();
    }
}
