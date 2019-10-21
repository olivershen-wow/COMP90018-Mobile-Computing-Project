package com.android.group_12.crushy;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalAreaFragment extends Fragment {
    private int fragmentHeight;
    private int fragmentWidth;
    private Button logoutButton;
    private FirebaseAuth mAuth;

    public PersonalAreaFragment(int fragmentHeight, int fragmentWidth) {
        this.fragmentHeight = fragmentHeight;
        this.fragmentWidth = fragmentWidth;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LocationBaseFriendingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalAreaFragment newInstance(int fragmentHeight, int fragmentWidth) {
        PersonalAreaFragment fragment = new PersonalAreaFragment(fragmentHeight, fragmentWidth);
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_area, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logoutButton = getView().findViewById(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                sendUserToLoginActivity();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = mAuth.getInstance();
    }


    private void sendUserToLoginActivity() {
        Intent loginIntent = new Intent(getContext(), LoginActivity.class);
        startActivity(loginIntent);
    }
}
