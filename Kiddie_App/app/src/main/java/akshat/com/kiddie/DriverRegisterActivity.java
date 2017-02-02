package akshat.com.kiddie;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by akshat on 09/28/2016.
 */
public class DriverRegisterActivity extends Activity {
    EditText edFname = null;
    EditText edMobile = null;
    EditText edPassword = null;
    EditText edAddress = null;
    EditText edLic =null;
    EditText edSchool=null;
    EditText edDob=null;
    EditText edCPass= null;

String page="driverregister.php";
    Button btnRegister = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_register_activity);


        edAddress = (EditText) findViewById(R.id.edtDAddressRegister);
        edFname = (EditText) findViewById(R.id.edtDFullName);
        edMobile = (EditText) findViewById(R.id.edtDMobileRegister);
        edPassword = (EditText) findViewById(R.id.edtDPaswordRegister);
        edCPass=(EditText)findViewById(R.id.edtDPaswordRegister1);
        edLic=(EditText)findViewById(R.id.edtDLicence);
        edSchool=(EditText)findViewById(R.id.edtDSchoolName);
        edDob=(EditText)findViewById(R.id.edtDDob);


        btnRegister = (Button) findViewById(R.id.btnDRegisterNow);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cnm = edFname.getText().toString();
                String cad = edAddress.getText().toString();
                String cps = edPassword.getText().toString();
                String cps1 = edCPass.getText().toString();

                String mob = edMobile.getText().toString();
                String dob= edDob.getText().toString();
                String lic= edLic.getText().toString();
                String sch= edSchool.getText().toString();

                if(!cps.equals(cps1))
                {
                    Toast.makeText(DriverRegisterActivity.this,"Password Does not Match",Toast.LENGTH_LONG).show();
                    return ;
                }

    SendData S=new SendData(DriverRegisterActivity.this,page,"");
                S.execute(page,"dnm",cnm,"dad",cad,"dps",cps,"mob",mob,"dob",dob,"lic",lic,"dschool",sch);

Log.d("SendData","SendData +:"+Common.msg);

               // postData(cnm,cad,cps,mob,city);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Register Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://akshat.com.kiddie/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Register Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://akshat.com.kiddie/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
