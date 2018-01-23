package utad.phpconexion.Adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import utad.phpconexion.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    public RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.MessageList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        return v;
    }
}
