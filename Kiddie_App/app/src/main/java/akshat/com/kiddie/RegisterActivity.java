package akshat.com.kiddie;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.params.HttpConnectionParams;

/**
 * Created by akshat on 09/29/2016.
 */
public class RegisterActivity extends Activity {
    EditText edFname = null;
    EditText edMobile = null;
    EditText edPassword = null;
    EditText edAddress = null;
    EditText edtCPass=null;
String page="parentregister.php";
    Button btnRegister = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);


        edAddress = (EditText) findViewById(R.id.edtAddressRegister);
        edFname = (EditText) findViewById(R.id.edtFullName);
        edMobile = (EditText) findViewById(R.id.edtMobileRegister);
        edPassword = (EditText) findViewById(R.id.edtPaswordRegister);
        edtCPass= (EditText)findViewById(R.id.edtCPaswordRegister);

        btnRegister = (Button) findViewById(R.id.btnRegisterNow);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cnm = edFname.getText().toString();
                String cad = edAddress.getText().toString();
                String cps = edPassword.getText().toString();
                String cps1= edtCPass.getText().toString();
                String mob = edMobile.getText().toString();

                if(!cps.equals(cps1))
                {
                    Toast.makeText(RegisterActivity.this,"Password Does not Match",Toast.LENGTH_LONG).show();
                    return ;
                }
    SendData S=new SendData(RegisterActivity.this,page,"");
                S.execute(page,"dnm",cnm,"dad",cad,"dps",cps,"mob",mob);

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
