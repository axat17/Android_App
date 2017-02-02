package akshat.com.kiddie;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Created by akshat on 10/05/2016.
 */

public class EditProfilePActivity extends AppCompatActivity {

    EditText edt1=null;
    EditText edt2=null;
    EditText edt3=null;

    Button cmdSave= null;

    String lid="";
    String page="getparentdetail.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_p);

        edt2= (EditText)findViewById(R.id.edtEditProfilePAddress);
        edt1=(EditText)findViewById(R.id.edtEditProfilePName);
        edt3=(EditText)findViewById(R.id.edtEditProfilePPhone);

        cmdSave= (Button)findViewById(R.id.cmdSaveEditProfile);
        SharedPreferences sf= getSharedPreferences("User", Context.MODE_PRIVATE);
        lid=sf.getString("cid","0");


        SendDataGetP S=new SendDataGetP(EditProfilePActivity.this,page,"checkotp");
        S.execute(page,"Submit","Submit","intLoginID",lid);


        cmdSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pg= "updateparent.php";

                String dnm=edt1.getText().toString();
                String dadr=edt2.getText().toString();
                String dphn= edt3.getText().toString();

                SendDataUpdateP S= new SendDataUpdateP(EditProfilePActivity.this,pg,"");
                S.execute(pg,"Submit","Submit","intLoginID",lid,"strName",dnm,"strAddress",dadr,"strPhone",dphn);

            }
        });
    }


    class SendDataUpdateP  extends AsyncTask<String, Void, String> {

        Context context;
        String callpage;
        String resultpage;
        String []param;

        public SendDataUpdateP(Context context,String callpage,String resultpage)
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



            AlertDialog.Builder alertdg= new AlertDialog.Builder(context);
            alertdg.setTitle(result);
            alertdg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intt= new Intent(EditProfilePActivity.this,StartActivity.class);
                    startActivity(intt);
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


    class SendDataGetP  extends AsyncTask<String, Void, String> {

        Context context;
        String callpage;
        String resultpage;
        String []param;

        public SendDataGetP(Context context,String callpage,String resultpage)
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


            try
            {

                // JSONArray jsonarray = new JSONArray(result);
                JSONObject obj= new JSONObject(result);
                edt1.setText(obj.getString("strFullName"));
                edt2.setText(obj.getString("strAddress"));
                edt3.setText(obj.getString("strPhoneNo"));

            }catch(Exception E)
            {
                E.printStackTrace();
            }
            // pname.setText(result);


          /*  AlertDialog.Builder alertdg= new AlertDialog.Builder(context);
            alertdg.setTitle(result);
            alertdg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });
            alertdg.show();
            */
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

}
