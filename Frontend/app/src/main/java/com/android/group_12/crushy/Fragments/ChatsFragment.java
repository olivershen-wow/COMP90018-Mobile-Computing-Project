package com.android.group_12.crushy.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.group_12.crushy.Adapter.UserAdapter;
import com.android.group_12.crushy.DatabaseWrappers.Chat;
import com.android.group_12.crushy.DatabaseWrappers.Friends;
import com.android.group_12.crushy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ChatsFragment extends Fragment {

    private final static ChatsFragment Instance = new ChatsFragment();

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<Friends> mUsers;

    FirebaseUser fuser;
    DatabaseReference databaseReference;
    private List<String> usersList;

    private ChatsFragment() {
        System.out.println("chats fragment启动！");
    }

    public static final ChatsFragment getInstance() {
        return ChatsFragment.Instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        usersList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Chats");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersList.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getSender().equals(fuser.getUid())) {
                        usersList.add(chat.getReceiver());
                    }
                    if (chat.getReceiver().equals(fuser.getUid())) {
                        usersList.add(chat.getSender());
                    }
                }

                System.out.println("usersList is ");
                System.out.println(Arrays.toString(usersList.toArray()));

                readChats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }


    private void readChats() {
        mUsers = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                mUsers.clear();
//                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
//                    Friends friend = snapshot.getValue(Friends.class);
//                    for (String id: usersList) {
//                        if (friend.getUserID().equals(id)) {
//                            if (mUsers.size()!=0) {
//                                for (Friends user: mUsers) {
//                                    if (!friend.getUserID().equals(user.getUserID())) {
//                                        mUsers.add(friend);
//                                    }
//                                }
//                            } else {
//                                mUsers.add(friend);
//                            }
//                        }
//                    }
//                }
//                userAdapter = new UserAdapter(getContext(), mUsers);
//                recyclerView.setAdapter(userAdapter);
//            }
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Friends friend = snapshot.getValue(Friends.class);
                    for (String id: usersList) {
                        if (friend.getUserID().equals(id)) {
//                            if (mUsers.size()!=0) {
//                                Set<Friends> arrayList = new HashSet<>();
//                                for (Friends temp: mUsers) {
//                                    arrayList.add(temp);
//                                }
//
//                                for (Friends user: arrayList) {
//                                    if (!friend.getUserID().equals(user.getUserID())) {
//                                        mUsers.add(friend);
//                                    }
//                                }
//
//                            } else {
//                                mUsers.add(friend);
//                            }
                            mUsers.add(friend);
                        }
                    }
                }
                System.out.println("users");
                System.out.println(Arrays.toString(mUsers.toArray()));
                userAdapter = new UserAdapter(getContext(), mUsers);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
