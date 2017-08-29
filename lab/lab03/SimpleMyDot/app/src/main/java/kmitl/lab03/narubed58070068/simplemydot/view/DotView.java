package kmitl.lab03.narubed58070068.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;

import kmitl.lab03.narubed58070068.simplemydot.model.Dot;

/**
 * Created by Amoeba on 8/25/2017.
 */

public class DotView extends View {
    private Paint paint;
    private Dot dot;
    private LinkedList<Dot> dotlist = new LinkedList();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        dotlist.add(dot);

        for (Dot dots : dotlist) {
            if (dots != null) {
                paint.setColor(dots.getColor());
                canvas.drawCircle(dots.getCenterX(), dots.getCenterY(), 20, paint);
                dot = null;
            }
        }
    }

    public void clearDot() {
        dotlist.clear();
    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        paint = new Paint();
    }
}
