package kmitl.lab04.narubed58070068.workshop01;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kmitl.lab04.narubed58070068.workshop01.fragment.MainFragment;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, new MainFragment().newInstance("")).commit();


        Button accessFragment = (Button) findViewById(R.id.accessFragment);
        accessFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().add(R.id.fragmentContainer, new MainFragment().newInstance("Access From Activity")).commit();
            }
        });

    }











}
