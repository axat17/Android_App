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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Created by akshat on 11/18/2016.
 */

public class ChangePass extends AppCompatActivity {

    EditText txtC;
    EditText txtN;
    EditText txtCn;
    Button btnc;
    String page="changepass1.php";
    String lid="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        SharedPreferences sf= getSharedPreferences("User", Context.MODE_PRIVATE);
        lid=sf.getString("cid","0");

        txtC= (EditText)findViewById(R.id.txtCurrentPass);
        txtN=(EditText)findViewById(R.id.txtNewPass);
        txtCn=(EditText)findViewById(R.id.txtConfirmPass);
        btnc=(Button)findViewById(R.id.cmdChange);


        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String op = txtC.getText().toString();
                String ps = txtN.getText().toString();
                String ps1 = txtCn.getText().toString();
                if (!ps.equals(ps1)) {
                    Toast.makeText(ChangePass.this, "Password Does not Match", Toast.LENGTH_LONG).show();
                } else
                {
                    SendDataCP1 S=new SendDataCP1(ChangePass.this,page,"checkotp");
                    S.execute(page,"Submit","Submit","txtLoginID",lid,"txtPassword",op,"txtNewPass",ps);


                }
            }
        });
    }

    class SendDataCP1  extends AsyncTask<String, Void, String> {

        Context context;
        String callpage;
        String resultpage;
        String []param;

        public SendDataCP1(Context context,String callpage,String resultpage)
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

                    if(result.contains("Changed")) {
                        Intent intt = new Intent(ChangePass.this, MainActivity.class);
                        startActivity(intt);
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

}
