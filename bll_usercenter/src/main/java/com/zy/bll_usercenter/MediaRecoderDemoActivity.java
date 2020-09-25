package com.zy.bll_usercenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.zy.common.log.LogUtils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class MediaRecoderDemoActivity extends AppCompatActivity  implements SurfaceHolder.Callback{
    private SurfaceView svSurfaceview;
    private Button btnVideoStartStop;
    private Button btnVideoPlay;

    //是否开始录制
    private boolean isStart=false;

    private MediaRecorder mRecorder;
    private Camera camera;
    private SurfaceHolder mSurfaceHolder;
    private MediaPlayer mediaPlayer;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_recoder_demo);

        requestPermissions2();

        initView();
        initEvent();


    }

    private void requestPermissions2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },0);
        }
    }

    private void initEvent() {
        btnVideoStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始录制
                if (!isStart){
                    if (mRecorder == null) {
                        //创建MediaRecorder
                        mRecorder = new MediaRecorder();
                    }

                    //打开相机 后置摄像头 并获取到camera实例
                    camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                    if (camera != null) {
                        //调整相机显示的角度为90度
                        camera.setDisplayOrientation(90);
                        //解锁相机
                        camera.unlock();
                        //将相机对象传递给MediaRecoder
                        mRecorder.setCamera(camera);

                        try {
                            // 这两项需要放在setOutputFormat之前
                            //设置音频的源
                            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                            //设置视频源
                            mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

                            // 指定输出格式MPEG_4
//                            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

                            // 这两项需要放在setOutputFormat之后
                            //设置音频编码器
//                            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                            //设置视频编码
//                            mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);

                            //设置视频的宽高
//                            mRecorder.setVideoSize(640, 480);
//                            //设置帧率 1秒30帧
//                            mRecorder.setVideoFrameRate(60);
//                            mRecorder.setVideoEncodingBitRate(1* 1024 * 1024);
                            mRecorder.setOrientationHint(90);
//                            //设置记录会话的最大持续时间（毫秒）
//                            mRecorder.setMaxDuration(30 * 1000);



                            mRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_1080P));

                            //设置预览图像显示到SurfaceView
                            mRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());

                            //定义保存录制视频的路径
                            path = getSDPath();
                            if (path != null) {

                                File dir = new File(path);
//                                Uri uriForFile = FileProvider.getUriForFile(MediaRecoderDemoActivity.this, "com.zy.bawei4_1804.fileprovider", dir);
//                                File file = new File(uriForFile.getPath());
                                if (!dir.exists()) {
                                    dir.mkdir();
                                }
                                //最终录制后的视频文件存储位置
                                path = dir.getAbsolutePath() + "/" + getDate() + ".mp4";
                                Log.d("123","video file save path="+path);
                                //视频输出
                                mRecorder.setOutputFile(path);
                                //录制前的准备
                                mRecorder.prepare();
                                //开始录制
                                mRecorder.start();
                                btnVideoStartStop.setText("停止");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        isStart=true;
                    }
                }
                //结束录制
                else{
                    try {
                        mRecorder.stop();
                        mRecorder.reset();
                        mRecorder.release();
                        mRecorder = null;
                        btnVideoStartStop.setText("开始");
                        if (camera != null) {
                            camera.release();
                            camera = null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    isStart=false;
                }
            }
        });

        btnVideoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                }
                mediaPlayer.reset();
                Uri uri = Uri.parse(path);
                mediaPlayer = MediaPlayer.create(MediaRecoderDemoActivity.this, uri);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDisplay(mSurfaceHolder);
                try{
                    if (mediaPlayer != null) {

                        mediaPlayer.stop();

                    }
                    mediaPlayer.prepare();
                }catch (Exception e){
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }
        });

        SurfaceHolder holder = svSurfaceview.getHolder();
        holder.addCallback(this);
        // setType必须设置，要不出错.
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    private void initView() {
        svSurfaceview = (SurfaceView) findViewById(R.id.sv_surfaceview);
        btnVideoStartStop = (Button) findViewById(R.id.btn_video_start_stop);
        btnVideoPlay = (Button) findViewById(R.id.btn_video_play);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurfaceHolder = holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // 将holder，这个holder为开始在onCreate里面取得的holder，将它赋给mSurfaceHolder
        mSurfaceHolder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        svSurfaceview = null;
        mSurfaceHolder = null;
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
            Log.d("123", "surfaceDestroyed release mRecorder");
        }
        if (camera != null) {
            camera.release();
            camera = null;
        }
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public static String getDate() {
        Calendar ca = Calendar.getInstance();
        // 获取年份
        int year = ca.get(Calendar.YEAR);
        // 获取月份
        int month = ca.get(Calendar.MONTH);
        // 获取日
        int day = ca.get(Calendar.DATE);
        // 分
        int minute = ca.get(Calendar.MINUTE);
        // 小时
        int hour = ca.get(Calendar.HOUR);
        // 秒
        int second = ca.get(Calendar.SECOND);

        String date = "" + year + (month + 1) + day + hour + minute + second;
        Log.d("123", "date:" + date);

        return date;
    }

    /**
     * 获取SD path
     *
     * @return
     */
    public String getSDPath() {
        File sdDir = null;

        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);
        // 判断sd卡是否存在
        if (sdCardExist) {
            // 获取跟目录
            sdDir = getExternalCacheDir().getAbsoluteFile();//Environment.getExternalStorageDirectory();
            return sdDir.toString();
        }

        return null;
    }
}