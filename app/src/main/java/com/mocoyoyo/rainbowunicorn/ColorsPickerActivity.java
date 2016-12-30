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

import java.util.ArrayList;

public class ColorsPickerActivity extends AppCompatActivity {
    private class ColorButtonTag {
        @ColorInt public int colorValue;
        public boolean isSelected;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors_picker);

        GridLayout gridLayout = (GridLayout) this.findViewById(R.id.main_grid_layout);

        // An alternative way to define a color is: Color.parseColor("#23B2A8")

        final ColorSettings colorSettings = ColorSettings.getInstance();
        ArrayList<Integer> selectedColors = colorSettings.getSelectedColors();
        for (int colorValue : colorSettings.getAllColors()) {
            ImageView imageView = new ImageView(this);
            imageView.setPadding(50, 50, 50, 50);
            imageView.setBackgroundColor(colorValue);

            ColorButtonTag tag = new ColorButtonTag();
            tag.colorValue = colorValue;
            tag.isSelected = selectedColors.contains(colorValue);
            imageView.setTag(tag);

            setImageViewTick(imageView, tag.isSelected);

            imageView.setLayoutParams(new ActionBar.LayoutParams(200, 200));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView innerImageView = (ImageView)v;
                    ColorButtonTag innerTag = (ColorButtonTag) v.getTag();

                    innerTag.isSelected = !innerTag.isSelected;
                    if (innerTag.isSelected) {
                        colorSettings.select(innerTag.colorValue);
                    }
                    else {
                        colorSettings.deselect(innerTag.colorValue);
                    }

                    setImageViewTick(innerImageView, innerTag.isSelected);
                }
            });

            gridLayout.addView(imageView);
        }
    }

    private void setImageViewTick(ImageView imageView, boolean isSelected) {
        if (isSelected) {
            imageView.setImageResource(R.drawable.ic_check_white);
        }
        else {
            imageView.setImageResource(android.R.color.transparent);
        }
    }
}
