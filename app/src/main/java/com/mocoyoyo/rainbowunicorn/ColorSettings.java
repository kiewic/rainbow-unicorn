package com.mocoyoyo.rainbowunicorn;

import android.support.annotation.ColorInt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ColorSettings {
    private static ColorSettings instance;
    public static ArrayList<Integer> selectedColors;

    @ColorInt private static final int allColors[] = new int[] {
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

    public static ColorSettings getInstance() {
        // TODO: Add a lock.
        if (instance == null) {
            instance = new ColorSettings();
        }
        return instance;
    }

    public ColorSettings() {
        selectedColors = new ArrayList<Integer>();
        for (int colorValue : allColors) {
            selectedColors.add(colorValue);
        }
    }

    public int[] getAllColors() {
        return allColors;
    }

    public ArrayList<Integer> getSelectedColors() {
        if (selectedColors == null) {
        }
        return selectedColors;
    }

    public static void select(@ColorInt int colorValue) {
        selectedColors.add(colorValue);
    }

    public static void deselect(@ColorInt int colorValue) {
        if (selectedColors.contains(colorValue)) {
            selectedColors.remove(selectedColors.indexOf(colorValue));
        }
    }
}
