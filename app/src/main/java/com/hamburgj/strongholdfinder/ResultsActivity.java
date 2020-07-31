package com.hamburgj.strongholdfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView mPortalTextX = (TextView) findViewById(R.id.portal_view_x);
        TextView mPortalTextZ = (TextView) findViewById(R.id.portal_view_z);
        Intent intent = getIntent();
        int px = (int) Math.round(Double.parseDouble(intent.getStringExtra("portalX")));
        int pz = (int) Math.round(Double.parseDouble(intent.getStringExtra("portalZ")));
        mPortalTextX.setText(Integer.toString(px));
        mPortalTextZ.setText(Integer.toString(pz));

    }

    public void gotoMain(View view) {
        onBackPressed();
    }
}
