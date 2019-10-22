package com.android.group_12.crushy.DatabaseWrappers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {

    String userID;
    String name;
    String birthday;
    String bodyType;
    String city;
    String description;
    String gender;
    String hobbies;
    String occupation;
    String profileImageUrl;
    String relationshipStatus;
    String height;
    String weight;
    String email;

    ArrayList<String> fansList=new ArrayList<String>();
    ArrayList<String> likeList=new ArrayList<String>();
    ArrayList<String> friendsList=new ArrayList<String>();
    ArrayList<String> blockList=new ArrayList<String>();
    ArrayList<String> dislikeList=new ArrayList<String>();

    public Map<String, Boolean> stars = new HashMap<>();

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public User(String userID, String name, String email, String birthday, String bodyType, String city, String description, String gender, String hobbies, String occupation, String profileImageUrl, String relationshipStatus, String height, String weight, ArrayList<String> fansList, ArrayList<String> likeList, ArrayList<String> friendsList, ArrayList<String> blockList, ArrayList<String> dislikeList) {
        this.userID = userID;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.bodyType = bodyType;
        this.city = city;
        this.description = description;
        this.gender = gender;
        this.hobbies = hobbies;
        this.occupation = occupation;
        this.profileImageUrl = profileImageUrl;
        this.relationshipStatus = relationshipStatus;
        this.height = height;
        this.weight = weight;

        this.fansList=fansList;
        this.likeList=likeList;
        this.friendsList=friendsList;
        this.blockList = blockList;
        this.dislikeList = dislikeList;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("userID", userID);
        result.put("name", name);
        result.put("birthday", birthday);
        result.put("email", email);
        result.put("bodyType", bodyType);
        result.put("city", city);
        result.put("description", description);
        result.put("gender", gender);
        result.put("hobbies", hobbies);
        result.put("occupation", occupation);
        result.put("profileImageUrl", profileImageUrl);
        result.put("relationshipStatus", relationshipStatus);
        result.put("height", height);
        result.put("weight", weight);
        result.put("fansList", fansList);
        result.put("likeList", likeList);
        result.put("blockList", blockList);
        result.put("dislikeList", dislikeList);
        result.put("friendsList", friendsList);

        return result;
    }


}