package ro.magdamiu.myapplication.module6;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ro.magdamiu.myapplication.R;
import ro.magdamiu.myapplication.utils.Logging;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListenerFragment extends Fragment {

    private EditText mEditTextFirstNumber;
    private EditText mEditTextSecondNumber;
    private Button mButtonSendNumbers;

    public ListenerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_listener, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEditTextFirstNumber = view.findViewById(R.id.edit_text_first_number);
        mEditTextSecondNumber = view.findViewById(R.id.edit_text_second_number);
        mButtonSendNumbers = view.findViewById(R.id.send_numbers);

        mButtonSendNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextFirstNumber != null && mEditTextSecondNumber != null) {
                    String firstNumberInput = mEditTextFirstNumber.getText().toString();
                    String secondNumberInput = mEditTextSecondNumber.getText().toString();

                    if (firstNumberInput != null && !firstNumberInput.isEmpty() && secondNumberInput != null && !secondNumberInput.isEmpty() ) {
                        int firstNumber = Integer.parseInt(firstNumberInput);
                        int secondNumber = Integer.parseInt(secondNumberInput);

                        Listener listener = (Listener) getActivity();
                        if (listener != null) {
                            listener.addNumbers(firstNumber, secondNumber);
                        }
                    }
                    else {
                        mEditTextFirstNumber.setError(getString(R.string.fill_input));
                        mEditTextSecondNumber.setError(getString(R.string.fill_input));
                    }
                }
                else {
                    Logging.show(ListenerFragment.this, "vezi ca e null edit textul :D");
                }
            }
        });
    }
}
