package com.zhbit.administrator.video;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
//    private SurfaceHolder holder;
//    private Thread mythread;
//    private Canvas canvas;
//    URL videoUrl;
//    private String url;
//    private int w;
//    private int h;
//    HttpURLConnection conn;
//    Bitmap bmp;
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
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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


        //

//        url = getIntent().getExtras().getString("CameraIp");
//
//        w = getWindowManager().getDefaultDisplay().getWidth();
//        h = getWindowManager().getDefaultDisplay().getHeight();
//        SurfaceView surface = (SurfaceView)findViewById(R.id.surface);
//
//        surface.setKeepScreenOn(true);// 保持屏幕常亮
//        mythread = new Thread(this);
//        holder = surface.getHolder();
//        holder.addCallback(new Callback() {
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                // TODO Auto-generated method stub
//
//            }
//
//            public void surfaceCreated(SurfaceHolder holder) {
//                // TODO Auto-generated method stub
//                mythread.start();
//            }
//
//            public void surfaceChanged(SurfaceHolder holder, int format, int width,
//                                       int height) {
//                // TODO Auto-generated method stub
//
//            }
//        });

    }
//    private void draw() {
//        // TODO Auto-generated method stub
//        try {
//            InputStream inputstream = null;
//
//            //创建一个URL对象
//            url = "http://"+"192.168.199.171"+":"+"8080"+"/?action=snapshot";
//            videoUrl=new URL(url);
//            //利用HttpURLConnection对象从网络中获取网页数据
//            conn = (HttpURLConnection)videoUrl.openConnection();
//            //设置输入流
//            conn.setDoInput(true);
//            //连接
//            conn.connect();
//            //得到网络返回的输入流
//            inputstream = conn.getInputStream();
//            //创建出一个bitmap
//            bmp = BitmapFactory.decodeStream(inputstream);
//            canvas = holder.lockCanvas();
//            canvas.drawColor(Color.WHITE);
//            RectF rectf = new RectF(0, 0, w, h);
//            canvas.drawBitmap(bmp, null, rectf, null);
//            holder.unlockCanvasAndPost(canvas);
//            //关闭HttpURLConnection连接
//            conn.disconnect();
//        } catch (Exception ex) {
//        } finally {
//        }
//    }
//
//    @Override
//    public void run() {
//        // TODO Auto-generated method stub
//        while(true){
//            draw();
//        }
//    }


}