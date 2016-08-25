package rixin.app.officeauto.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import rixin.app.officeauto.R;

public class HelpActivity extends AppCompatActivity {

    private TextView tvSuggtion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_help);
        getSupportActionBar().hide();
        initView();
    }

    private void initView(){
        tvSuggtion = (TextView) findViewById(R.id.tv_suggtion);
        tvSuggtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelpActivity.this,SuggestionActivity.class);
                startActivity(intent);
            }
        });
    }
}
