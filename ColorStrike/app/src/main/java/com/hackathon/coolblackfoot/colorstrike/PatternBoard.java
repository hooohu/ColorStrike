package com.hackathon.coolblackfoot.colorstrike;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.*;

/**
 * Created by hechh on 6/27/15.
 */
public class PatternBoard {
    Paint paint;
    List<MovingBall> patternList;
    int patternNum;

    public PatternBoard(int patternNum) {
        this.paint = new Paint();
        this.patternList = new LinkedList<MovingBall>();
        this.patternNum = patternNum;
        initPattern();
    }

    private void initPattern() {
        for (int i = 0; i <  patternNum; i++) {
            Random randomGenerator = new Random();
            int c = Background.colorArray[randomGenerator.nextInt(Background.colorArray.length)];
            MovingBall pBall = new MovingBall(0, 0, 20, c, 0, 0, 0);
            patternList.add(pBall);
        }
        setListLocation();
    }

    private void setListLocation() {
        float sX = 2;
        float sY = 36 + 2;
        float lX = 1;
        float lY = 1;
        float ballW = 40;
        int i = patternList.size() - 1;
        for (MovingBall pBall: patternList) {
            pBall.x = sX + 20;
            pBall.y = sY + 20 + i * (ballW + lY);
            i --;
        }
    }

    public MovingBall peekPattern() {
        return this.patternList.get(0);
    }

    public void addPattern() {
        this.patternList.remove(0);
        Random randomGenerator = new Random();
        int c = Background.colorArray[randomGenerator.nextInt(Background.colorArray.length)];
        MovingBall pBall = new MovingBall(0, 0, 20, c, 0, 0, 0);
        patternList.add(pBall);
        setListLocation();
    }

    public void drawPatternBoard(Canvas canvas) {
        float ballW = 40;
        float lX = 1;
        float lY = 1;
        float sX = 2 - lX;
        float sY = 36 + 2 - lY;
        float eX = sX + ballW + 2 + lX;
        float eY = sY + ballW * patternNum + patternNum * lY + 1 + lY;

        paint.setColor(Color.YELLOW);
        canvas.drawRect(sX, sY, eX, eY, paint);
        paint.setColor(Color.GRAY);
        canvas.drawRect(sX + lX, sY + lX, eX - lX, eY - lY, paint);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        canvas.drawLine(sX, eY, eX, eY, paint);
        canvas.drawLine(eX, eY, eX, eY - ballW - 2 * lY, paint);
        canvas.drawLine(eX, eY - ballW - 2 * lY, sX, eY - ballW - 2 *lY, paint);
        canvas.drawLine(sX, eY - ballW - 2 *lY, sX, eY, paint);

        for (MovingBall pBall: this.patternList) {
            pBall.drawStable(canvas);
        }
    }
}
