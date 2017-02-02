package akshat.com.kiddie;

import android.content.Context;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Created by akshat on 10/18/2016.
 */

public class AddSeniorActivity extends AppCompatActivity {

    String page1="senioradd.php";
    String page="getdriverlist.php";
    Spinner spnRoute= null;
    Spinner spnGen=null;
    Spinner spnPic =null;

    EditText edtName=null;
    EditText edtAge=null;

    Button btnSave=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_senior);

        edtAge =(EditText)findViewById(R.id.edtSeniorAge);
        edtName=(EditText)findViewById(R.id.edtSeniorName);
        spnGen=(Spinner)findViewById(R.id.spnSeniorGen);
        spnPic=(Spinner)findViewById(R.id.spnSeniorPic);
        spnRoute= (Spinner)findViewById(R.id.spnSeniorLIC);
        btnSave=(Button)findViewById(R.id.cmdSeniorSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String snm= edtName.getText().toString();
                String age= edtAge.getText().toString();
                String route= spnRoute.getSelectedItem().toString();
                String gen= spnGen.getSelectedItem().toString();
                String pic=spnPic.getSelectedItem().toString();
                SharedPreferences sf= getSharedPreferences("User", Context.MODE_PRIVATE);
                String id=sf.getString("cid","0");


                SendData S=new SendData(AddSeniorActivity.this,page1,"");
                S.execute(page1,"dnm",snm,"age",age,"mid",id,"lic",route,"gen",gen,"pic",pic);

// csitpark.com/kiddie/senioradd.php?mid=44&dnm=John&lic=233&age=87&gen=Male&pic=ch


            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new ReadJSONList().execute(page);


    }



    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(AddSeniorActivity.this, ParentHomeActivity.class));
        finish();

    }

    class ReadJSONList extends AsyncTask<String, Void, String>
    {
        String []route;
        String []param;
        @Override
        protected String doInBackground(String... params) {
            this.param = params;
            String msg= postData(params);
            return msg;
        }

        @Override
        protected void onPostExecute(final String result) {

            try {
                JSONArray array = new JSONArray(result);

                route= new String [array.length()];

                for(int i=0;i<array.length();i++)
                {
                    JSONObject object= array.getJSONObject(i);
                    route[i]= object.getString("strSchoolName");

                }

                ArrayAdapter<String>adp= new ArrayAdapter<String>(AddSeniorActivity.this,
                        android.R.layout.simple_spinner_item, route);

                spnRoute.setAdapter(adp);
            }catch (Exception E)
            {
                E.printStackTrace();
            }

        }

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


