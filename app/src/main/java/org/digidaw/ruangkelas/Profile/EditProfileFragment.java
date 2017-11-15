package org.digidaw.ruangkelas.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.digidaw.ruangkelas.R;
import org.digidaw.ruangkelas.Utilitas.UniversalImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;


public class EditProfileFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseUser mCurrentUser;
    private String userID;

    private ImageView mSaveBtn;

    private CircleImageView mDisplayImage;
    private EditText mUsername, mDisplayName, mWebiste, mAbout, mEmail, mPhoneNumber;
    private TextView editPhoto;

    private ProgressBar mProgresBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);


        return view;
    }
}
