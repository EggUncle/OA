package rixin.app.officeauto.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rixin.app.officeauto.R;
import rixin.app.officeauto.myclass.ToolsButton;


public class ToolsFragment extends Fragment {

    private ToolsButton mButton1;
    private ToolsButton mButton2;
    private ToolsButton mButton3;
    private ToolsButton mButton4;
    private ToolsButton mButton5;
    private ToolsButton mButton6;
    private ToolsButton mButton7;
    private ToolsButton mButton8;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.act_tools, container, false);

        mButton1 = (ToolsButton) view.findViewById(R.id.button1);
        //设置botton的图片
        mButton1.setImage(R.drawable.tools_announce);
        //设置botton的text
        mButton1.setText("公告");
        mButton2 = (ToolsButton) view.findViewById(R.id.button2);
        mButton2.setImage(R.drawable.tools_attendance);
        mButton2.setText("考勤");
        mButton3 = (ToolsButton) view.findViewById(R.id.button3);
        mButton3.setImage(R.drawable.tools_check);
        mButton3.setText("审批");
        mButton4 = (ToolsButton) view.findViewById(R.id.button4);
        mButton4.setImage(R.drawable.tools_activity);
        mButton4.setText("活动");
        mButton5 = (ToolsButton) view.findViewById(R.id.button5);
        mButton5.setImage(R.drawable.tools_report);
        mButton5.setText("工作汇报");
        mButton6 = (ToolsButton) view.findViewById(R.id.button6);
        mButton6.setImage(R.drawable.tools_meeting);
        mButton6.setText("会议");
        mButton7 = (ToolsButton) view.findViewById(R.id.button7);
        mButton7.setImage(R.drawable.tools_examine);
        mButton7.setText("培训");
        mButton8 = (ToolsButton) view.findViewById(R.id.button8);
        mButton8.setImage(R.drawable.tools_train);
        mButton8.setText("考核");

        return view;
    }
}
