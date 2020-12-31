package com.example.myopencvtest;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_test;
    private ImageView iv_lena;
    private boolean isInitOpenCVLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_lena = findViewById(R.id.iv_lena);

        //初始化
        isInitOpenCVLoader = OpenCVLoader.initDebug();

        btn_test = findViewById(R.id.btn_test);
        btn_test.setOnClickListener(this);

    }

    /**
     * 灰度化
     */
    private void convert2Grey() {
        Mat src = new Mat();//Mat是OpenCV的一种图像格式
        Mat temp = new Mat();
        Mat dst = new Mat();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.lena);
        Utils.bitmapToMat(bitmap, src);
        Imgproc.cvtColor(src, temp, Imgproc.COLOR_RGB2BGR);
        Imgproc.cvtColor(temp, dst, Imgproc.COLOR_BGR2GRAY);
        Utils.matToBitmap(dst, bitmap);
        iv_lena.setImageBitmap(bitmap);
        //
        src.release();
        temp.release();
        dst.release();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_test:
                if (isInitOpenCVLoader) {
                    convert2Grey();
                }else {
                    Toast.makeText(this, "OpenCVLoader 初始化失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}