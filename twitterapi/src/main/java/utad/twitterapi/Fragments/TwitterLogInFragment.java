package utad.twitterapi.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import utad.twitterapi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwitterLogInFragment extends Fragment {


    public TwitterLogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_twitter_log_in, container, false);
    }

}
