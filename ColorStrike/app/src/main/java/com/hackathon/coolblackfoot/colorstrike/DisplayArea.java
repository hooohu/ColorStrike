package com.hackathon.coolblackfoot.colorstrike;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by chenghuh on 6/26/2015.
 */
public class DisplayArea {
    int xMin = 0, xMax = 0, yMin = 0, yMax = 0;     // The paint style, color used for drawing

    public DisplayArea(int xMin, int xMax, int yMin, int yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public void setDisp(int xMax, int yMax) {
        this.xMax = xMax;
        this.yMax = yMax;
    }
}
