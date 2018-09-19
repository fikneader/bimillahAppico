package com.example.chiva.bimillahappico;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DataPost> items;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    DatabaseReference myRef;
    Firebase firebase;

    public PostAdapter(Context context, ArrayList<DataPost> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lapor, null);
        ViewHolder view = new ViewHolder(layoutView,context);
        return view;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataPost dataPost = items.get(position);
        holder.username.setText(dataPost.getUsername());
        holder.post.setText(dataPost.getPost());
        holder.tanggal.setText(dataPost.getTanggal());
        holder.harapan.setText(dataPost.getHarapan());
        holder.terlapor.setText(dataPost.getTerlapor());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Context context;
        public TextView username,post,tanggal,harapan,terlapor;

        public ViewHolder(View itemView, final Context context) {
            super(itemView);
            this.context = context;
            username = itemView.findViewById(R.id.username);
            post = itemView.findViewById(R.id.post);
            tanggal = itemView.findViewById(R.id.tanggallapor);
            harapan =itemView.findViewById(R.id.harapan);
            terlapor = itemView.findViewById(R.id.terlapor);

            auth = FirebaseAuth.getInstance();

            //get current user
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            authListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) {
                        // user auth state is changed - user is null
                        // launch login activity
                       // startActivity(new Intent(getContext(), LoginActivity.class));
                        Intent mIntent = new Intent(context, LoginActivity.class);
                        context.startActivity(mIntent);
                    }
                }
            };

            final FirebaseDatabase database = FirebaseDatabase.getInstance();

            myRef = database.getReference().child("Post");

            itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                int position = getAdapterPosition();
                                                DataPost dataPost = items.get(position);

                                                //DefinitionFragment definitionFragment = new DefinitionFragment();
                                                DetailPostActivity detailPostActivity = new DetailPostActivity();
                                                Intent mIntent = new Intent(context, DetailPostActivity.class);
                                                mIntent.putExtra("USERNAME",dataPost.getUsername());
                                                mIntent.putExtra("POST",dataPost.getPost());
                                                mIntent.putExtra("JUDUL",dataPost.getJudul());
                                                mIntent.putExtra("TERLAPOR",dataPost.getTerlapor());
                                                mIntent.putExtra("TGLPOST",dataPost.getTanggal());
                                                mIntent.putExtra("HARAPAN",dataPost.getHarapan());

//                                                mIntent.putExtra("GAMBAR",dictionaryModel.getGambar());
//                                                mIntent.putExtra("FAVORITE",dictionaryModel.getFavorite());
//                                                mIntent.putExtra("ID",dictionaryModel.getId());
                                                Bundle bundle = new Bundle();

                                                if(bundle != null){
                                                    bundle.putString("USERNAME",dataPost.getUsername());
                                                    bundle.putString("POST",dataPost.getPost());
                                                    bundle.putString("TGLPOST",dataPost.getTanggal());
                                                    bundle.putString("HARAPAN",dataPost.getHarapan());
                                                    bundle.putString("TERLAPOR",dataPost.getTerlapor());
//                                                    bundle.putString("GAMBAR",dictionaryModel.getGambar());
//                                                    bundle.putString("FAVORITE",dictionaryModel.getFavorite());
//                                                    bundle.putString("ID",dictionaryModel.getId());
                                                    //definitionFragment.setArguments(bundle);
                                                    //definitionActivity.setArguments(bundle);
                                                } else {
                                                    Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                                }
                                                context.startActivity(mIntent);
                                                //Toast.makeText(view.getContext(), dataPost.getPost(), Toast.LENGTH_SHORT).show();
                                                //((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.content, definitionFragment).commit();

                                            }
                                        }

            );
        }

    }
}