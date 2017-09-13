package kmitl.lab03.narubed58070068.simplemydot.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.util.Random;
import kmitl.lab03.narubed58070068.simplemydot.R;
import kmitl.lab03.narubed58070068.simplemydot.model.Dot;
import kmitl.lab03.narubed58070068.simplemydot.model.Dots;
import kmitl.lab03.narubed58070068.simplemydot.view.DotView;


public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangedListener, DotView.OnDotViewPressListener {

    private DotView dotView;
    private Dots dotlist;
    private Dot dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);
        dotlist = new Dots();
        dotlist.setListener(this);
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

    public void onDotViewPressed(int x, int y){
        if(dotlist.checkdot(x, y) == true) {
            createDot(x, y);
        }
    }

    public void onUndo(View view){
        dotlist.undo();
        dotView.invalidate();
    }

    private boolean requestExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
            return false;
        } else {
            return true;
        }
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
        if(requestExternalStoragePermission()) {
            Bitmap screenshot = screenShot();
            Uri uri = imageUri(getApplicationContext(), screenshot);
            shareScreen(uri);
        }
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

}