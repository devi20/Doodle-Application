package edu.csulb.android.doodleapp;



import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class DrawingView extends View {

    private Paint drawPaint = new Paint();
    private Path drawPath = new Path();
    Context ctx;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.ctx = context;
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5f);
        drawPaint.setColor(Color.BLUE);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(drawPath, drawPaint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(eventX, eventY);
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }
}
