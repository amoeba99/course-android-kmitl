package kmitl.lab03.narubed58070068.simplemydot.model;

/**
 * Created by Amoeba on 8/25/2017.
 */

public class Dot {

    public interface onDotChangedListener {
        void onDotChanged(Dot dot);
    }

    public void setListener(onDotChangedListener listener) {
        this.listener = listener;
    }

    private int centerX;
    private int centerY;
    private int radius;
    private int color;
    private onDotChangedListener listener;

    public Dot(onDotChangedListener listener, int centerX, int centerY, int radius, int color) {
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
        this.listener.onDotChanged(this);
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
