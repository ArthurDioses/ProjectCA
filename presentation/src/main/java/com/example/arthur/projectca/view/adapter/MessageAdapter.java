package com.example.arthur.projectca.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arthur.projectca.R;
import com.example.arthur.projectca.model.MessageListViewModel;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<MessageListViewModel> messageModelList;
    private Context context;

    public MessageAdapter(List<MessageListViewModel> messageModelList) {
        this.messageModelList = messageModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MessageViewHolder(LayoutInflater.from(context).inflate(R.layout.row_message,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MessageViewHolder)holder).bindMessage(messageModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }
    class MessageViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvDescription)
        TextView tvDescription;

         MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        private void bindMessage(MessageListViewModel messageModel)
        {
            tvTitle.setText(messageModel.getMessages().get(0).getTitle());
            tvDescription.setText(messageModel.getMessages().get(0).getDescription());
        }
    }
}
