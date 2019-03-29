package ro.bogdantruca.firebaseproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class CloudFirestoreActivity extends AppCompatActivity {

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore mFirebaseFirestore;

    private static final String NAME = "name";
    private static final String DONE = "done";
    private static final String TASKS_COLLECTION = "tasks";

    private EditText mEditTextName;
    private TextView mTextViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_firestore);

        initFirebaseFirestore();
        initView();
        readTasks();
        displayWelcome();
    }

    private void displayWelcome() {
        //Toast.makeText(CloudFirestoreActivity.this, "Welcome, " + getUsername(), Toast.LENGTH_SHORT).show();
        ToastClass.show(CloudFirestoreActivity.this, "Welcome, " + getUsername());
    }

    private String getUsername() {
        return ApplicationData.getStringValueFromSharedPreferences(CloudFirestoreActivity.this, ApplicationData.USERNAME);
    }

    private void initFirebaseFirestore() {
        mFirebaseFirestore = FirebaseFirestore.getInstance();
    }

    private void initView() {
        mEditTextName = findViewById(R.id.edit_text_name);
        mTextViewTasks = findViewById(R.id.text_view_tasks);
    }

    private void addTask(Item item) {
        Map<String, Object> theItem = new HashMap<>();
        theItem.put(NAME, item.getName());
        theItem.put(DONE, item.isDone());

        mFirebaseFirestore.collection(TASKS_COLLECTION)
                .add(theItem)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        displayMessage(
                                "DocumentSnapshot added with ID: " + documentReference.getId());
                        readTasks();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        displayMessage("Error adding document");
                    }
                });
    }

    private void displayMessage(String s) {
        Toast.makeText(CloudFirestoreActivity.this,
                s,
                Toast.LENGTH_LONG).show();
    }

    private void readTasks() {
        mFirebaseFirestore.collection(TASKS_COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        mTextViewTasks.setText("");
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mTextViewTasks
                                        .append(document.getId() + " => " + document.getData() + "\n");
                            }
                        } else {
                            displayMessage("Error getting documents.");
                        }
                    }
                });
    }

    public void saveTaskOnClick(View view) {
        if (mEditTextName != null) {
            String name = mEditTextName.getText().toString();
            if (name != null && !name.isEmpty()) {
                addTask(new Item(name, false));
            }
        }
    }
}
