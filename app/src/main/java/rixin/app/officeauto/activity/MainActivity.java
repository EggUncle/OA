package rixin.app.officeauto.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import rixin.app.officeauto.R;
import rixin.app.officeauto.fragment.ContactFragment;
import rixin.app.officeauto.fragment.HomeFragment;
import rixin.app.officeauto.fragment.ToolsFragment;
import rixin.app.officeauto.myclass.ResideMenu;
import rixin.app.officeauto.myclass.ResideMenuItem;

//控制了所有主界面间的联系，所有的Activity要用继承自fragment的形式存在，然后会一个个new出来
//如果这个residemenu和其他控件有冲突，可以用residemenu的方法在打开其他界面的时候忽略这个控件

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private ImageButton imgHome;
    private ImageButton imgTools;
    private ImageButton imgContact;
    private FrameLayout mainFragment;

    private FragmentManager fm;
    private FragmentTransaction transaction;
    private ContactFragment contactFragment;
    private HomeFragment homeFragment;
    private ToolsFragment toolsFragment;

    private Fragment nowFragment;// 当前的farmgent，在toolFragment收起后显示该fragment

    private ResideMenu mresideMenu;
    private MainActivity context;
    private ResideMenuItem leftbar_manager,leftbar_setting,leftbar_news,leftbar_clear,leftbar_help,leftbar_about;
    private int leftbar_icon=R.mipmap.leftbar_sign;

    private Boolean keyTools = false; //用来判断toolFragment是否展开

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
      //  getSupportActionBar().hide();

        context = this;
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new HomeFragment(this));

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
        imgHome = (ImageButton) findViewById(R.id.img_home_page);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHome.setImageDrawable(getResources().getDrawable(R.drawable.tabbar_home_highlighted));
                imgContact.setImageDrawable(getResources().getDrawable(R.drawable.tabbar_contact));

                nowFragment = homeFragment;

                transaction = fm.beginTransaction();
                transaction.replace(R.id.main_fragment, homeFragment);
                transaction.commit();
            }
        });

        imgTools = (ImageButton) findViewById(R.id.img_tools_page);
        imgTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!keyTools) {    //当toolsFragment未展开时，展开它
                    transaction = fm.beginTransaction();
                    transaction.setCustomAnimations(R.anim.fragment_slide_in_from_bottom,R.anim.fragment_slide_in_from_top);
                    transaction.replace(R.id.main_fragment, toolsFragment);
                    transaction.commit();

                    keyTools=true;
                    imgTools.setImageResource(R.drawable.home_tabbar_tools_highlighted);

                }else{
                    transaction = fm.beginTransaction();
                    transaction.setCustomAnimations(R.anim.fragment_slide_in_from_bottom_2,R.anim.fragment_slide_in_from_top);
                    transaction.replace(R.id.main_fragment, nowFragment);
                    transaction.commit();
                    keyTools=false;
                    imgTools.setImageResource(R.drawable.tabbar_tools);
                }
            }
        });
        imgContact = (ImageButton) findViewById(R.id.img_contact_page);
        imgContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgContact.setImageDrawable(getResources().getDrawable(R.drawable.tabbar_contact_highlighted));
                imgHome.setImageDrawable(getResources().getDrawable(R.drawable.tabbar_home));


                nowFragment = contactFragment;

                transaction = fm.beginTransaction();
                transaction.replace(R.id.main_fragment, contactFragment);
                transaction.commit();

            }
        });



    }


    /**
     * 初始化各种要使用fragment
     */

    private void initFragments() {
        homeFragment = new HomeFragment(this);
        contactFragment = new ContactFragment(this);
        toolsFragment = new ToolsFragment();
         fm = getSupportFragmentManager();
        nowFragment = homeFragment;

    }


    @Override
    public void onClick(View view) {

        if (view == leftbar_manager){
          //  changeFragment(new HomeFragment());
        }
        else if (view == leftbar_setting){
        //    changeFragment(new HomeFragment());
        }else if (view == leftbar_news){
         //   changeFragment(new HomeFragment());
        }else if (view == leftbar_clear){
         //   changeFragment(new HomeFragment());
        }else if (view == leftbar_news){
          //  changeFragment(new HomeFragment());
        }else if (view == leftbar_news){
         //   changeFragment(new HomeFragment());
        }

        //菜单关闭方法
        mresideMenu.closeMenu();
    }

    //可以监听打开和关闭的状态
    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
          //  Toast.makeText(context, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
         //   Toast.makeText(context, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    //重写dispatchTouchEvent方法
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mresideMenu.dispatchTouchEvent(ev);
    }

    private void setUpMenu() {
        mresideMenu = new ResideMenu(this);//侧滑菜单
        // attach to current activity;

//        resideMenu.setUse3D(true);
        mresideMenu.setBackground(R.color.leftbar_background);
        //绑定当前Activity
        mresideMenu.attachToActivity(this);
        //设置监听事件
        mresideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        //设置内容比例的缩放
        mresideMenu.setScaleValue(0.55f);

        // create menu items;
        leftbar_manager = new ResideMenuItem(this,leftbar_icon,"后台管理");
        leftbar_setting = new ResideMenuItem(this,leftbar_icon, "账户设置");
        leftbar_news = new ResideMenuItem(this, leftbar_icon,"新消息通知");
        leftbar_clear = new ResideMenuItem(this,leftbar_icon, "清除缓存");
        leftbar_help = new ResideMenuItem(this, leftbar_icon,"帮助反馈");
        leftbar_about = new ResideMenuItem(this,leftbar_icon, "关于");

        //设置点击事件及将刚创建的子菜单添加到侧换菜单栏中，通过常量来添加子菜单栏的位置
        leftbar_manager.setOnClickListener(this);
        leftbar_setting.setOnClickListener(this);
        leftbar_news.setOnClickListener(this);
        leftbar_clear.setOnClickListener(this);
        leftbar_help.setOnClickListener(this);
        leftbar_about.setOnClickListener(this);

        mresideMenu.addMenuItem(leftbar_manager, ResideMenu.DIRECTION_LEFT);
        mresideMenu.addMenuItem(leftbar_setting, ResideMenu.DIRECTION_LEFT);
        mresideMenu.addMenuItem(leftbar_news, ResideMenu.DIRECTION_LEFT);
        mresideMenu.addMenuItem(leftbar_clear, ResideMenu.DIRECTION_LEFT);
        mresideMenu.addMenuItem(leftbar_help, ResideMenu.DIRECTION_LEFT);
        mresideMenu.addMenuItem(leftbar_about, ResideMenu.DIRECTION_LEFT);



        // You can disable a direction by setting ->
        //去控制禁止一边的菜单栏开启
        mresideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        //设置了title button
//        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mresideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
//            }
//        });
    }

    // What good method is to access resideMenu？
    public  ResideMenu getResideMenu(){
        return mresideMenu;
    }

    private void changeFragment(Fragment targetFragment){
        mresideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }


}
