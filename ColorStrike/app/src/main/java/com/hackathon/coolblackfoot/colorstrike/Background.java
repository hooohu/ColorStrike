package com.hackathon.coolblackfoot.colorstrike;

/**
 * Created by chenghuh on 6/26/2015.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
        import android.graphics.Rect;
import java.util.*;

public class Background {
    int xMin, xMax, yMin, yMax;
    private Paint paintIn, paintOut;  // paint style and color
    List<MovingBall> mBalls;
    static int[] colorArray = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.LTGRAY};
    private final int BALL_NUM = 50;
    private ScoreBoard scoreBoard;
    public PatternBoard patternBoard;
    public static boolean isPattern = true;

    public Background(int xMin, int yMin, int xMax, int yMax, int colorIn, int colorOut) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        paintIn = new Paint();
        paintIn.setColor(colorIn);
        paintOut = new Paint();
        paintOut.setColor(colorOut);
        mBalls = new LinkedList<MovingBall>();
        initMBalls(this.BALL_NUM);
        scoreBoard = new ScoreBoard(10);
        patternBoard = new PatternBoard(7);
    }

    public void moveBalls() {
        for (MovingBall mB : this.mBalls) {
            mB.moveWithCollisionDetection(this);
        }
    }

    public void draw(Canvas canvas, DisplayArea disp, CentralBall cball) {
        // Draw the board
        canvas.drawRect(0, 0, disp.xMax, disp.yMax, paintIn);
        // JP: cancle collision
//        float dLeft = disp.xMax / 2 - cball.ballX;
//        float dRight = disp.xMax / 2 - (this.xMax - cball.ballX);
//        float dTop = disp.yMax / 2 - cball.ballY;
//        float dBottom = disp.yMax / 2 - (this.yMax - cball.ballY);
//        if (dLeft > 0) {
//            canvas.drawRect(0, 0, dLeft, disp.yMax, paintOut);
//        }
//        if (dRight > 0) {
//            canvas.drawRect(disp.xMax - dRight, 0, disp.xMax, disp.yMax, paintOut);
//        }
//        if (dTop > 0) {
//            canvas.drawRect(0, 0, disp.xMax, dTop, paintOut);
//        }
//        if (dBottom > 0) {
//            canvas.drawRect(0, disp.yMax - dBottom, disp.xMax, disp.yMax, paintOut);
//        }

        // Draw central ball
        cball.draw(canvas, disp);

        // Collision detection.
        cball.collisionDetection(this, scoreBoard);

        // Update the position of the ball, including collision detection and reaction.
        cball.moveWithCollisionDetection(this);

        // Draw other balls.
        for (MovingBall ball : mBalls) {
            ball.updateLocation(disp, cball);
            ball.draw(canvas, disp, cball);
        }

        // Update ball positions
        moveBalls();

        if (isPattern) patternBoard.drawPatternBoard(canvas);
        scoreBoard.drawScoreBoard(canvas);

        addOneBall(disp, cball);
    }

    public void initMBalls(int numOfBalls) {
        Random randomGenerator = new Random();
        for (int i = 0; i < numOfBalls; i++) {
            float x = randomGenerator.nextInt(this.xMax) + randomGenerator.nextFloat();
            if (x > xMax) x -= 1;
            float y = randomGenerator.nextInt(this.yMax) + randomGenerator.nextFloat();
            if (y > yMax) y -= 1;
            float radius = 20;
            int color = colorArray[randomGenerator.nextInt(colorArray.length)];
            float dx = randomGenerator.nextFloat() - (float)0.5;
            float dy = randomGenerator.nextFloat() - (float)0.5;
            MovingBall mB = new MovingBall(x, y, radius, color, 5, dx, dy);
            this.mBalls.add(mB);
        }
    }

    public void addOneBall(DisplayArea disp, CentralBall cBall) {
        if (this.mBalls.size() < this.BALL_NUM) {
            Random randomGenerator = new Random();
            float x = randomGenerator.nextInt(this.xMax) + randomGenerator.nextFloat();
            if (x > xMax) x -= 1;
            float y = randomGenerator.nextInt(this.yMax) + randomGenerator.nextFloat();
            if (y > yMax) y -= 1;

            // avoid putting new ball near central ball
            if (Math.abs(cBall.ballX - x) < (disp.xMax / 2)) x = cBall.ballX - (disp.xMax / 2);
            if (Math.abs(cBall.ballY - y) < (disp.yMax / 2)) y = cBall.ballY - (disp.yMax / 2);

            float radius = 20;
            int color = colorArray[randomGenerator.nextInt(colorArray.length)];
            float dx = randomGenerator.nextFloat() - (float)0.5;
            float dy = randomGenerator.nextFloat() - (float)0.5;
            MovingBall mB = new MovingBall(x, y, radius, color, 5, dx, dy);
            this.mBalls.add(mB);
        }
    }
}
