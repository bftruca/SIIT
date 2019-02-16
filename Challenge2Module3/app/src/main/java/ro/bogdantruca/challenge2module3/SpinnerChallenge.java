package ro.bogdantruca.challenge2module3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SpinnerChallenge extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner mSpinnerChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        Init();
    }

    private void Init() {
        mSpinnerChallenge = findViewById(R.id.spinner_elements);
        mSpinnerChallenge.setAdapter(getAdapterElements());
        mSpinnerChallenge.setOnItemSelectedListener(this);
    }

    private List<String> getElements() {
        List<String> elements = new ArrayList();
        elements.add("Cupcake");
        elements.add("Donut");
        elements.add("Eclair");
        elements.add("KitKat");
        elements.add("Pie");

        return elements;
    }

    private ArrayAdapter<String> getAdapterElements() {
        return new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getElements());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = getElements().get(position);

        Toast.makeText(SpinnerChallenge.this, "Selected: " + selected, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
