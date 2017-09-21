package kmitl.lab03.narubed58070068.simplemydot.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kmitl.lab03.narubed58070068.simplemydot.R;
import kmitl.lab03.narubed58070068.simplemydot.fragment.DotViewFragment;
import kmitl.lab03.narubed58070068.simplemydot.fragment.EditDotFragment;
import kmitl.lab03.narubed58070068.simplemydot.model.Dot;



public class MainActivity extends AppCompatActivity implements DotViewFragment.OnDotSelectListener, EditDotFragment.EditListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        if(savedInstanceState == null) {
            initialFragment();
        }
    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, new DotViewFragment().newInstance(), "DotViewFragmentTag").commit();
    }

    private void viewFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDotSelect(Dot dot) {
            viewFragment(EditDotFragment.newInstance(dot));
    }

    @Override
    public void editlistener() {
        getSupportFragmentManager().popBackStack();
    }
}