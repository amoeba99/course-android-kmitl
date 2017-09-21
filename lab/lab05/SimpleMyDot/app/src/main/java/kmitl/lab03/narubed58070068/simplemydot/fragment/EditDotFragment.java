package kmitl.lab03.narubed58070068.simplemydot.fragment;


import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import kmitl.lab03.narubed58070068.simplemydot.R;
import kmitl.lab03.narubed58070068.simplemydot.model.Dot;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment implements View.OnClickListener {

    public interface EditListener {
        void editlistener();
    }

    private EditListener listener;
    private Dot dot;
    private View rootView;
    private SeekBar alpha;
    private SeekBar red;
    private SeekBar green;
    private SeekBar blue;
    private EditText radius;
    private EditText centerX;
    private EditText centerY;
    private TextView preview;

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
        preview = rootView.findViewById(R.id.preview);
        dot = getArguments().getParcelable("dot");
        alpha.setProgress(Color.alpha(dot.getColor()));
        red.setProgress(Color.red(dot.getColor()));
        green.setProgress(Color.green(dot.getColor()));
        blue.setProgress(Color.blue(dot.getColor()));
        radius.setText(Integer.toString(dot.getRadius()));
        centerX.setText(Integer.toString(dot.getCenterX()));
        centerY.setText(Integer.toString(dot.getCenterY()));
        preview.setBackgroundColor(dot.getColor());
        seekbarListener(rootView);
        return rootView;
    }

    public void seekbarListener(View view){
        alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    preview.setBackgroundColor(Color.argb(alpha.getProgress(), red.getProgress(),
                            green.getProgress(),
                            blue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                preview.setBackgroundColor(Color.argb(alpha.getProgress(), red.getProgress(),
                        green.getProgress(),
                        blue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                preview.setBackgroundColor(Color.argb(alpha.getProgress(), red.getProgress(),
                        green.getProgress(),
                        blue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                preview.setBackgroundColor(Color.argb(alpha.getProgress(), red.getProgress(),
                        green.getProgress(),
                        blue.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void onOkay() {
        dot.setColor(Color.argb(alpha.getProgress(), red.getProgress(),
                green.getProgress(),
                blue.getProgress()));
        dot.setRadius(Integer.parseInt(radius.getText().toString()));
        dot.setCenterX(Integer.parseInt(centerX.getText().toString()));
        dot.setCenterY(Integer.parseInt(centerY.getText().toString()));
        listener.editlistener();
    }

    @Override
    public void onClick(View view) {
        if (R.id.okay == view.getId()) {
            onOkay();
        }
    }
}
