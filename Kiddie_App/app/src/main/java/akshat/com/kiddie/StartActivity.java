package akshat.com.kiddie;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by akshat on 10/01/2016.
 */

public class StartActivity extends AppCompatActivity {

    final int PERMISSION_LOCATION_REQUEST_CODE = 1;
    Button btnNewDriver =null;
    Button btnNewParent =null;
    Button btnCur=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(StartActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(StartActivity.this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_LOCATION_REQUEST_CODE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        btnCur= (Button)findViewById(R.id.cmdCurentUserOption);
        btnNewDriver=(Button)findViewById(R.id.cmdNewDriverOption);
        btnNewParent=(Button)findViewById(R.id.cmdNewParent);



        btnCur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt= new Intent(StartActivity.this,MainActivity.class);
                startActivity(intt);
            }
        });


        btnNewDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt= new Intent(StartActivity.this,DriverRegisterActivity.class);
                startActivity(intt);
            }
        });



        btnNewParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt= new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(intt);
            }
        });

     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
