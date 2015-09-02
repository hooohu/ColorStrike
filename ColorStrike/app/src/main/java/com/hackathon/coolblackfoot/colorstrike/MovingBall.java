package com.hackathon.coolblackfoot.colorstrike;

/**
 * Created by chenghuh on 6/26/2015.
 */

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class MovingBall {
    float radius;      // Ball's radius
    float x;  // Ball's center (x,y)
    float y;
    float speedX;       // Ball's speed (x,y)
    float speedY;
    RectF bounds;   // Needed for Canvas.drawOval
    Paint paint;                                                                                                                                                                                                                     // The paint style, color used for drawing

    // Constructor
    public MovingBall(float x, float y, float radius, int color, float speed, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        if (dx == 0 && dy == 0) {
            speedX = 0;
            speedY = 0;
        }
        else {
            speedX = (float) (speed * dx / Math.sqrt(dx * dx + dy * dy));
            speedY = (float) (speed * dy / Math.sqrt(dx * dx + dy * dy));
        }

        bounds = new RectF();
        paint = new Paint();
        paint.setColor(color);

    }

    public void moveWithCollisionDetection(Background back) {
        // Detect collision and react
        x += speedX;
        y += speedY;
    }

    public void draw(Canvas canvas, DisplayArea disp, CentralBall cball) {
        //If the balls are in display area
        float dX = x - cball.ballX;
        float dY = y - cball.ballY;

        float hWidth = (float)disp.xMax / 2;
        float hHeight = (float)disp.yMax / 2;

        boolean isInArea = true;
        if (dX + radius < - hWidth) isInArea = false;
        if (dX - radius > hWidth) isInArea = false;
        if (dY + radius < - hHeight) isInArea = false;
        if (dY - radius > hWidth) isInArea = false;
        //If in area, draw it

        if (isInArea) {
            bounds.set(hWidth + dX - radius, hHeight + dY - radius, hWidth + dX + radius, hHeight + dY + radius);
            canvas.drawOval(bounds, paint);
        }
    }

    public void drawStable(Canvas canvas) {
        bounds.set(x - radius, y - radius, x + radius, y + radius);
        canvas.drawOval(bounds, paint);
    }

    public void updateLocation(DisplayArea disp, CentralBall cball) {
        // update x coordinate
        if((x - cball.ballX) >= (disp.xMax * 1.5 - radius)) {
            x -= disp.xMax * 2;
        }else if ((cball.ballX - x) >= (disp.xMax * 1.5 - radius)) {
            x += disp.xMax * 2;
        }

        // update y coordinate
        if ((y - cball.ballY) >= (disp.yMax * 1.5 - radius)) {
            y -= disp.yMax * 2;
        }else if ((cball.ballY - y) >= (disp.xMax * 1.5 - radius)) {
            y += disp.yMax * 2;
        }
    }
}
