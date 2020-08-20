package com.chujian.ups.mtatest.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import java.io.ByteArrayOutputStream;

public class BitMapUtil {


    /**截图
     *
     * @param view
     * @return
     */
    public static Bitmap captureView(View view) {
        // 根据View的宽高创建一个空的Bitmap
        Bitmap bitmap = Bitmap.createBitmap(
                view.getWidth(),
                view.getHeight(),
                Bitmap.Config.RGB_565);
        // 利用该Bitmap创建一个空的Canvas
        Canvas canvas = new Canvas(bitmap);
        // 绘制背景(可选)
        canvas.drawColor(Color.WHITE);
        // 将view的内容绘制到我们指定的Canvas上
        view.draw(canvas);
        return bitmap;
    }

    /**
     * bitmap转byte数组
     * @param bmp
     * @param needRecycle
     * @return
     */
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

//    Decode an immutable bitmap from the specified byte array.
//    Bitmap  bitmap= BitmapFactory.decodeByteArray(bitmapBytes , 0, bitmapBytes.length);
    //设置缩略图
//    Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
//        bmp.recycle();
}
