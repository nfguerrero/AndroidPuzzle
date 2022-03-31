package com.cypuzzle.main.ui.hard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cypuzzle.main.R;

public class HardFragment extends Fragment {

    private HardViewModel HardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HardViewModel =
                ViewModelProviders.of(this).get(HardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_leaderboard_hard, container, false);
        final TextView textView = root.findViewById(R.id.text_leaderboard_hard);
        HardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}