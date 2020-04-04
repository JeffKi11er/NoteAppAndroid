package com.t3h.myprojectnoteupdate.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

@SuppressLint("AppCompatCustomView")
public class CustomViewNoteOriginal extends EditText {
    private static Paint linePaint;

    static {
        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.STROKE);
    }
    public CustomViewNoteOriginal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect bounds = new Rect();
        int fistLineY = getLineBounds(0,bounds);
        int lineHeight = getLineHeight();
        int totalLine = Math.max(getLineCount(),getHeight()/lineHeight);
        for (int i = 0; i <totalLine ; i++) {
            int lineY = fistLineY + i* lineHeight;
            canvas.drawLine(bounds.left,lineY,bounds.right,lineY,linePaint);
        }
        super.onDraw(canvas);
    }
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        invalidate();
//    }
}
