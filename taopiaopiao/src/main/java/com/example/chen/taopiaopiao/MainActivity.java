package com.example.chen.taopiaopiao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.txt_show_p).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (that == null){
                    Toast.makeText(MainActivity.this, "淘票票....", Toast.LENGTH_SHORT).show();
                    Intent intent =  new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(that, "淘票票....", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(that, SecondActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("key","1234");
                    intent.putExtra("data", bundle);
                    startActivity(intent);
                }
            }
        });
    }
}
