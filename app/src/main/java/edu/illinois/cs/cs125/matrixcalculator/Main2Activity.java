package edu.illinois.cs.cs125.matrixcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        System.out.println("output online");
        double[][] getUpper;
        Object[] objectArray = (Object[]) getIntent().getExtras().getSerializable("doubleArray");
        if (objectArray == null) {
            System.out.println("Something is terribly wrong");
            return;
        }
        double determinant = getIntent().getExtras().getDouble("determinant", 0.0);
        getUpper = new double[objectArray.length][];
        for (int i = 0; i < objectArray.length; i++) {
            getUpper[i] = (double[]) objectArray[i];
        }
        System.out.println("data rec1eived");
        System.out.println("output Matrix: ");
        for (double [] elements: getUpper) {
            for (double element: elements) {
                System.out.print(element);
                System.out.print(" ");
            }
            System.out.println();
        }
        String display = "";
        if (getUpper[0][0] != 0.00001) {
            for (int i = 0; i < getUpper.length; i++) {
                display += "[";
                for (int j = 0; j < getUpper[0].length; j++) {
                    display = display + getUpper[i][j];
                    if (j == getUpper[0].length - 1) {
                        continue;
                    }
                    display += ", ";
                }
                display += "]\n";
            }
        } else {
            display = "Please give me a square matrix.";
        }
        System.out.println("display string: ");
        System.out.println(display);
        TextView outputMatrixUpper = (TextView) findViewById(R.id.outputMatrixUpper);
        outputMatrixUpper.setText(display);
        String displayDeterminant;
        if (determinant != 1.23456789012345) {
            displayDeterminant = "" + determinant;
        } else {
            displayDeterminant = "Determinant must be square!\nPay attention in lectures!!!";
        }
        System.out.print("determinant: ");
        System.out.println(displayDeterminant);
        TextView outputDeterminant = (TextView) findViewById(R.id.determinant);
        outputDeterminant.setText(displayDeterminant);
        final Button backButton = findViewById(R.id.BackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BackActivity = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(BackActivity);
            }
        });
    }
}
