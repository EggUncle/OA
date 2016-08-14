package rixin.app.officeauto.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.xlf.nrl.NsRefreshLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rixin.app.officeauto.R;
import rixin.app.officeauto.adapter.ContactBookRecycleAdapter;
import rixin.app.officeauto.myclass.PersonBean;
import rixin.app.officeauto.myclass.RecycleViewDivider;
import rixin.app.officeauto.myclass.XCRecyclerView;
import rixin.app.officeauto.util.PinyinComparator;
import rixin.app.officeauto.util.PinyinUtils;


/**
 * Created by egguncle on 16.8.3.
 * 联系人列表界面
 */
public class ContactBookFragment extends Fragment {
    private View view;
    private View headView;
    private XCRecyclerView rcvBook;
    private ProgressBar pbContactBook;
    private NsRefreshLayout nrlBook;

    private List<PersonBean> data;
    private ContactBookRecycleAdapter contactAdapter;

    private Context context;

    public ContactBookFragment() {

    }

    public ContactBookFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_contact_book, null);
        headView = inflater.inflate(R.layout.frag_contact_book_title, null);
        initView();
        //   initVars();
        MyAyncTask myAyncTask = new MyAyncTask();
        myAyncTask.execute();
        return view;
    }

    private void initView() {
        nrlBook = (NsRefreshLayout) view.findViewById(R.id.contact_book_nrl);
        nrlBook.setRefreshLayoutController(new NsRefreshLayout.NsRefreshLayoutController(){
            @Override
            public boolean isPullRefreshEnable() {
                return true;
            }

            @Override
            public boolean isPullLoadEnable() {
                return false;
            }
        });
        nrlBook.setRefreshLayoutListener(new NsRefreshLayout.NsRefreshLayoutListener() {
            @Override
            public void onRefresh() {
                nrlBook.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nrlBook.finishPullRefresh();

                        //  Toast.makeText(getActivity(), "下拉刷新", Toast.LENGTH_LONG).show();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                nrlBook.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nrlBook.finishPullLoad();

                        //     Toast.makeText(getActivity(), "上拉加载更多", Toast.LENGTH_LONG).show();
                    }
                }, 1000);
            }
        });

        pbContactBook = (ProgressBar) view.findViewById(R.id.contact_book_progress);
        rcvBook = (XCRecyclerView) view.findViewById(R.id.rcv_book);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcvBook.setLayoutManager(linearLayoutManager);
        rcvBook.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL));
        rcvBook.addHeaderView(headView);
    }

    private void initVars() {
       //用于调试的模拟数据
        data = getData(new String[]{"马云",
                "马化腾",
                "佩兰",
                "奥巴马",
                "普京",
                "金三胖",
                "暗影军团",
                "141特遣队",
                "冲锋号",
                "胜利号",
                "超级赛亚人",
                "金克拉",
                "亚古兽",
                "数码宝贝大冒险",
                "大胜归来",
                "道士下山",
                "A利亚克",
                "胜利冲锋龙卷风",
                "走在冷风中",
                "外面的世界",
                "烦恼歌",
                "如果没有你",
                "四季歌",
                "南山南",
                "胜利号",
                "光能使者",
                "铁甲小宝",
                "皮卡丘",
                "奥特曼",
                "开普勒452b",
                "神舟20号",
                "屌炸天的结尾"});
        // 数据在放在adapter之前需要排序
        Collections.sort(data, new PinyinComparator());
        contactAdapter = new ContactBookRecycleAdapter(data);
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

    //使用异步加载来加载联系人列表
    //第一个参数：启动任务时输入的参数类型.
    //第二个参数：后台任务执行中返回进度值的类型.
    //第三个参数：后台任务执行完成后返回结果的类型.
    class MyAyncTask extends AsyncTask<Void, Integer, Void> {


        public MyAyncTask() {
            super();
        }


        @Override
        protected void onPostExecute(Void v) {
            //执行后返回值
            super.onPostExecute(v);
            rcvBook.setAdapter(contactAdapter);
            pbContactBook.setVisibility(View.GONE);
            nrlBook.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPreExecute() {
            //执行前的初始化操作
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //更新时调用的操作

            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... params) {
            //后台加载时的操作

            initVars();

            return null;
        }

    }

}
