package utad.phpconexion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    public RecyclerView recyclerView;

    /*
    private static ListFragment instance;
    public ListFragment() {
        // Required empty public constructor
    }
    public static ListFragment getInstance(){
        if (instance==null){
            instance = new ListFragment();
        }
        return  instance;
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        Log.v("Oscar", "Uso la LISTAAAAAAAA");

       recyclerView = (RecyclerView) v.findViewById(R.id.MessageList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));


        /*ArrayList<String> mData = new ArrayList<>();
        ListMessageAdapter listMessageAdapter = new ListMessageAdapter(mData);
        recyclerView.setAdapter(listMessageAdapter);*/


        return v;
    }
}
