package ro.bogdantruca.challenge9module6;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HelloFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Logging.show(this, "onCreateView");

        return inflater.inflate(R.layout.fragment_hello, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Logging.show(this, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logging.show(this, "onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Logging.show(this, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();

        Logging.show(this, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Logging.show(this, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Logging.show(this, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Logging.show(this, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Logging.show(this, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Logging.show(this, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Logging.show(this, "onDetach");
    }
}
