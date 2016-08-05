package rixin.app.officeauto.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rixin.app.officeauto.R;
import rixin.app.officeauto.adapter.ContactBookRecycleAdapter;
import rixin.app.officeauto.myclass.PersonBean;
import rixin.app.officeauto.myclass.RecycleViewDivider;
import rixin.app.officeauto.util.PinyinComparator;
import rixin.app.officeauto.util.PinyinUtils;
import rixin.app.officeauto.util.SideBar;

/**
 * Created by egguncle on 16.8.3.
 */
public class ContactBookFragment extends Fragment{
    private View view;
    private RecyclerView recyclerView;


    private List<PersonBean> data;
    private ContactBookRecycleAdapter contactAdapter;

    private Context context;

    public ContactBookFragment(){

    }

    public ContactBookFragment(Context context){
        this.context =context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_contact_book, null);
        initView();




       data=getData(new String[]{"马云",
               "马化腾" ,
               "佩兰" ,
               "奥巴马" ,
               "普京" ,
               "金三胖" ,
               "暗影军团" ,
               "141特遣队" ,
               "冲锋号" ,
               "胜利号" ,
               "超级赛亚人" ,
               "金克拉" ,
               "亚古兽" ,
               "数码宝贝大冒险" ,
               "大胜归来" ,
               "道士下山" ,
               "A利亚克" ,
               "胜利冲锋龙卷风" ,
               "走在冷风中" ,
               "外面的世界" ,
               "烦恼歌" ,
               "如果没有你" ,
               "四季歌" ,
               "南山南" ,
               "胜利号" ,
               "光能使者" ,
               "铁甲小宝",
               "皮卡丘" ,
               "奥特曼" ,
               "开普勒452b" ,
               "神舟20号" ,
               "屌炸天的结尾"});
        // 数据在放在adapter之前需要排序
        Collections.sort(data, new PinyinComparator());
//        for (int i = 0; i < data.size(); i++) {
//            Log.i("wxl", "Fpinyin=" + data.get(i).getFirstPinYin());
//        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL));
        contactAdapter = new ContactBookRecycleAdapter(data);
        recyclerView.setAdapter(contactAdapter);

        return view;
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_book);
    }

    private List<PersonBean> getData(String[] data) {
        List<PersonBean> listarray = new ArrayList<PersonBean>();
        for (int i = 0; i < data.length; i++) {
            String pinyin = PinyinUtils.getPingYin(data[i]);
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();

            PersonBean person = new PersonBean();
            person.setName(data[i]);
            person.setPinYin(pinyin);
            // 正则表达式，判断首字母是否是英文字母
            if (Fpinyin.matches("[A-Z]")) {
                person.setFirstPinYin(Fpinyin);
            } else {
                person.setFirstPinYin("#");
            }

            listarray.add(person);
        }

        return listarray;

    }
}
