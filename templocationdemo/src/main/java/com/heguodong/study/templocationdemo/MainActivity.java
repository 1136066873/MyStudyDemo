package com.heguodong.study.templocationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv ;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn:

/*                String timeZone = GetTimeZoneFromIP.getTheAutoTimeZone();
                tv.setText(timeZone);*/

                MyLocationHelper.getInstance(this).GetCurrentLatitudeAndLongitude();
                break;
        }

    }
}
