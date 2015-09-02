package com.hackathon.coolblackfoot.colorstrike;

/**
 * Created by itinchou on 6/26/2015.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.MotionEvent;

public class BouncingBallView extends View {

    private Background background;
    private DisplayArea displayArea;
    private CentralBall centralBall;
    public static boolean isInitBackground = false;

    // Constructor
    public BouncingBallView(Context context) {
        super(context);

        displayArea = new DisplayArea(0, 0, 0, 0);

        centralBall = new CentralBall(0, 0, 20, 10, 0, 0);
    }

    // Called back to draw the view. Also called by invalidate().
    @Override
    protected void onDraw(Canvas canvas) {

        // Draw the board
        background.draw(canvas, displayArea, centralBall);

        // Delay
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {}

        invalidate();  // Force a re-draw
    }

    // Called back when the view is first created or its size changes.
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        displayArea.setDisp(w-1, h-1);
        if (!isInitBackground) {
            isInitBackground = true;
            background = new Background(0, 0, displayArea.xMax, displayArea.yMax, Color.BLACK, Color.YELLOW);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed
        float width = displayArea.xMax / 2;
        float height = displayArea.yMax / 2;
        float x = e.getX(); // convert piel coordinates to canvas coordinates
        float y = e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - width;
                float dy = y - height;
                centralBall.setSpeed(10, dx, dy);
                if (dx > 0 && dy > 0) {
                    if (centralBall.ballSpeedX < 0) {
                        centralBall.ballSpeedX = -centralBall.ballSpeedX;
                    }
                    if (centralBall.ballSpeedY < 0) {
                        centralBall.ballSpeedY = -centralBall.ballSpeedY;
                    }
                }
                if (dx > 0 && dy < 0) {
                    if (centralBall.ballSpeedX < 0) {
                        centralBall.ballSpeedX = -centralBall.ballSpeedX;
                    }
                    if (centralBall.ballSpeedY > 0) {
                        centralBall.ballSpeedY = -centralBall.ballSpeedY;
                    }
                }
                if (dx < 0 && dy > 0) {
                    if (centralBall.ballSpeedX > 0) {
                        centralBall.ballSpeedX = -centralBall.ballSpeedX;
                    }
                    if (centralBall.ballSpeedY < 0) {
                        centralBall.ballSpeedY = -centralBall.ballSpeedY;
                    }
                }
                if (dx < 0 && dy < 0) {
                    if (centralBall.ballSpeedX > 0) {
                        centralBall.ballSpeedX = -centralBall.ballSpeedX;
                    }
                    if (centralBall.ballSpeedY > 0) {
                        centralBall.ballSpeedY = -centralBall.ballSpeedY;
                    }
                }
        }
        return true;
    }
}

