package com.liruijie.testdownload;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.File;

/**
 * Created by liruijie on 16/9/12.
 */
public class DownLoadUtils {


    /**
     * @param downLoadUrl 下载地址
     * @param path        本地保存路径
     * @param fileName    文件名称
     * @param handler     更新UI媒介
     * @param progressBar 进度条
     */
    public static void doDownLoad(String downLoadUrl, String path, String fileName, Handler handler, ProgressBar progressBar) {

        File file = new File(path);
        // 如果SD卡目录不存在创建
        if (!file.exists()) {
            file.mkdir();
        }

        // 设置progressBar初始化
        progressBar.setProgress(0);

        int threadNum = 5;
        String filepath = path + fileName;
        DownloadTask task = new DownloadTask(downLoadUrl, threadNum, filepath, handler, progressBar);
        task.start();

    }


}
