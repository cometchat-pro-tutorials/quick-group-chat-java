package com.vucko.quickgroupchat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.MessagesRequest;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.BaseMessage;
import com.cometchat.pro.models.CustomMessage;
import com.cometchat.pro.models.MediaMessage;
import com.cometchat.pro.models.TextMessage;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.vucko.quickgroupchat.models.MessageWrapper;
import com.vucko.quickgroupchat.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class GroupChatActivity extends AppCompatActivity {

    MessagesListAdapter<MessageWrapper> adapter;
    private MessageInput messageInput;
    private MessagesList messagesList;
    private String groupId;
    private String listenerID = "IncomingMessageListener1";

    public static void start(Context context, String groupId) {
        Intent starter = new Intent(context, GroupChatActivity.class);
        starter.putExtra(Constants.GROUP_ID, groupId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        messageInput = findViewById(R.id.input);
        messagesList = findViewById(R.id.messagesList);
        ImageLoader imageLoader = (imageView, url, payload) -> Picasso.get().load(url).into(imageView);
        adapter = new MessagesListAdapter<>(CometChat.getLoggedInUser().getUid(), imageLoader);
        messagesList.setAdapter(adapter);

        messageInput.setInputListener(input -> {
            sendMessage(input.toString());
            return true;
        });
        groupId = getIntent().getStringExtra(Constants.GROUP_ID);

        addIncomingMessageListener();
        getPreviousMessages();
    }

    private void getPreviousMessages() {
        MessagesRequest messagesRequest = new MessagesRequest.MessagesRequestBuilder().setGUID(groupId).build();
        messagesRequest.fetchPrevious(new CometChat.CallbackListener<List<BaseMessage>>() {
            @Override
            public void onSuccess(List<BaseMessage> baseMessages) {
                addMessages(baseMessages);
            }

            @Override
            public void onError(CometChatException e) {

            }
        });
    }

    private void addMessages(List<BaseMessage> baseMessages) {
        List<MessageWrapper> messageWrappers = new ArrayList<>();
        for (BaseMessage message : baseMessages) {
            messageWrappers.add(new MessageWrapper((TextMessage) message));
        }
        adapter.addToEnd(messageWrappers, true);
    }

    private void addIncomingMessageListener() {
        CometChat.addMessageListener(listenerID, new CometChat.MessageListener() {
            @Override
            public void onTextMessageReceived(TextMessage textMessage) {
                addMessage(textMessage);
            }

            @Override
            public void onMediaMessageReceived(MediaMessage mediaMessage) {

            }

            @Override
            public void onCustomMessageReceived(CustomMessage customMessage) {

            }
        });
    }

    private void sendMessage(String message) {
        TextMessage textMessage = new TextMessage(groupId, message, CometChatConstants.MESSAGE_TYPE_TEXT, CometChatConstants.RECEIVER_TYPE_GROUP);

        CometChat.sendMessage(textMessage, new CometChat.CallbackListener<TextMessage>() {
            @Override
            public void onSuccess(TextMessage textMessage) {
                addMessage(textMessage);
            }

            @Override
            public void onError(CometChatException e) {

            }
        });
    }

    private void addMessage(TextMessage textMessage) {
        adapter.addToStart(new MessageWrapper(textMessage), true);
    }

}
