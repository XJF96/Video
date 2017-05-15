package com.zhbit.administrator.video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    private Button connect;
    private EditText ip_edittext;
    private EditText port_edittext;
    private String ip;
    private String port;
    private Bundle data;
    private Intent intent;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connect = (Button) findViewById(R.id.button) ;
        ip_edittext = (EditText) findViewById(R.id.EditText_Ip);
        port_edittext = (EditText) findViewById(R.id.EditText_Port);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip = ip_edittext.getText().toString();
                port = port_edittext.getText().toString();

                data = new Bundle();
                intent = new Intent();

                data.putString("ip",ip);
                data.putString("port",port);

                intent.setClass(MainActivity.this,Video.class);
                intent.putExtra("data",data);
                startActivityForResult(intent,0);
            }
        });

    }
}