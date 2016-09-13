package com.liruijie.testdownload;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    /**
     * 显示下载进度TextView
     */
    private TextView mMessageView;
    /**
     * 显示下载进度ProgressBar
     */
    private ProgressBar mProgressbar;

    private String downLoadUrl;//下载地址
    private String path;       //本地保存路径
    private String fileName;//文件名称


    /**
     * 使用Handler更新UI界面信息
     */
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            mProgressbar.setProgress(msg.getData().getInt("size"));

            float temp = (float) mProgressbar.getProgress()
                    / (float) mProgressbar.getMax();

            int progress = (int) (temp * 100);
            if (progress == 100) {
                Toast.makeText(MainActivity.this, "下载完成！", Toast.LENGTH_SHORT).show();
            }
            mMessageView.setText("下载进度:" + progress + " %");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressbar = (ProgressBar) findViewById(R.id.download_progress);
        mMessageView = (TextView) findViewById(R.id.download_message);


        downLoadUrl = "http://dl.genymotion.com/releases/genymotion-2.8.0/genymotion-2.8.0.dmg";

        // 获取SD卡路径
        path = Environment.getExternalStorageDirectory()
                + "/testDownLoad/";

        fileName = "genymotion-2.8.0.dmg";

    }

    public void startDownLoad(View view) {

        DownLoadUtils.doDownLoad(downLoadUrl, path, fileName, mHandler, mProgressbar);

    }

}
