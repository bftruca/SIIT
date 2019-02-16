package ro.bogdantruca.siitandroidfundamentals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int FRUITS_NUMBER = 15;

    private Spinner mSpinnerFruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        initView();
    }

    private void initView() {
        mSpinnerFruits = findViewById(R.id.spinner_fruits);
        mSpinnerFruits.setAdapter(getFruitsAdapter());
    }

    //get data source for spinner
    private List<String> getFruits(int numberOfFruits) {
        List<String> fruits = new ArrayList<>();

        for(int indexFruits = 0; indexFruits < numberOfFruits; indexFruits++) {
            fruits.add("Fruit " + indexFruits);
        }

        return fruits;
    }

    // create adapter
    private ArrayAdapter<String> getFruitsAdapter() {
        return new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getFruits(FRUITS_NUMBER));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedFruit = getFruits(FRUITS_NUMBER).get(position);
        Toast.makeText(SpinnerActivity.this, selectedFruit + " / " + position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
