package com.example.lenovo.thefirststepoflearning;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {

    ImageView imgPages;
    public PageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page,container,false);
        imgPages = (ImageView) view.findViewById(R.id.imgPages);
        Bundle bundle = getArguments();
        String image = bundle.getString("rowrow");
        return inflater.inflate(R.layout.fragment_page, container, false);
    }

}
