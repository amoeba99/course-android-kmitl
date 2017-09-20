package kmitl.lab03.narubed58070068.simplemydot.model;

import java.util.LinkedList;

import kmitl.lab03.narubed58070068.simplemydot.activity.MainActivity;

/**
 * Created by Amoeba on 9/12/2017.
 */

public class Dots {

    public interface OnDotsChangedListener{
        void onDotsChanged(Dots dots);
    }

    private OnDotsChangedListener listener;

    public void setListener(OnDotsChangedListener listener) {
        this.listener = listener;
    }

    private LinkedList<Dot> dotlist = new LinkedList();

    public LinkedList<Dot> getDotlist() {
        return dotlist;
    }

    public void addDot(Dot dot){
        this.dotlist.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void clearDot(){
        dotlist.clear();
    }

    public int checkdot(int centerX, int centerY) {
        double distance;
        for(int i=dotlist.size()-1;i>=0;i--){
            distance = Math.sqrt(Math.pow(dotlist.get(i).getCenterX() - centerX, 2) + Math.pow(dotlist.get(i).getCenterY() - centerY, 2));
            if(dotlist.get(i).getRadius() >= distance){

                return i;

            }
        }
        return -1;
    }

    public void removeDot(int i){
        dotlist.remove(i);
        listener.onDotsChanged(this);
    }

    public void undo(){
        if(!dotlist.isEmpty()) {
            dotlist.remove(dotlist.getLast());
        }
    }

}
