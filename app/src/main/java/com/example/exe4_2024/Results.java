package com.example.exe4_2024;
/**
 * @author		Yiftah David yd2058@bs.amalnet.k12.il
 * @version	    1.0
 * @since		5/10/2024
 * parameter    results display for series input in screen 1
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Results extends AppCompatActivity implements AdapterView.OnItemLongClickListener, View.OnCreateContextMenuListener {
    double[] ser = new double[20];
    double mod, sum;
    String[] vser = new String[20];
    ListView lstv;
    TextView tv;
    int temp;
    double temp2=0;
    String tmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        lstv = (ListView) findViewById(R.id.lstv);
        tv = findViewById(R.id.tv);


        Intent gi = getIntent();
        ser[0] = gi.getDoubleExtra("first", 0.0);
        mod = gi.getDoubleExtra("mod", 0.0);

        if (gi.getBooleanExtra("type", true)) {
            for (int i = 1; i < 20; i++) {
                ser[i] = ser[i - 1] * mod;
            }
        } else {
            for (int i = 1; i < 20; i++) {
                ser[i] = ser[i - 1] + mod;
            }
        }
        for (int i = 0; i < 20; i++) {
            vser[i] = (ser[i]) + "";
        }

        lstv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lstv.setOnItemLongClickListener(this);
        lstv.setOnCreateContextMenuListener(this);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, vser);
        lstv.setAdapter(adp);


    }
    /**
     * creates the context menu.
     * <p>
     *
     * @param	menu Description     refers to the menu created.
     * @param   v Description        refers to the current activity.
     * @param   menuInfo Description refers to the menuinfo object of this menu
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("View In TextView For "+ser[temp]);
        menu.add("n=");
        menu.add("Sn=");

    }

    /**
     * reacts accordingly to the context menu item selected.
     * <p>
     *
     * @param	item Description	refers to item selected in the context menu.
     * @return	Description			returns the super of this action.
     */

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        tmp = item.getTitle().toString();
        if (tmp.equals("n=")){tv.setText("n="+(temp+1));}
        else{
            temp2=0;
            for (int i = 0; i <=temp; i++) {
                temp2+=ser[i];
            }
            tv.setText("Sn="+temp2);
        }
        return super.onContextItemSelected(item);
    }

    /**
     * returns to MainActivity.
     * <p>
     *
     * @param	view Description	refers to current activity.
     */

    public void ret(View view) {
        finish();
    }

    /**
     * gives the number who is the subject of the question in the context menu.
     * <p>
     *
     * @param	parent Description	refers to the relevant adapter.
     * @param	view Description	refers to current activity.
     * @param   position Description refers to list unit selected
     * @param   id Descrition       refers to the id of the item selected
     * @return	Description			returns false.
     */

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        temp = position;
        return false;
    }
}