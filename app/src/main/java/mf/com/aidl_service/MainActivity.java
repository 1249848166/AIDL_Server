package mf.com.aidl_service;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    LinearLayout container;
    Button refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refresh=findViewById(R.id.refresh);
        container=findViewById(R.id.container);

        SharedPreferences sp=getSharedPreferences("people",MODE_PRIVATE);
        Map<String,String> map= (Map<String, String>) sp.getAll();
        for(String s:map.keySet()){
            TextView tv=new TextView(this);
            tv.setText(s);
            container.addView(tv);
        }

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeAllViews();
                SharedPreferences sp=getSharedPreferences("people",MODE_PRIVATE);
                Map<String,String> map= (Map<String, String>) sp.getAll();
                for(String s:map.keySet()){
                    TextView tv=new TextView(MainActivity.this);
                    tv.setText(s);
                    container.addView(tv);
                }
            }
        });
    }
}
