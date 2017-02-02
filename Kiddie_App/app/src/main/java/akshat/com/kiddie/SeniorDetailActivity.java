package akshat.com.kiddie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by akshat on 10/05/2016.
 */

public class SeniorDetailActivity extends AppCompatActivity {
    String []ids=null;
    String []nms={"First","jOHN"};
    String []ages={"33","66"};
    String []gen={"Male","mALE"};
    String []lic={"Lic-1","lic"};
    String []pic={"Ch02","P1"};
    ListView lstSenior=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_detail);

        SharedPreferences sf= getSharedPreferences("User", Context.MODE_PRIVATE);
 String mob =sf.getString("mob","0");

        new ReadJSONList().execute("getseniorlist.php","mob",mob);

        lstSenior =(ListView) findViewById(R.id.lstSeniors);


        lstSenior.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


              final   String sid=ids[position];
                new AlertDialog.Builder(SeniorDetailActivity.this)
                        .setTitle("Delete Senior?")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                DeleteJson D= new DeleteJson();
                                D.execute("removesenior.php","delid",sid);

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();



            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intt= new Intent(SeniorDetailActivity.this,AddSeniorActivity.class);
                startActivity(intt);
            }
        });
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SeniorDetailActivity.this, ParentHomeActivity.class));
        finish();

    }

    class DeleteJson extends AsyncTask<String, Void, String>
    {
        String []param;
        @Override
        protected String doInBackground(String... params) {
            this.param = params;
            String msg= postData(params);
            return msg;
        }

        @Override
        protected void onPostExecute(final String result) {
        Toast.makeText(SeniorDetailActivity.this,"Senior Deleted",Toast.LENGTH_LONG).show();
            finish();
            startActivity(getIntent());
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
    class ReadJSONList extends AsyncTask<String, Void, String>
    {
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

    nms= new String [array.length()];
    ages=new String [array.length()];
    gen=new String [array.length()];
    lic=new String [array.length()];
    ids = new String[array.length()];
pic= new String[array.length()];
    for(int i=0;i<array.length();i++)
    {
        JSONObject object= array.getJSONObject(i);
        ids[i] = object.getString("intSeniorID");
        nms[i]= object.getString("strSeniorName");
        ages[i]=object.getString("intAge");
        gen[i] = object.getString("strGender");
        lic[i]=object.getString("strLicenceNo");
        pic[i]= object.getString("strPickupStand");
    }
    SeniorDataAdapter adp= new SeniorDataAdapter(SeniorDetailActivity.this,nms,ages,gen,lic,pic);
    lstSenior.setAdapter(adp);
    lstSenior.invalidate();

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
