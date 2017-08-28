package com.example.shubham.parkingsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import static android.R.attr.data;

public class DealersActivity extends AppCompatActivity {

    private ListView listView;
    private DealerAdapter adapter;
    private Button picButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap=null;
    private String parsedVehicleno=null;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private static final int RC_OCR_CAPTURE = 9003;
    private static final String TAG = "MainActivity";
    private ChildEventListener mChildEventListener;
    //private OcrCaptureActivity ocrCaptureActivity;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String uid=null;
    private int charges=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealers);
        listView=(ListView)findViewById(R.id.dealer_list);
        Dealer t=new Dealer(15.0,"Rahul Somani","969682500");
        Dealer k=new Dealer(11.0,"Archit Goyal","969682500");
        List<Dealer> transactions=new ArrayList<Dealer>();
        transactions.add(t);
        transactions.add(k);
        adapter=new DealerAdapter(this,transactions);
       // ocrCaptureActivity=new OcrCaptureActivity(getApplicationContext());
        listView.setAdapter(adapter);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference();

        picButton=(Button)findViewById(R.id.pic_button);
        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DealersActivity.this,TextCaptureActivity.class);
                startActivityForResult(intent, RC_OCR_CAPTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==RC_OCR_CAPTURE){
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    parsedVehicleno = data.getStringExtra(TextCaptureActivity.Text);
                    //statusMessage.setText("Success");
                    //textValue.setText(text);
                    Log.d(TAG, "Text read: " + parsedVehicleno);
                } else {
                    //statusMessage.setText("Failure");
                    Log.d(TAG, "No Text captured, intent data is null");
                }
            } else {
                //statusMessage.setText("Failure");
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    // function to calculate price
    public int calculatePrice(Date prev,Date current,int charges){
        return 0;
    }
}
