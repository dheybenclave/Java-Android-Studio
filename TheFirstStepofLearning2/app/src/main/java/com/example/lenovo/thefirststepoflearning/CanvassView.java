package com.example.lenovo.thefirststepoflearning;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dragonaire on 10/4/2017.
 */

public class CanvassView extends View {

    private Paint paint;
    private Path path;
    private Paint canvasPaint;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    public CanvassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();

        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(4f);
        this.paint.setColor(Color.BLACK);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
    }

    private void init() {
        this.paint = new Paint();
        this.path = new Path();
        this.canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    public void setStroke(float width) {
        this.paint.setStrokeWidth(width);
    }

    public void changeColor(String text)
    {
        if(text == "black")
        {
            this.paint.setColor(Color.BLACK);
            this.paint.setStrokeWidth(4f);
        }
        if(text == "white")
        {
            this.paint.setColor(Color.WHITE);
            this.paint.setStrokeWidth(30);

        }
        else if(text == "red")
        {
            this.paint.setColor(Color.RED);
            this.paint.setStrokeWidth(4f);
        }
        if(text == "yellow")
        {
            this.paint.setColor(Color.YELLOW);
            this.paint.setStrokeWidth(4f);
        }
        if(text == "blue")
        {
            this.paint.setColor(Color.BLUE);
            this.paint.setStrokeWidth(4f);
        }
        if(text == "green")
        {
            this.paint.setColor(Color.GREEN);
            this.paint.setStrokeWidth(4f);
        }
        if(text == "magenta")
        {
            this.paint.setColor(Color.MAGENTA);
            this.paint.setStrokeWidth(4f);
        }
    }
    public void clearCanvas()
    {
        path.reset();
        invalidate();
    }

    public void reset() {
        this.drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        this.drawCanvas = new Canvas(this.canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(this.canvasBitmap, 0, 0, this.canvasPaint);
        canvas.drawPath(this.path, this.paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.path.moveTo(eventX, eventY);
                break;

            case MotionEvent.ACTION_MOVE:
                this.path.lineTo(eventX, eventY);
                break;

            case MotionEvent.ACTION_UP:
                this.drawCanvas.drawPath(this.path, this.paint);
                this.path.reset();
                break;

            default:
                return false;
        }

        invalidate();
        return true;
    }
}
