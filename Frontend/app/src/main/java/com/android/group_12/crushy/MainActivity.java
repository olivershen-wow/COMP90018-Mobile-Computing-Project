package com.android.group_12.crushy;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.group_12.crushy.Constants.DatabaseConstant;
import com.android.group_12.crushy.Utils.ScreenUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Point screenSize;
    private BottomNavigationView navView;
    private int phoneNavigationBarHeight;
    private int appNavigationBarHeight;
    private int fragmentHeight;

    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private DatabaseReference rootRef;

    private void updateFragment(int selectedNavigationItemID) {
        Fragment fragment = new Fragment();

        switch (selectedNavigationItemID) {
            case R.id.navigation_location: {
                System.out.println("Location Based Friending");
                fragment = new LocationBaseFriendingFragment(this.fragmentHeight, this.screenSize.x);
                break;
            }
            case R.id.navigation_friend_list: {
                System.out.println("Friend list");
                break;
            }
            case R.id.navigation_personal_area: {
                System.out.println("Personal Area");
                fragment = new PersonalAreaFragment(this.fragmentHeight, this.screenSize.x);
                break;
            }
            default: {
                // do nothing
            }

        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.first_level_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            try {
                updateFragment(item.getItemId());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mAuth = mAuth.getInstance();
        this.rootRef = FirebaseDatabase.getInstance().getReference();
        this.currentUser = mAuth.getCurrentUser();

        setContentView(R.layout.activity_main);

        this.navView = findViewById(R.id.button_nav);
        // Bind the event listener with the navigation view.
        this.navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (currentUser == null) {
            System.out.println("User is null");
            sendUserToLoginActivity();
        } else {
            System.out.println("User is not null");

            verifyUserExistence(); // Verify user's existence.

            // Height information
            this.screenSize = ScreenUtil.getScreenSize(getApplicationContext());
            this.phoneNavigationBarHeight = ScreenUtil.getHeightOfNavigationBar(getApplicationContext());
            this.appNavigationBarHeight = this.navView.getLayoutParams().height;
            this.fragmentHeight = this.screenSize.y - this.phoneNavigationBarHeight - this.appNavigationBarHeight;

            System.out.println("this.phoneNavigationBarHeight = " + this.phoneNavigationBarHeight);
            System.out.println("this.appNavigationBarHeight = " + this.appNavigationBarHeight);
            System.out.println("this.screenSize = " + this.screenSize);
            System.out.println("this.fragmentHeight = " + this.fragmentHeight);

            int appBarHeight = this.navView.getHeight();
            System.out.println("appBarHeight = " + appBarHeight);

            // Open navigation location fragment by default.
            this.updateFragment(R.id.navigation_location);
        }
    }

    private void sendUserToLoginActivity() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }

    private void sendUserToSettingsActivity() {
//        Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
//        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(settingsIntent);
    }

    private void verifyUserExistence() {
        String currentUserID = this.currentUser.getUid();

        rootRef.child(DatabaseConstant.USER_TABLE_NAME).child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(DatabaseConstant.USER_TABLE__USER_NAME).exists()) {
                    Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Oops, your name is not set...", Toast.LENGTH_SHORT).show();
                    sendUserToSettingsActivity();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
