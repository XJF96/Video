package com.zhbit.administrator.video;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class Video extends Activity implements Runnable{
    private Intent intent;
    private Bundle data;
    private SurfaceHolder holder;
    private Thread mythread;
    private Canvas canvas;
    URL videoUrl;
    private String url;
    private int w;
    private int h;
    HttpURLConnection conn;
    Bitmap bmp;
    private String ip;
    private String port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏

        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        url = getIntent().getExtras().getString("CameraIp");

        w = getWindowManager().getDefaultDisplay().getWidth();
        h = getWindowManager().getDefaultDisplay().getHeight();
        SurfaceView surface = (SurfaceView)findViewById(R.id.surface);

        surface.setKeepScreenOn(true);// 保持屏幕常亮
        mythread = new Thread(this);
        holder = surface.getHolder();//通过 getHolder()方法可以得到这个 SurfaceHolder对象
        holder.addCallback(new SurfaceHolder.Callback() {
            public void surfaceDestroyed(SurfaceHolder holder) {//surfaceview的对象销毁时调用
                // TODO Auto-generated method stub

            }

            public void surfaceCreated(SurfaceHolder holder) {//surfaceView被激发是自动调用
                // TODO Auto-generated method stub
                mythread.start();
            }

            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                       int height) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void draw(String ip,String port) {
        // TODO Auto-generated method stub
        try {
            InputStream inputstream = null;

            //创建一个URL对象
            url = "http://" + ip + ":" + port + "/?action=snapshot";
            videoUrl=new URL(url);
            //利用HttpURLConnection对象从网络中获取网页数据
            conn = (HttpURLConnection)videoUrl.openConnection();
            //设置输入流
            conn.setDoInput(true);

            //连接
            conn.connect();
            //得到网络返回的输入流
            inputstream = conn.getInputStream();

            //创建出一个bitmap
            bmp = BitmapFactory.decodeStream(inputstream);
            canvas = holder.lockCanvas();// 获取画布
            canvas.drawColor(Color.WHITE);//背景颜色
            RectF rectf = new RectF(0, 0, w, h);//左上右下坐标，占满

            //bmp要绘制的位图对象，不裁剪，图片显示的区域，不要画笔
            canvas.drawBitmap(bmp, null, rectf, null);
            holder.unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像

            //关闭HttpURLConnection连接
            conn.disconnect();
        } catch (Exception ex) {
        } finally {
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        intent = getIntent();
        data = intent.getBundleExtra("data");
        ip = data.getString("ip");
        port = data.getString("port");
        while(true){
            draw(ip,port);
        }
    }

}
