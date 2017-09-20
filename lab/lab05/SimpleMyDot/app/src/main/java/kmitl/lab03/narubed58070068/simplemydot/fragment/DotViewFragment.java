package kmitl.lab03.narubed58070068.simplemydot.fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import kmitl.lab03.narubed58070068.simplemydot.R;
import kmitl.lab03.narubed58070068.simplemydot.model.Dot;
import kmitl.lab03.narubed58070068.simplemydot.model.Dots;
import kmitl.lab03.narubed58070068.simplemydot.view.DotView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DotViewFragment extends Fragment implements Dots.OnDotsChangedListener, DotView.OnDotViewPressListener, View.OnClickListener {


    private DotView dotView;
    private Dots dotlist;
    private Dot dot;

    public DotViewFragment() {
        // Required empty public constructor
    }


    public static DotViewFragment newInstance() {

        Bundle args = new Bundle();

        DotViewFragment fragment = new DotViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dotview, container, false);
        initSetup(rootView);
        dotlist = new Dots();
        dotlist.setListener(this);
        return rootView;

    }

    public void onRandomDot(View view) {
        Random rd = new Random();
        int centerX = rd.nextInt(dotView.getWidth());
        int centerY = rd.nextInt(dotView.getHeight());
        createDot(centerX, centerY);
    }

    public void createDot(int centerX, int centerY){
        Random rd = new Random();
        int color = Color.argb(100+rd.nextInt(155), rd.nextInt(255), rd.nextInt(255), rd.nextInt(255));
        int radius = 20+rd.nextInt(80);
        dot = new Dot(centerX,(int) centerY, radius, color);
        dotlist.addDot(dot);
    }

    private Bitmap screenShot() {
        View root = this.dotView.getRootView();
        Bitmap screenShot = Bitmap.createBitmap(root.getWidth(), root.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(screenShot);
        root.draw(canvas);
        return screenShot;
    }

    public Uri imageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void shareScreen(Uri uri) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "Share screenshot"));
    }

    public void onShare(View view){

            Bitmap screenshot = screenShot();
            Uri uri = imageUri(getContext(), screenshot);
            shareScreen(uri);

    }

    public void onDotViewPressed(int x, int y){
        if(dotlist.checkdot(x, y) == true) {
            createDot(x, y);
        }
    }

    public void onUndo(View view){
        dotlist.undo();
        dotView.invalidate();
    }


    private void initSetup(View rootView){
        dotView = rootView.findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);
        Button btnRandom =  rootView.findViewById(R.id.RandomDot);
        Button btnClear = rootView.findViewById(R.id.clearDot);
        Button btnUndo = rootView.findViewById(R.id.undo);
        Button btnShare = rootView.findViewById(R.id.Share);
        btnRandom.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnUndo.setOnClickListener(this);
        btnShare.setOnClickListener(this);
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    public void onClearDot(View view) {
        dotlist.clearDot();
        dotView.invalidate();
    }

    @Override
    public void onClick(View view) {
        if(R.id.RandomDot == view.getId()){
            onRandomDot(view);
        }
        if(R.id.clearDot == view.getId()){
            onClearDot(view);
        }
        if(R.id.undo == view.getId()){
            onUndo(view);
        }
        if(R.id.Share == view.getId()){
            onShare(view);
        }
    }
}
