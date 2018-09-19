package com.example.chiva.bimillahappico;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditProfilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditProfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    public BottomNavigationView navigationView;
    DatabaseReference rootReff, userReff;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EditProfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProfilFragment newInstance(String param1, String param2) {
        EditProfilFragment fragment = new EditProfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profil, container, false);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        rootReff = FirebaseDatabase.getInstance().getReference();
        userReff = rootReff.child("User");

        final EditText notelp = (EditText) view.findViewById(R.id.notelp);
        userReff.child(user.getUid()).child("notelp").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                String notelp2 = dataSnapshot.getValue(String.class);
                notelp.setText(notelp2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final EditText asal = (EditText) view.findViewById(R.id.asal);
        userReff.child(user.getUid()).child("daerahasal").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                String asal2 = dataSnapshot.getValue(String.class);
                asal.setText(asal2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final EditText pekerjaan = (EditText) view.findViewById(R.id.pekerjaan);
        userReff.child(user.getUid()).child("pekerjaan").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                String pekerjaan2 = dataSnapshot.getValue(String.class);
                pekerjaan.setText(pekerjaan2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final EditText bio = (EditText) view.findViewById(R.id.bio);
        userReff.child(user.getUid()).child("tentangsaya").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                String bio2 = dataSnapshot.getValue(String.class);
                bio.setText(bio2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button simpan = (Button) view.findViewById(R.id.simpanprofil);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notelp3 = notelp.getText().toString().trim();
                userReff.child(user.getUid()).child("notelp").setValue(notelp3);

                String asal3 = asal.getText().toString().trim();
                userReff.child(user.getUid()).child("daerahasal").setValue(asal3);

                String pekerjaan3 = pekerjaan.getText().toString().trim();
                userReff.child(user.getUid()).child("pekerjaan").setValue(pekerjaan3);

                String bio3 = bio.getText().toString().trim();
                userReff.child(user.getUid()).child("tentangsaya").setValue(bio3);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new ProfilFragment()).commit();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        void onFragmentInteraction(Uri uri);
    }
}
