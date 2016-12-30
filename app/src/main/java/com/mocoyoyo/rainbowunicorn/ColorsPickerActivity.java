package com.mocoyoyo.rainbowunicorn;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.ColorInt;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ColorsPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors_picker);

        GridLayout gridLayout = (GridLayout) this.findViewById(R.id.main_grid_layout);

        // An alternative way to define a color is: Color.parseColor("#23B2A8")
        @ColorInt final int colors[] = new int[]
                {
                        // Pink
                        0xFFE72D92,
                        0xFFFF80AB,
                        0xFFFF4081,
                        0xFFF50057,
                        0xFFC51162,
                        // Blue
                        0xFF82B1FF,
                        0xFF448AFF,
                        0xFF2979FF,
                        0xFF2962FF,
                        0xFF213468,
                        // Green
                        0xFF00E676,
                        0xFF00C853,
                        0xFF23B2A8,
                        // Yellow-Orange
                        0xFFFEDD12,
                        0xFFFFD740,
                        0xFFFFC400,
                        0xFFFFAB00,
                        0xFFEE7523,
                };

        for (int colorValue : colors) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ic_check_white);
            imageView.setPadding(50, 50, 50, 50);
            imageView.setBackgroundColor(colorValue);
            imageView.setTag(true);

            imageView.setLayoutParams(new ActionBar.LayoutParams(200, 200));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView innerImageView = (ImageView)v;

                    boolean isSelected = (boolean)v.getTag();
                    if (isSelected)
                    {
                        innerImageView.setImageResource(android.R.color.transparent);
                    }
                    else
                    {
                        innerImageView.setImageResource(R.drawable.ic_check_white);
                    }

                    innerImageView.setTag(!isSelected);
                }
            });

            gridLayout.addView(imageView);
        }

        TextView text1 = new TextView(this);
        text1.setText("hello world");

    }
}
