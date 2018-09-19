package com.example.chiva.bimillahappico;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    DatabaseReference myRef;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
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
        final View view = inflater.inflate(R.layout.fragment_post, container, false);

        final EditText judul = view.findViewById(R.id.jdlaporan);
        final EditText pihakterlapor = view.findViewById(R.id.pihakygdilaporkan);
        final EditText textPost = view.findViewById(R.id.deskripsilaporan);
        final EditText harapan = view.findViewById(R.id.harapan);
        final Button buttonKirim = view.findViewById(R.id.buttonKirim);
        final ImageView kirimGambar = view.findViewById(R.id.fotoicon);

        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        myRef = database.getReference().child("Post").push();
        kirimGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "Fitur Tambah Gambar dalam Perbaikan", Toast.LENGTH_SHORT).show();
            }
        });

        buttonKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);

                final String username = user.getDisplayName();
                final String judulku = judul.getText().toString().trim();
                final String terlapor = pihakterlapor.getText().toString().trim();
                final String postku = textPost.getText().toString().trim();
                final String harapanku = harapan.getText().toString().trim();

                if (TextUtils.isEmpty(judulku)) {
                    Toast.makeText(getContext(), "Masukan Judul Laporan!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(terlapor)) {
                    Toast.makeText(getContext(), "Masukan Pihak Terlapor!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(postku)) {
                    Toast.makeText(getContext(), "Masukan Kiriman Laporan Anda!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(harapanku)) {
                    Toast.makeText(getContext(), "Masukan Harapan dari Laporan Anda!", Toast.LENGTH_SHORT).show();
                    return;
                }
                myRef.child("TextKiriman").setValue(postku);
                myRef.child("Judulku").setValue(judulku);
                myRef.child("Terlapor").setValue(terlapor);
                myRef.child("Harapanku").setValue(harapanku);
                myRef.child("Username").setValue(username);
                myRef.child("Gambar").setValue("Gambar");
                myRef.child("TglPost").setValue(formattedDate);

                textPost.getText().clear();
                judul.getText().clear();
                pihakterlapor.getText().clear();
                harapan.getText().clear();
                //Toast.makeText(view.getContext(), "Kirim", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new homeFragment()).commit();
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
