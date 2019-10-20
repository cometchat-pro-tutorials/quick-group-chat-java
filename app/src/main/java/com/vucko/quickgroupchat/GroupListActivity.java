package com.vucko.quickgroupchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.GroupsRequest;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.Group;
import com.vucko.quickgroupchat.adapters.GroupsAdapter;

import java.util.List;

public class GroupListActivity extends AppCompatActivity {

    private RecyclerView groupsRecyclerView;

    public static void start(Context context) {
        Intent starter = new Intent(context, GroupListActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        setupUI();
        getGroups();
    }

    private void getGroups() {
        GroupsRequest groupsRequest = new GroupsRequest.GroupsRequestBuilder().build();
        groupsRequest.fetchNext(new CometChat.CallbackListener<List<Group>>() {
            @Override
            public void onSuccess(List<Group> groups) {
                updateList(groups);
            }

            @Override
            public void onError(CometChatException e) {

            }
        });
    }

    private void updateList(List<Group> groups) {
        GroupsAdapter groupsAdapter = new GroupsAdapter(groups, this);
        groupsRecyclerView.setAdapter(groupsAdapter);
    }

    private void setupUI() {
        groupsRecyclerView = findViewById(R.id.groupsRecyclerView);
        groupsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
