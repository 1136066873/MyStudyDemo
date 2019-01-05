package com.haier.demo.addemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.newest).setOnClickListener(this);
        findViewById(R.id.newer).setOnClickListener(this);
        findViewById(R.id.older_1).setOnClickListener(this);
        findViewById(R.id.older_2).setOnClickListener(this);
        findViewById(R.id.older_3).setOnClickListener(this);

        TurnCardListView list = (TurnCardListView) findViewById(R.id.card_list);

        list.setOnTurnListener(new TurnCardListView.OnTurnListener() {
            @Override
            public void onTurned(int position) {
                Toast.makeText(MainActivity.this, "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "position = " + view.getId(), Toast.LENGTH_SHORT).show();
            }
        });


        list.setAdapter(new BaseAdapter() {
            int[] colors = {0xffFF9800, 0xff3F51B5, 0xff673AB7, 0xff006064, 0xffC51162, 0xffFFEB3B, 0xff795548, 0xff9E9E9E};

            @Override
            public int getCount() {
                return colors.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View child, ViewGroup parent) {
                if (child == null) {
                    child = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
                }

                ((TextView)child.findViewById(R.id.pos)).setText("" + position);
                child.findViewById(R.id.image).setBackgroundColor(colors[position]);
                return child;
            }
        });

    }


    boolean isBottom;

    @Override
    public void onClick(View v) {
        TurnCardListView cardList = (TurnCardListView) findViewById(R.id.card_list);
        switch (v.getId()) {
            case R.id.newest:
                //            cardList.setAdapter(cardList.getAdapter());
                cardList.turnTo(0);
                break;
            case R.id.newer:
                cardList.turnBy(-1);
                break;
            case R.id.older_1:
                cardList.turnBy(1);
                break;
            case R.id.older_2:
                cardList.turnBy(2);
                break;
            case R.id.older_3:
                cardList.turnBy(3);
                break;
        }

    }
}
