package ro.bogdantruca.module9;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    private TextView mTextViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        initView();
        new LoadItemsAsync().execute();
    }

    private void initView() {
        mTextViewItems = findViewById(R.id.textView);
    }

    private class LoadItemsAsync extends AsyncTask<Void, Void, List<Item>> {
        @Override
        protected void onPreExecute() {
            ItemRoomDatabase.getDatabase(ItemsActivity.this);
            super.onPreExecute();
        }

        @Override
        protected List<Item> doInBackground(Void... voids) {
            ItemRoomDatabase database = ItemRoomDatabase.getDatabase(ItemsActivity.this);
            return database.itemDao().getAllItems();
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            super.onPostExecute(items);

            for (Item item : items) {
                mTextViewItems.append(item.toString() + " | ");
            }
        }
    }
}
