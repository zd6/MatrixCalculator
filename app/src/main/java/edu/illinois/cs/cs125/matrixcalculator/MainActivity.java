package edu.illinois.cs.cs125.matrixcalculator;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * set UI botton functions.
         */
        final Button calculate = findViewById(R.id.button);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                System.out.println( "this is a message");
                getMatrix();
                System.out.println("matrix get success");
            }
        });
    }

    public int[][] getMatrix() {
        EditText[][] input = new EditText[4][4];
        for (int )
        input[0][0] = findViewById(R.id.r1c1+5);
        System.out.println(input[0][0].getText().length());
        return null;
    }
}
