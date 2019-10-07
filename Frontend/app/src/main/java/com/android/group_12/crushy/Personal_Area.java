package com.android.group_12.crushy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Personal_Area.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Personal_Area#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Personal_Area extends Fragment {
    //    private OnFragmentInteractionListener mListener;
    private int fragmentHeight;
    private int fragmentWidth;
    //private FirebaseAuth mAuth;
    private ImageView UserImage;
    private TextView UserID,UserName,FollowerNum,FollowingNum;
    private LinearLayout MyProfile,Following,Follower;
    private RelativeLayout Calendar,BlockList,Invitation,Setting,About;


    public Personal_Area(int fragmentHeight, int fragmentWidth) {
        this.fragmentHeight = fragmentHeight;
        this.fragmentWidth = fragmentWidth;
    }
    private OnFragmentInteractionListener mListener;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment Personal_Area.
     */
    // TODO: Rename and change types and number of parameters
    public static Personal_Area newInstance(int fragmentHeight, int fragmentWidth) {
        Personal_Area fragment = new Personal_Area(fragmentHeight, fragmentWidth);
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mAuth = FirebaseAuth.getInstance();
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_personal__area, container, false);

        UserImage = (ImageView) view.findViewById(R.id.UserImageView);
        UserID = (TextView) view.findViewById(R.id.UserID);
        UserName = (TextView) view.findViewById(R.id.UserName);
        FollowerNum = (TextView) view.findViewById(R.id.FollowersNum);
        FollowingNum = (TextView) view.findViewById(R.id.FollowingNum);
        MyProfile = (LinearLayout) view.findViewById(R.id.MyProfile);
        Follower = (LinearLayout) view.findViewById(R.id.Follower);
        Following = (LinearLayout) view.findViewById(R.id.Following);
        Calendar = (RelativeLayout) view.findViewById(R.id.Calendar);
        BlockList = (RelativeLayout) view.findViewById(R.id.Blockedlist);
        Invitation = (RelativeLayout) view.findViewById(R.id.Invitation);
        Setting = (RelativeLayout) view.findViewById(R.id.Setting);
        About = (RelativeLayout) view.findViewById(R.id.About);
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

        MyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PersonalFilePage;
                PersonalFilePage = new Intent(getActivity(), UserProfile.class);
                startActivity(PersonalFilePage);
            }
        });


        return view;
    }


//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        System.out.println("PA, line 73");
//        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        System.out.println("PA, line 75");
//        layoutParams.height = this.fragmentHeight;
//        view.setLayoutParams(layoutParams);
//        ImageView userImageView = getView().findViewById(R.id.potential_friend_image);
//
//        // Set the image view size, with height:width = 4:3.
//        ViewGroup.LayoutParams imageViewParams = userImageView.getLayoutParams();
//
//        // Calculate the remaining height, as the app navigation and button group height are fixed.
//        LinearLayout buttonGroup = getView().findViewById(R.id.button_group);
//        BottomNavigationView appNavigationBar = getView().findViewById(R.id.nav_view);
//
//        int expectedHeight = this.fragmentWidth * 4 / 3;
//        int remainingHeight = this.fragmentHeight - buttonGroup.getLayoutParams().height;
//
//        userImageView.getLayoutParams().width = this.fragmentWidth;
//        userImageView.getLayoutParams().height = remainingHeight > expectedHeight ? expectedHeight : remainingHeight;
//
//        if (expectedHeight > this.fragmentHeight) {
//            userImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        }
//
//        // Set the default image.
//        userImageView.setImageResource(R.drawable.ic_poker_2);
//
//
//        System.out.println("image view height = " + imageViewParams.height + ", width = " + imageViewParams.width);
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);

    }


}