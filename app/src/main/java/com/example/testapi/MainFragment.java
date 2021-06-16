package com.example.testapi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;


public class MainFragment extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Drawer drawer = (Drawer) getActivity();
        drawer.addData(""+1);
        SearchView busca = (SearchView)getView().findViewById(R.id.searchView);
        busca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (query != ""){
                    query = query.substring(0, 1).toLowerCase() + query.substring(1);
                    drawer.addData(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}

