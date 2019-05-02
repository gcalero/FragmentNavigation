package com.gabrielcalero.fragmentnavigation;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabrielcalero.fragmentnavigation.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class StopFragment extends Fragment {

    private View.OnClickListener mClickListener;

    public StopFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_stop, container, false);
        if (mClickListener != null) {
            rootView.findViewById(R.id.stop).setOnClickListener(mClickListener);
        }
        return rootView;
    }

}
