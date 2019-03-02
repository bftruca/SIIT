package ro.bogdantruca.siitandroidfundamentals.complexview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.bogdantruca.siitandroidfundamentals.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private static final int CONTACTS_COUNT = 25;

    private RecyclerView mRecyclerViewContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        InitView();
    }

    private void InitView() {
        mRecyclerViewContacts = findViewById(R.id.recycler_view_contacts);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewContacts.setLayoutManager(layoutManager);

        ContactAdapter contactAdapter = new ContactAdapter(getContacts(), RecyclerViewActivity.this);
        mRecyclerViewContacts.setAdapter(contactAdapter);
    }

    private List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();

        Contact contact;

        for (int i = 0; i < CONTACTS_COUNT; i++) {
            contact = new Contact();

            contact.setFullName("Android Name " + i);
            contact.setAdresse("Address " + i);
            contact.setGroup("Family " + i);
            contact.setImageUrl("http://profilepicturesdp.com/wp-content/uploads/2018/07/smile-profile-picture-3.jpg");
            contacts.add(contact);
        }

        return contacts;
    }
}
