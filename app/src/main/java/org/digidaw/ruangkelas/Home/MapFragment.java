package org.digidaw.ruangkelas.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.digidaw.ruangkelas.Map.TrackerMainActivity;
import org.digidaw.ruangkelas.R;

/**
 * Created by STURMBANNFUEHRER on 29/10/2017.
 */

public class MapFragment extends Fragment {

    private Button cobaButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container, false);

        cobaButton = (Button) view.findViewById(R.id.btnCoba);

        cobaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        return view;
    }
}
