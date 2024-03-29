package com.android.group_12.crushy.DatabaseWrappers;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {

    public String userID;
    public String name;
    public String birthday;
    public String bodyType;
    public String city;
    public String description;
    public String email;
    public String gender;
    public String hobbies;
    public String occupation;
    public String profileImageUrl;
    public String relationshipStatus;
    public String height;
    public String weight;

    public Map<String, Boolean> stars = new HashMap<>();

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public User(String userID, String name, String birthday, String email, String bodyType,
                String city, String description, String gender, String hobbies, String occupation,
                String profileImageUrl, String relationshipStatus, String height, String weight) {

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

        return result;
    }
}
