package com.mocoyoyo.rainbowunicorn;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.ColorInt;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class ColorsPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors_picker);

        GridLayout gridLayout = (GridLayout) this.findViewById(R.id.main_grid_layout);


        @ColorInt final int colors[] = new int[]
                {
                        // Yellow, Orange, Pink, Turquoise, Marine Blue
                        0xFFFEDD12,
                        0xFFEE7523,
                        0xFFE72D92,
                        Color.parseColor("#23B2A8"),
                        Color.parseColor("#213468"),
                        // Pink
                        0xFFFF80AB,
                        0xFFFF4081,
                        0xFFF50057,
                        0xFFC51162,
                        // Blue
                        0xFF82B1FF,
                        0xFF448AFF,
                        0xFF2979FF,
                        0xFF2962FF,
                        // Green
                        0xFF00E676,
                        0xFF00C853,
                        // Yellow-Orange
                        0xFFFFD740,
                        0xFFFFC400,
                        0xFFFFAB00
                };

        for (int colorValue : colors) {
            //Shape shape = new Shape() {
            //    @Override
            //    public void draw(Canvas canvas, Paint paint) {
            //
            //    }
            //};

            View view = new View(this);
            view.setLayoutParams(new ActionBar.LayoutParams(200, 200));
            view.setBackgroundColor(colorValue);

            gridLayout.addView(view);
        }

        TextView text1 = new TextView(this);
        text1.setText("hello world");

    }
}
