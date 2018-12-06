package com.study.hidenavigationbar;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout bg_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bg_click = findViewById(R.id.bg_click);
        bg_click.setOnClickListener(this);

        //Set the system NavigationBar to be transparent
        new SystemStatusBarManager(this).setSystemStatusBarColor(R.color.colorTransparentinSys);//colorAccent //colorTransparent
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bg_click:
                changeBackground();
                break;
        }
    }

    private void changeBackground() {
        int [] arr = {R.drawable.wall_paper0,R.drawable.wall_paper1,R.drawable.wall_paper2,R.drawable.wall_paper3,R.drawable.wall_paper4,
                      R.drawable.wall_paper5,R.drawable.wall_paper6,R.drawable.wall_paper7,R.drawable.wall_paper8};
        int index=(int)(Math.random()*arr.length);
        int rand = arr[index];
        bg_click.setBackgroundResource(rand);
    }
}
