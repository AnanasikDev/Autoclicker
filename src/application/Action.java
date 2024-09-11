package application;

import java.util.ArrayList;

public class Action {
    public ArrayList<Delegate> delegates = new ArrayList<>();

    public void Subscribe(Delegate delegate){
        delegates.add(delegate);
    }

    public void Invoke(){
        for (var d : delegates){
            d.execute();
        }
    }
}
