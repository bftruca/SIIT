package ro.bogdantruca.firebaseproject.Fragments;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import ro.bogdantruca.firebaseproject.R;
import ro.bogdantruca.firebaseproject.Utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment implements Constants {

    private RecyclerView mRecyclerViewTrips;
    private FirebaseFirestore db;
    private String mUsernameMail;
    private List<Trip> trips;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        mRecyclerViewTrips = view.findViewById(R.id.recyclerview_trips_fragment);

        initView();

        return view;
    }

    private void initView() {
        trips = new ArrayList<>();

        //set the layout manager for the current recycler view
        mRecyclerViewTrips.setLayoutManager(new LinearLayoutManager(getActivity()));

        setUpFireBase();
        loadDataFromFirebase();
    }

    private void setUpFireBase() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        db = FirebaseFirestore.getInstance();

        if ( firebaseUser != null ) {
            mUsernameMail = firebaseUser.getEmail();
        }
    }

    private void loadDataFromFirebase() {
        db.collection(mUsernameMail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot documentSnapshot: task.getResult()) {
                            Log.e("ID:", documentSnapshot.getId());
                            Trip trip = new Trip(documentSnapshot.getId()
                                    , documentSnapshot.getString(DB_TRIP_NAME)
                                    , documentSnapshot.getString(DB_DESTINATION)
                                    , documentSnapshot.getString(DB_PRICE_EURO)
                                    , documentSnapshot.getString(DB_START_DATE)
                                    , documentSnapshot.getString(DB_END_DATE)
                                    , documentSnapshot.getString(DB_TRIP_TYPE)
                                    , documentSnapshot.getString(DB_URL_IMAGE)
                                    , documentSnapshot.getString(DB_FILE_REFERENCE)
                                    , documentSnapshot.getDouble(DB_RATING)
                                    , documentSnapshot.get(DB_FAVORITE , boolean.class)
                            );
                            trips.add(trip);
                        }
                        //create the adapter
                        TripAdapter tripAdapter = new TripAdapter(trips);

                        //set the adapter to the recycler view
                        mRecyclerViewTrips.setAdapter(tripAdapter);
                    }
                });
    }
}
