package com.example.exe4_2024;
/**
 * @author		Yiftah David yd2058@bs.amalnet.k12.il
 * @version	    1.0
 * @since		5/10/2024
 * parameter    inputer for a geometric/arithmic calculator, second screen has results
 */

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etf, etm;
    boolean ty = true;//true = geo, false = art

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etf = (EditText) findViewById(R.id.etf);
        etm = (EditText) findViewById(R.id.etm);
    }

    public void tog(View view) {
        ty = !ty;
    }
    /**
     * toggles the variable that indicates the series type.
     * <p>
     *
     * @param	view Description	refers to this activity.
     */


    public void next(View view) {
        /**
         * launches an intent to the screen which contains the series result in accordance to give parameters with all relevant parameters.
         * <p>
         *
         * @param	view Description	refers to this activity.
         */


        if(!etf.getText().toString().equals("")&&!etm.getText().toString().equals("")) {
            Intent si = new Intent(this, Results.class);
            si.putExtra("type", ty);
            si.putExtra("first", parseDouble(etf.getText().toString()));
            si.putExtra("mod", parseDouble(etm.getText().toString()));
            startActivity(si);
        }
        else Toast.makeText(this, "you have not entered all parameters", Toast.LENGTH_SHORT).show();
    }
}