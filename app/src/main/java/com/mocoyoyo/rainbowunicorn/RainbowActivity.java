package com.mocoyoyo.rainbowunicorn;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

public class RainbowActivity extends AppCompatActivity {
    private float progress;
    private Bitmap bitmap;
    private Canvas canvas;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rainbow);

        imageView = (ImageView) findViewById(R.id.myImageView);
        final ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Remove the listener, or we will be doing this a lot.
                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                Log.d("foo", "drawing!");
                bitmap = Bitmap.createBitmap(
                        imageView.getWidth(),
                        imageView.getHeight(),
                        Bitmap.Config.ARGB_8888);
                canvas = new Canvas(bitmap);
                doPaint();
            }
        });

        ValueAnimator timerAnimator = ValueAnimator.ofFloat(0f, 1f);
        timerAnimator.setDuration(TimeUnit.SECONDS.toMillis(5));
        timerAnimator.setInterpolator(new LinearInterpolator());
        timerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float)animation.getAnimatedValue();
                Log.d("foo", Float.toString(progress));
                imageView.invalidate();

                doPaint();
            }
        });
        timerAnimator.start();
    }

    private void doPaint()
    {
        if (canvas != null)
        {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.MAGENTA);
            canvas.drawCircle(100, imageView.getHeight() * progress, 20, paint);
            imageView.setImageBitmap(bitmap);
        }
    }
}
