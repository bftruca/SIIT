package ro.bogdantruca.challenge2module4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ITEMS_COUNT = 10;

    private RecyclerView mRecyclerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mRecyclerItems = findViewById(R.id.recycler_view_items);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerItems.setLayoutManager(layoutManager);

        ItemAdapter itemAdapter = new ItemAdapter(getItems(), MainActivity.this);
        mRecyclerItems.setAdapter(itemAdapter);
    }

    private List<Item> getItems() {
        List <Item> items = new ArrayList<>();

        Item item;

        for (int indexOfItems = 1; indexOfItems <= ITEMS_COUNT; indexOfItems++) {
            item  = new Item();

            item.setFirstName("FirstName " + indexOfItems);
            item.setLastName("LastName " + indexOfItems);

            items.add(item);
        }

        return items;
    }
}
