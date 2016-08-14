package rixin.app.officeauto.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Switch;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import rixin.app.officeauto.R;
import rixin.app.officeauto.activity.MainActivity;
import rixin.app.officeauto.myclass.CircleImageView;
import rixin.app.officeauto.myclass.Msg;
import rixin.app.officeauto.myclass.ResideMenu;

/**
 * Created by egguncle on 16.8.3.
 * 联系人界面和消息界面共同的标题栏
 */
public class ContactFragment extends Fragment {

    private View view;
    private Context context;
    private PopupMenu popupMenu;

    private FragmentManager fm;
    private FragmentTransaction transaction;
    private ContactMessageFragment contactMessageFragment;
    private ContactBookFragment contactBookFragment;
    private List<Msg> messageData;

    private CircleImageView titleIvIcon;
    private Switch titleSwitch;
    private ImageButton titleImgMenu;

    private ResideMenu resideMenu;


    public ContactFragment() {

    }

    public ContactFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_contact,null);

        //模拟数据
        setSimulationData();

        initView();
        initVar();
        return view;
    }

    private void initView(){
        MainActivity parentActivity = (MainActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();

        titleIvIcon = (CircleImageView) view.findViewById(R.id.title_iv_icon);
        titleIvIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });


        titleSwitch = (Switch) view.findViewById(R.id.title_switch);
        titleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b){
                    transaction = fm.beginTransaction();
                    //   transaction.replace(R.id.fragment_contact, contactMessageFragment);
                    transaction.replace(R.id.fragment_contact, contactMessageFragment);
                    transaction.commit();
                }else {
                    transaction = fm.beginTransaction();
                    //   transaction.replace(R.id.fragment_contact, contactMessageFragment);
                    transaction.replace(R.id.fragment_contact, contactBookFragment);
                    transaction.commit();
                }
            }
        });
        titleImgMenu = (ImageButton) view.findViewById(R.id.title_img_menu);
        titleImgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //右上角菜单
                popupMenu = new PopupMenu(context, titleImgMenu);
                popupMenu.getMenuInflater().inflate(R.menu.pop_window, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        return true;
                    }
                });
                try {
                    //使用反射来显示图标
                    Class<?> classPopupMenu = Class.forName(popupMenu.getClass()
                            .getName());
                    Field popup = classPopupMenu.getDeclaredField("mPopup");
                    popup.setAccessible(true);
                    Object menuPopupHelper = popup.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper
                            .getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod(
                            "setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                popupMenu.show();
            }
        });
    }


    private void initVar() {
        contactMessageFragment = new ContactMessageFragment(context,messageData);
        contactBookFragment = new ContactBookFragment(context);

        fm = getChildFragmentManager();
        transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_contact, contactMessageFragment);
        transaction.commit();
//        transaction.replace(R.id.fragment_contact, contactBookFragment);
//        transaction.commit();

    }


    //设置模拟数据测试模块效果
    private void setSimulationData() {
        messageData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Msg msg = new Msg();
            msg.setStrContent("content" + " " + i);
            msg.setStrName("name" + " " + i);
            msg.setStrDate("date" + " " + i);
            messageData.add(msg);
        }
    }




}
