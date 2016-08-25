package rixin.app.officeauto.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rixin.app.officeauto.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_about);
        getSupportActionBar().hide();
    }
}
