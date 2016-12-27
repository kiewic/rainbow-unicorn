package com.mocoyoyo.rainbowunicorn;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RainbowActivity extends AppCompatActivity {
    private int canvasColor = Color.MAGENTA;
    private float positionY;
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

                startPaintAnimation();
            }
        });
    }

    private void startPaintAnimation()
    {
        Random r = new Random();
        this.canvasColor = GetRandomColor();
        this.positionY = r.nextInt(this.imageView.getWidth());

        ValueAnimator timerAnimator = ValueAnimator.ofFloat(0f, 1f);
        timerAnimator.setDuration(TimeUnit.SECONDS.toMillis(1));
        timerAnimator.setInterpolator(new AccelerateInterpolator());

        timerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float)animation.getAnimatedValue();

                paintCanvas();

                imageView.invalidate();
            }
        });

        timerAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startPaintAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        timerAnimator.start();
    }

    private void paintCanvas()
    {
        if (canvas != null)
        {
            Paint paint = new Paint();
            paint.setColor(this.canvasColor);
            //Log.d("foo", Float.toString(paint.getColor()));
            Log.d("foo", Integer.toHexString(paint.getColor()));
            canvas.drawCircle(this.positionY, this.imageView.getHeight() * this.progress, 20, paint);
            imageView.setImageBitmap(bitmap);
        }
    }

    private int GetRandomColor()
    {
        @ColorInt final int colors[] = new int[] {
            0xFFFEDD12,
            0xFFEE7523,
            0xFFE72D92,
            Color.parseColor("#23B2A8"),
            Color.parseColor("#213468"),
            0xFFFF80AB,
            0xFFFF4081,
            0xFFF50057,
            0xFFC51162,
            0xFF82B1FF,
            0xFF448AFF,
            0xFF2979FF,
            0xFF2962FF
        };
        Random r = new Random();
        return colors[r.nextInt(colors.length)];
    }
}
