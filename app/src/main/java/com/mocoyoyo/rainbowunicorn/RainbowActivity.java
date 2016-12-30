package com.mocoyoyo.rainbowunicorn;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.content.Intent;
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
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RainbowActivity extends AppCompatActivity {
    private final float cicleRadius = 20;
    private float lastPositionX = Float.NaN;
    private float lastPositionY = Float.NaN;
    private float positionY;
    private float progress;
    private Bitmap bitmap;
    private Canvas canvas;
    private ColorSettings colorSettings;
    private ImageView imageView;
    private Paint paint;

    public void showColorsPicker(View view) {
        Intent intent = new Intent(this, ColorsPickerActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rainbow);

        imageView = (ImageView) findViewById(R.id.myImageView);
        colorSettings = ColorSettings.getInstance();

        final ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Remove the listener, or we will be doing this a lot.
                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                bitmap = Bitmap.createBitmap(
                    imageView.getWidth(),
                    imageView.getHeight(),
                    Bitmap.Config.ARGB_8888);
                canvas = new Canvas(bitmap);
                imageView.setImageBitmap(bitmap);

                startPaintAnimation();
            }
        });
    }

    private void startPaintAnimation()
    {
        int canvasColor = GetRandomColor();
        this.paint = new Paint();
        this.paint.setColor(canvasColor);
        Random r = new Random();
        this.positionY = r.nextInt(this.imageView.getWidth());

        ValueAnimator timerAnimator = ValueAnimator.ofFloat(0f, 1f);
        timerAnimator.setDuration(TimeUnit.SECONDS.toMillis(1));
        timerAnimator.setInterpolator(new DecelerateInterpolator());

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
            //Log.d("foo", Float.toString(paint.getColor()));
            float positionX = this.imageView.getHeight() * this.progress;

            if (this.lastPositionY == this.positionY)
            {
                canvas.drawRect(
                    this.positionY - this.cicleRadius,
                    this.lastPositionX,
                    this.positionY + this.cicleRadius,
                    positionX,
                    paint);
            }

            canvas.drawCircle(this.positionY, positionX, 20, this.paint);

            this.lastPositionX = positionX;
            this.lastPositionY = this.positionY;
        }
    }

    private int GetRandomColor()
    {
        ArrayList<Integer> selectedColors = colorSettings.getSelectedColors();

        // If there are no colors selected, select WHITE.
        if (selectedColors.size() == 0)
        {
            return Color.WHITE;
        }

        Random r = new Random();
        return selectedColors.get(r.nextInt(selectedColors.size()));
    }
}
