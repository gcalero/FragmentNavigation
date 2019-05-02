package com.gabrielcalero.fragmentnavigation;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    private View.OnClickListener mClickListener;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof View.OnClickListener) {
            mClickListener = (View.OnClickListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);
        if (mClickListener != null) {
            rootView.findViewById(R.id.third).setOnClickListener(mClickListener);
            rootView.findViewById(R.id.backToFirst).setOnClickListener(mClickListener);
        }
        return rootView;
    }

}
