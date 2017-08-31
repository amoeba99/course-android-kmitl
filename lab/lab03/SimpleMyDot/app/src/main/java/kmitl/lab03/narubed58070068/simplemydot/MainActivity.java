package kmitl.lab03.narubed58070068.simplemydot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import kmitl.lab03.narubed58070068.simplemydot.model.Dot;
import kmitl.lab03.narubed58070068.simplemydot.view.DotView;


public class MainActivity extends AppCompatActivity implements Dot.onDotChangedListener {

    private DotView dotView;
    private LinkedList<Dot> dotlist = new LinkedList();

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
        createDot(centerX, centerY);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            int position[] = new int[2];
            dotView.getLocationOnScreen(position);
            int centerX = (int) event.getX()-position[0];
            int centerY = (int) event.getY()-position[1];
            dotlist = (LinkedList) dotView.getDotlist().clone();
            Collections.reverse(dotlist);
            for(Dot dot : dotlist) {
                double distance = dotView.checkdot(dot, centerX, centerY);
                if(distance <= 50){
                    dotView.removeDot(dot);
                    dotView.invalidate();
                    return false;
                }
            }
            createDot(centerX, centerY);
        }
        return false;
    }

    public void createDot(int centerX, int centerY){
        Random rd = new Random();
        int color = Color.argb(100+rd.nextInt(155), rd.nextInt(255), rd.nextInt(255), rd.nextInt(255));
        new Dot(this, centerX,(int) centerY, 50, color);
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