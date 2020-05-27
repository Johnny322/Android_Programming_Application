package com.example.android_programming_application;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {
    private ListView listView;
    private List<String> titleList = new ArrayList<>();
    private List<String> infoList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        listView = view.findViewById(R.id.listView);
        initializeLists();
        MovieAdaptor movieAdaptor = new MovieAdaptor(2, titleList, infoList);
        listView.setAdapter(movieAdaptor);
        // Inflate the layout for this fragment
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/
    }
    private void initializeLists() {
        titleList.add("Shawshank Redemption");
        titleList.add("Up");
        infoList.add("Man escapes from prison and lives happily ever after. Some dudes die");
        infoList.add("Man escapes from the city and lives happily ever after with some kid. Some dude die");
    }
}
