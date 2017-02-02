package akshat.com.kiddie;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Created by akshat on 10/11/2016.
 */

public class EditProfileDActivity extends AppCompatActivity {

    EditText pname;
    EditText paddress;
    EditText plic;
    EditText pphone;
    EditText pschool;
Button btnD;
    String page="getdriverdetail.php";
    String lid="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_d);

        pname= (EditText)findViewById(R.id.edtPName);
        paddress=(EditText)findViewById(R.id.edtAddressD);
        pschool=(EditText)findViewById(R.id.editText3);
        plic=(EditText)findViewById(R.id.editText4);
        pphone=(EditText)findViewById(R.id.editText5);
        btnD=(Button)findViewById(R.id.cmdUpdateD);

        SharedPreferences sf= getSharedPreferences("User", Context.MODE_PRIVATE);
        lid=sf.getString("cid","0");

        SendDataGetD S=new SendDataGetD(EditProfileDActivity.this,page,"checkotp");
        S.execute(page,"Submit","Submit","intLoginID",lid);


        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pg= "updatedriver.php";

                String dnm=pname.getText().toString();
                String dadr=paddress.getText().toString();
                String dlic= plic.getText().toString();
                String dsch= pschool.getText().toString();
                String dphn= pphone.getText().toString();

                SendDataUpdateD S= new SendDataUpdateD(EditProfileDActivity.this,pg,"");
                S.execute(pg,"Submit","Submit","intLoginID",lid,"strName",dnm,"strSchool",dsch,"strLic",dlic,"strAddress",dadr,"strPhone",dphn);

            }
        });

    }

    class SendDataUpdateD  extends AsyncTask<String, Void, String> {

        Context context;
        String callpage;
        String resultpage;
        String []param;

        public SendDataUpdateD(Context context,String callpage,String resultpage)
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

Intent intt= new Intent(EditProfileDActivity.this,UserHomeActivity.class);
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


    class SendDataGetD  extends AsyncTask<String, Void, String> {

        Context context;
        String callpage;
        String resultpage;
        String []param;

        public SendDataGetD(Context context,String callpage,String resultpage)
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
                pname.setText(obj.getString("strDriverName"));
                paddress.setText(obj.getString("strAddress"));
                plic.setText(obj.getString("strLicenceNo"));
                pschool.setText(obj.getString("strSchoolName"));
               pphone.setText(obj.getString("strPhoneNo"));

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
