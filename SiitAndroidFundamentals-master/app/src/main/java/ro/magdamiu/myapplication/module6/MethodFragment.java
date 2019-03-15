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
public class MethodFragment extends Fragment {

    private String mName;

    private TextView mTextViewHello;

    public MethodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_method,container,false);
        initView(view);
        setTextViewHello();
        return view;
    }

    private void initView(View view) {
        mTextViewHello = view.findViewById(R.id.text_view_hello);
    }

    private void setTextViewHello() {
        if (mTextViewHello != null) {
            mTextViewHello.setText("Hello " + mName + " !");
        }
    }

    public void setName(String name) {
        this.mName = name;
    }
}
