package ro.bogdantruca.challenge9module6.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ro.bogdantruca.challenge9module6.Logging;
import ro.bogdantruca.challenge9module6.MainActivity;
import ro.bogdantruca.challenge9module6.R;
import ro.bogdantruca.challenge9module6.ToastClass;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {


    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Show("onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

    @Override
    public void onAttach(Context context) {
        Show("onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Show("onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Show("onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Show("onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Show("onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Show("onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Show("onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Show("onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Show("onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Show("onDestroy");
        super.onDestroy();
    }

    private void Show(String message) {
        Logging.show(getActivity(), message);
        ToastClass.show(getActivity(), message);
    }

}
