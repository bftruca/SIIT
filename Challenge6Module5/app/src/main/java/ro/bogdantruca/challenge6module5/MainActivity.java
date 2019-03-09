package ro.bogdantruca.challenge6module5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerItems;
    private EditText mEditTextToDo;
    private Button mButtonAdd;

    ItemAdapter mItemAdapter;

    List <Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mRecyclerItems = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerItems.setLayoutManager(layoutManager);

        mItemAdapter = new ItemAdapter(getItems(), MainActivity.this);
        mRecyclerItems.setAdapter(mItemAdapter);

        mEditTextToDo = findViewById(R.id.editText);
        mButtonAdd = findViewById(R.id.button);
    }

    private List<Item> getItems() {
        return items;
    }

    public void onClickButton(View view) {
        String message = mEditTextToDo.getText().toString();
        Item item = new Item();

        int numberOfOrder = items.size();
        item.setIntNumberOfOrder((numberOfOrder + 1));

        if (!message.isEmpty()) {
            item.setStringToDo(message);
            items.add(item);

            mItemAdapter.notifyItemInserted(numberOfOrder);
        }
    }
}
