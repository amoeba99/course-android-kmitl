package kmitl.lab03.narubed58070068.simplemydot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;
import java.util.Random;

import kmitl.lab03.narubed58070068.simplemydot.model.Dot;
import kmitl.lab03.narubed58070068.simplemydot.view.DotView;


public class MainActivity extends AppCompatActivity implements Dot.onDotChangedListener {

    private Dot dot;
    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        dotView = (DotView) findViewById(R.id.dotView);
    }

    public void onRandomDot(View view) {
        Random rd = new Random();
        int centerX = rd.nextInt(dotView.getWidth());
        int centerY = rd.nextInt(dotView.getHeight());
        int color = Color.argb(255, rd.nextInt(255), rd.nextInt(255), rd.nextInt(255));
        new Dot(this, centerX, centerY, 50, color);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            int position[] = new int[2];
            dotView.getLocationOnScreen(position);
            Random rd = new Random();
            int color = Color.argb(255, rd.nextInt(255), rd.nextInt(255), rd.nextInt(255));
            new Dot(this, (int) event.getX()-position[0],(int) event.getY()-position[1], 50, color);
        }
        return false;
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(dot);
        dotView.invalidate();
    }

    public void onClearDot(View view) {
        dotView.clearDot();
        dotView.invalidate();
    }

}