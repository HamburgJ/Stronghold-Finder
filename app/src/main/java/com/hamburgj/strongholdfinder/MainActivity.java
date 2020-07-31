package com.hamburgj.strongholdfinder;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mx1;
    private EditText mz1;
    private EditText mdir1;
    private EditText mx2;
    private EditText mz2;
    private EditText mdir2;
    private double maxAngle = 180;
    private double minAngle = -180;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get buttons
        mx1 = findViewById(R.id.x1);
        mz1 = findViewById(R.id.z1);
        mdir1 = findViewById(R.id.dir1);
        mx2 = findViewById(R.id.x2);
        mz2 = findViewById(R.id.z2);
        mdir2 = findViewById(R.id.dir2);

        //Add listener to limit angle that can be input
        mdir1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {
                try {
                    if (Double.parseDouble(mdir1.getText().toString()) > maxAngle) {
                        mdir1.setText(Double.toString(maxAngle));
                        mdir1.setSelection(mdir1.getText().length());
                    }
                    if (Double.parseDouble(mdir1.getText().toString()) < minAngle) {
                        mdir1.setText(Double.toString(minAngle));
                        mdir1.setSelection(mdir1.getText().length());
                    }
                } catch (Exception e){};
            }
        });

        mdir2.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {
                try {
                    if(Double.parseDouble(mdir2.getText().toString()) > maxAngle){
                        mdir2.setText(Double.toString(maxAngle));
                        mdir2.setSelection(mdir2.getText().length());
                    }
                    if(Double.parseDouble(mdir2.getText().toString()) < minAngle){
                        mdir2.setText(Double.toString(minAngle));
                        mdir2.setSelection(mdir2.getText().length());
                    }
                } catch (Exception e){};
            }
        });
    }

    public void gotoResults(View view) {
        try {
            double x1 = Double.parseDouble(mx1.getText().toString());
            double z1 = Double.parseDouble(mz1.getText().toString());
            double dir1 = Double.parseDouble(mdir1.getText().toString());
            double x2 = Double.parseDouble(mx2.getText().toString());
            double z2 = Double.parseDouble(mz2.getText().toString());
            double dir2 = Double.parseDouble(mdir2.getText().toString());

            //transform in-game direction
            double dir1Transformed = -dir1;
            double dir2Transformed = -dir2;

            //find slope of both lines
            double slope1 = Math.tan(dir1Transformed * Math.PI/180);
            double slope2 = Math.tan(dir2Transformed * Math.PI/180);

            //find intercept of both lines
            double b1 = x1 - slope1*z1;
            double b2 = x2 - slope2*z2;

            //find x and z of portal
            double pz = (b2-b1)/(slope1-slope2);
            double px = slope1*pz + b1;

            //goto next page
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra("portalX", Double.toString(px));
            intent.putExtra("portalZ", Double.toString(pz));
            startActivity(intent);
        } catch (Exception e) {
            //show error toast
            Toast.makeText(this, R.string.error_toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void gotoInfo(View view) {
        startActivity(new Intent(this, InfoActivity.class));
    }

    public void clearValues(View view) {
        mx1.setText("");
        mz1.setText("");
        mdir1.setText("");
        mx2.setText("");
        mz2.setText("");
        mdir2.setText("");
        Toast.makeText(this, R.string.clear_toast, Toast.LENGTH_SHORT).show();
    }
}
