package rixin.app.officeauto.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rixin.app.officeauto.R;

public class NoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_notice);
        getSupportActionBar().hide();
    }
}
