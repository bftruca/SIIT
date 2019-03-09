package ro.magdamiu.myapplication.module6;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import ro.magdamiu.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private TextView mEditTextEmail;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_blank, container, false);

        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEditTextEmail = view.findViewById(R.id.text_view_mail);

        Bundle bundle = getArguments();
        String email = bundle.getString(DynamicFragmentActivity.EMAIL);
        if (email != null && !email.isEmpty()) {
            mEditTextEmail.setText(email);
        }
    }

}
