package com.example.chiva.bimillahappico;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<PostDetails> MainImageUploadInfoList;

    public RecyclerViewAdapter(Context context, List<PostDetails> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lapor, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PostDetails postDetails = MainImageUploadInfoList.get(position);

        holder.username.setText(postDetails.getUsername());
        holder.post.setText(postDetails.getPost());
        holder.tanggal.setText(postDetails.getTanggal());
        holder.harapan.setText(postDetails.getHarapan());
        holder.terlapor.setText(postDetails.getTerlapor());

    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username;
        public TextView post;
        public TextView tanggal,harapan,terlapor;

        public ViewHolder(View itemView) {

            super(itemView);

            username = (TextView) itemView.findViewById(R.id.username);
            post = (TextView) itemView.findViewById(R.id.post);
            tanggal = (TextView) itemView.findViewById(R.id.tanggallapor);
            harapan = (TextView) itemView.findViewById(R.id.harapan);
            terlapor = (TextView) itemView.findViewById(R.id.terlapor);
        }
    }
}