package kmitl.lab03.narubed58070068.simplemydot.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import kmitl.lab03.narubed58070068.simplemydot.R;
import kmitl.lab03.narubed58070068.simplemydot.model.Dot;
import kmitl.lab03.narubed58070068.simplemydot.view.DotView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment implements View.OnClickListener {

    public interface EditListener {
        void editlistener(Dot dot);
    }

    private EditListener listener;
    private Dot dot;
    private View rootView;
    private EditText alpha;
    private EditText red;
    private EditText green;
    private EditText blue;
    private EditText radius;
    private EditText centerX;
    private EditText centerY;

    public EditDotFragment() {
        // Required empty public constructor
    }

    public void setListener(EditListener listener) {
        this.listener = listener;
    }

    public static EditDotFragment newInstance(Dot dot) {
        EditDotFragment fragment = new EditDotFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("dot", dot);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);
        listener = (EditListener)getActivity();
        Button okay = rootView.findViewById(R.id.okay);
        okay.setOnClickListener(this);
        alpha = rootView.findViewById(R.id.alpha);
        red = rootView.findViewById(R.id.red);
        green = rootView.findViewById(R.id.green);
        blue = rootView.findViewById(R.id.blue);
        radius = rootView.findViewById(R.id.radius);
        centerX = rootView.findViewById(R.id.centerX);
        centerY = rootView.findViewById(R.id.centerY);
        dot = getArguments().getParcelable("dot");
        alpha.setText(Integer.toString(Color.alpha(dot.getColor())));
        red.setText(Integer.toString(Color.red(dot.getColor())));
        green.setText(Integer.toString(Color.green(dot.getColor())));
        blue.setText(Integer.toString(Color.blue(dot.getColor())));
        radius.setText(Integer.toString(dot.getRadius()));
        centerX.setText(Integer.toString(dot.getCenterX()));
        centerY.setText(Integer.toString(dot.getCenterY()));

        return rootView;
    }

    public void onOkay() {
        dot.setColor(Color.argb(Integer.parseInt(alpha.getText().toString()), Integer.parseInt(red.getText().toString()),
                Integer.parseInt(green.getText().toString()),
                Integer.parseInt(blue.getText().toString())));
        dot.setRadius(Integer.parseInt(radius.getText().toString()));
        dot.setCenterX(Integer.parseInt(centerX.getText().toString()));
        dot.setCenterY(Integer.parseInt(centerY.getText().toString()));
        listener.editlistener(dot);
    }

    @Override
    public void onClick(View view) {
        if (R.id.okay == view.getId()) {
            onOkay();
        }
    }
}
