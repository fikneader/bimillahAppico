package com.example.chiva.bimillahappico;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PostHolder extends RecyclerView.ViewHolder {
    TextView post,username,tanggal,harapan;

    public PostHolder (View itemView) {
        super(itemView);
        post = (TextView)itemView.findViewById(R.id.post);
        username = (TextView)itemView.findViewById(R.id.username);
        tanggal = (TextView)itemView.findViewById(R.id.tanggallapor);
        harapan = (TextView) itemView.findViewById(R.id.harapan);
    }
}
