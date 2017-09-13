package kmitl.lab03.narubed58070068.simplemydot.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import kmitl.lab03.narubed58070068.simplemydot.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button btnBack = (Button) findViewById(R.id.btnBack);

        Intent intent = getIntent();
        TextView text = (TextView) findViewById(R.id.testText);
        text.setText(intent.getStringExtra("word"));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
