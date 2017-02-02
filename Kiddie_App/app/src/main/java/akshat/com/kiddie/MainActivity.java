package akshat.com.kiddie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by akshat on 09/10/2016.
 */

public class MainActivity extends AppCompatActivity {

String page="login.php";
    String mob="";
    String pass="";
    String ut="";
    EditText edtMobile=null;
    EditText edtPassword= null;
    Button btnLogin= null;

Button btnForgot = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtMobile =(EditText)findViewById(R.id.txtPhone);
    edtPassword=(EditText)findViewById(R.id.txtPassword);
        btnLogin= (Button)findViewById(R.id.cmdLogin);
btnForgot=(Button)findViewById(R.id.cmdForgot);

        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt= new Intent(MainActivity.this,ForgotPassActivity.class);
                startActivity(intt);

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendData S=new SendData(MainActivity.this,page,"");
                mob=edtMobile.getText().toString().trim();
                pass = edtPassword.getText().toString().trim();

                SharedPreferences sf= getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit= sf.edit();

                edit.putString("mob",mob);
                edit.commit();

                S.execute(page,"Submit","Submit","txtUserName",mob,"txtPassword",pass);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
