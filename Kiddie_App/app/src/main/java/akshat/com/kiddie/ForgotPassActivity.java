package akshat.com.kiddie;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Created by akshat on 11/19/2016.
 */

public class ForgotPassActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    Button cmdGetOTP=null;
    String page="checkuser.php";
    String mob="";
    EditText edtMobile =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        edtMobile=(EditText)findViewById(R.id.edtFPPhoneNo);
        cmdGetOTP=(Button)findViewById(R.id.cmdGetOTP);

        cmdGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendDataFP S=new SendDataFP(ForgotPassActivity.this,page,"checkotp");

                mob=edtMobile.getText().toString().trim();

                S.execute(page,"Submit","Submit","txtUserName",mob);
                Toast.makeText(ForgotPassActivity.this,Common.msg,Toast.LENGTH_LONG).show();
            }
        });
    }

    class SendDataFP  extends AsyncTask<String, Void, String> {

        Context context;
        String callpage;
        String resultpage;
        String []param;

        public SendDataFP(Context context,String callpage,String resultpage)
        {
            this.context= context;
            this.callpage=callpage;
            this.resultpage=resultpage;
        }
        @Override
        protected String doInBackground(String... params) {
            this.param = params;
            String msg= postData(params);
            return msg;
        }

        @Override
        protected void onPostExecute(final String result) {
           // Toast.makeText(context,"Process Over",Toast.LENGTH_LONG).show();
           // Common.msg=result;

            AlertDialog.Builder alertdg= new AlertDialog.Builder(context);
            alertdg.setTitle(result);
            alertdg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(result.split(":").length>1)
                    {
                        if(resultpage.equals("checkotp")) {


                            if (ContextCompat.checkSelfPermission(context,
                                    Manifest.permission.SEND_SMS)
                                    != PackageManager.PERMISSION_GRANTED) {
                                if (ActivityCompat.shouldShowRequestPermissionRationale(ForgotPassActivity.this, Manifest.permission.SEND_SMS)) {
                                } else {
                                    ActivityCompat.requestPermissions(ForgotPassActivity.this, new String[]{Manifest.permission.SEND_SMS},
                                            MY_PERMISSIONS_REQUEST_SEND_SMS);
                                }
                            }


                            SmsManager smsManager = SmsManager.getDefault();
                            int ccode = (int) (Math.random() * 10000);
                            String code = "OTP for Password Recovery :" + ccode;

                            //Uncomment below Line to Send SMS
                            smsManager.sendTextMessage(mob, null, code, null, null);

                            //Commment Below Line
                            cmdGetOTP.setText(ccode + "");
                            Intent intt = new Intent(ForgotPassActivity.this, GetOTPActivity.class);
                            intt.putExtra("code", ccode + "");
                            intt.putExtra("mob",mob);
                            startActivity(intt);
                        }
                    }
                }
            });
            alertdg.show();
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}


        public String postData(String... param) {//,String cnm, String cad, String cps, String mob, String city) {
            // Create a new HttpClient and Post Header
            String page=param[0];
            String msg="";
            try {

                java.net.URL url = new java.net.URL(Common.ip+page);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();



                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(45000);

                conn.setDoInput(true);
                conn.setDoOutput(true);

                String charset = "UTF-8";
                String s = "";

                for(int i=1;i<param.length;i++)
                {
                    s+= "&"+param[i++] + "=" + URLEncoder.encode(param[i], charset);
                }
                Log.d("SendData","SendData "+s);

                conn.setFixedLengthStreamingMode(s.getBytes().length);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                out.print(s);
                out.close();

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    Log.d("SendData", "SendData:" + inputLine);
                    msg+=inputLine;
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed :"+e.getMessage();
//            Toast.makeText(context,"Error "+e.getMessage(),Toast.LENGTH_LONG).show();
            }
            return msg;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}
