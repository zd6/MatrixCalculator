package edu.illinois.cs.cs125.matrixcalculator;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Jama.*;
//import Jama.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * set UI botton functions.
         */
        final Button calculate = findViewById(R.id.button);
        //String a = "erfmesf;l";
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                System.out.println( "this is a message");
                double[][] inputMatrixDouble = getDoubleMatrix();
                System.out.println("matrix get success");
                getUpper(getDoubleMatrix());
                System.out.println("getU success");
                Intent NextActivity = new Intent(MainActivity.this, Main2Activity.class);
//                    NextActivity.putExtra("outputUpper", getUpper(getDoubleMatrix()));
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("doubleArray", getUpper(getDoubleMatrix()));
                    NextActivity.putExtras(mBundle);
                    NextActivity.putExtra("determinant", determinant(getDoubleMatrix()));
                    NextActivity.putExtra("eigenvaluereal", eigenValueReal(getDoubleMatrix()));
                    NextActivity.putExtra("eigenvalueimag", eigenValueImag(getDoubleMatrix()));
                startActivity(NextActivity);
            }
        });
    }
    public double[][] getDoubleMatrix() {
        EditText[][] input = new EditText[4][4];
        //int row = 0;
        int column = 0;
        for (int i = R.id.r1c1; i < R.id.r1c4 + 1; i++) {
            input[0][column] = findViewById(i);
            column++;
        }
        column = 0;
        for (int i = R.id.r2c1; i < R.id.r2c4 + 1; i++) {
            input[1][column] = findViewById(i);
            column++;
        }
        column = 0;
        for (int i = R.id.r3c1; i < R.id.r3c4 + 1; i++) {
            input[2][column] = findViewById(i);
            column++;
        }
        column = 0;
        for (int i = R.id.r4c1; i < R.id.r4c4 + 1; i++) {
            input[3][column] = findViewById(i);
            column++;
        }
        boolean[][] noInput = new boolean[4][4];
        double[][] output = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (input[i][j] == null || input[i][j].getText().length() == 0) {
                    noInput[i][j] =true;
                    output[i][j] = 0;
                    //System.out.print(output[i][j]);
                    //System.out.print(" ");
                    continue;
                }
                output[i][j] = Double.parseDouble(input[i][j].getText().toString());
            }
            //System.out.println();
        }
        int rowValid = 4;
        int columbValid = 4;
        for (int i = 3; i >= 0; i--) {
            if (noInput[i][0] && noInput[i][1] && noInput[i][2] && noInput[i][3]) {
                rowValid--;
                continue;
            }
            break;
        }
        for (int i = 3; i >= 0; i--) {
            if (noInput[0][i] && noInput[1][i] && noInput[2][i] && noInput[3][i]) {
                columbValid--;
                continue;
            }
            break;
        }
        if (rowValid == 0 || columbValid == 0) {
            System.out.println("This idiot didn't put any number! Dumb ass.");
            double[][] temp = new double[1][1];
            return temp;
        }
        double[][] outputSized = new double[rowValid][columbValid];
        System.out.println("sizing matrix:");
        for (int i = 0; i < rowValid; i++) {
            for (int j = 0; j < columbValid; j++) {
                outputSized[i][j] = output[i][j];
                System.out.print(outputSized[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
//        input[0][0] = findViewById(R.id.r1c1+5);
//        System.out.println(input[0][0].getText().length());
        return outputSized;
    }
    public double[][] getUpper(double[][] input) {
        if (input.length != input[0].length) {
            double[][] temp = new double[1][1];
            temp[0][0] = 0.00001;
            return temp;
        }
        Matrix current = new Matrix(input);
        LUDecomposition calulator = new LUDecomposition(current);
        current = calulator.getU();
        double[][] output = current.getArray();
        for (double [] elements: output) {
            for (double element: elements) {
                System.out.print(element);
                System.out.print(" ");
            }
            System.out.println();
        }
//        System.out.println(output.toString());
        return output;
    }
    public double determinant(double[][] input) {
        if (input.length != input[0].length) {
            return 1.23456789012345;
        }
        Matrix a = new Matrix(input);
        LUDecomposition aa = new LUDecomposition(a);
        double output = aa.det();
        return output;
    }

    public double[] eigenValueReal(double[][] input) {
        if (input.length != input[0].length) {
            double[] temp = new double[1];
            temp[0] = 0.12345678901234;
            return temp;
        }
        Matrix current = new Matrix(input);
        EigenvalueDecomposition calculator = new EigenvalueDecomposition(current);
        double[] output = calculator.getRealEigenvalues();
        return output;
    }
    public double[] eigenValueImag(double[][] input) {
        if (input.length != input[0].length) {
            double[] temp = new double[1];
            temp[0] = 0.12345678901234;
            return temp;
        }
        Matrix current = new Matrix(input);
        EigenvalueDecomposition calculator = new EigenvalueDecomposition(current);
        double[] output = calculator.getImagEigenvalues();
        return output;
    }
}
