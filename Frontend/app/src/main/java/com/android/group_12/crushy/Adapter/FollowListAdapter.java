package com.android.group_12.crushy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.group_12.crushy.DatabaseWrappers.User;
import com.android.group_12.crushy.Activities.OtherProfilePageActivity;
import com.android.group_12.crushy.R;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.android.group_12.crushy.Constants.IntentExtraParameterName.UNIFORM_EXTRA_INFO_ACTIVITY_USER_ID;

public class FollowListAdapter extends RecyclerView.Adapter<FollowListAdapter.ViewHolder> {
    private Context context;
    private List<User> personalInfos;
    // the resource id of item layout
    private int resourceId;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.follow_image);
            text = view.findViewById(R.id.follow_text);
        }
    }

    public FollowListAdapter(List<User> listInfos, int resourceId, Context mContext) {
        this.personalInfos = listInfos;
        this.resourceId = resourceId;
        this.context = mContext;
    }

    @NonNull
    @Override
    //  Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent an item.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(resourceId, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User personalInfo = this.personalInfos.get(position);
        String profileUrl = personalInfo.profileImageUrl;
        if (profileUrl == null || profileUrl.equals("") || profileUrl.equals("N/A")) {
            holder.image.setImageResource(R.drawable.profile_image);
        } else {
            Glide.with(this.context).load(profileUrl).into(holder.image);
        }
        holder.text.setText(personalInfo.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OtherProfilePageActivity.class);
                intent.putExtra(UNIFORM_EXTRA_INFO_ACTIVITY_USER_ID, personalInfo.userID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.personalInfos.size();
    }

}