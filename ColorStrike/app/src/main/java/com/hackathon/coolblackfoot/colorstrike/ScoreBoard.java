package com.hackathon.coolblackfoot.colorstrike;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
/**
 * Created by chenghuh on 6/27/2015.
 */
public class ScoreBoard {
    int strike = 0;
    int score = 0;
    int life;
    Paint paint;

    public ScoreBoard(int life) {
        paint = new Paint();
        this.life = life;
    }

    public void reset() {
        strike = 0;
        score = 0;
    }

    public void addStrike() {
        strike++;
    }

    public void addScore() {
        score += (strike / 2) * strike * 100;
        strike = 0;
        life--;

    }

    public void drawScoreBoard(Canvas canvas) {
        String message = "Strike: " + Integer.toString(strike) + " Score: " + Integer.toString(score) + " Life: " + Integer.toString(life);
        if (life <= 0) message = "Game Over";
        paint.setTextSize(30);
        float width = paint.measureText(message);

        float sX = 2, sY = 2, eX = 6 + width, eY = 35, lX = 1, lY = 1;

        paint.setColor(Color.YELLOW);
        canvas.drawRect(sX, sY, eX, eY, paint);
        paint.setColor(Color.GRAY);
        canvas.drawRect(sX + lX, sY + lX, eX - lX, eY - lY, paint);

        paint.setColor(Color.RED);
        canvas.drawText(message, 4, eY - 4, paint);

    }
}
