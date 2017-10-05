package cubesquaretech.com.pushnotifyfairbase;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView txtViewDeviceId;
    String unique_id;
    Button tokenbtn;
    String app_server_url="http://192.168.1.101/fcmtest/fcm_insert.php";
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TextView ShowToken = (TextView) findViewById(R.id.txtViewTokenID);
        //Get the token
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token: " + token);
        ShowToken.setText(token);

        txtViewDeviceId = (TextView) findViewById(R.id.txtViewDeviceId);

        unique_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        txtViewDeviceId.setText(unique_id);


        //tokenbtn= (Button) findViewById(R.id.tokenbtn);
        ShowToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the token
//                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
//               final String token =sharedPreferences.getString(getString(R.string.FCM_TOKEN),"");
               Toast.makeText(MainActivity.this, "The Token Id is : \n "+token, Toast.LENGTH_SHORT).show();


//                StringRequest stringRequest=new StringRequest(Request.Method.POST, app_server_url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
//                    }
//                })
//                {
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//
//                        Map<String,String> params = new HashMap<String, String>();
//                        params.put("devid",token);
//
//                        return params;
//                    }
//                };
//                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                        app_server_url, null,
//                        new Response.Listener<JSONObject>() {
//
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                Log.d(TAG, response.toString());
//                                Toast.makeText(MainActivity.this, "the response is "+response, Toast.LENGTH_SHORT).show();
//                            }
//                        }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        VolleyLog.d(TAG, "Error: " + error.getMessage());
//                        Toast.makeText(MainActivity.this, "The error is "+error, Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//
//
//                    @Override
//                    protected Map<String, String> getParams() {
//                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("devid",token);
//                        return params;
//                    }
//
//                };

// Adding request to request queue

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, app_server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Toast.makeText(MainActivity.this, " The response is "+response, Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "The response is "+response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: "+error);
                        Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }


                })
                {
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("devid",token);
                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Content-Type","application/x-www-form-urlencoded");
                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

               // jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
                queue.add(stringRequest);
                queue.start();

            }
        });



    }

//    private void sendRegistrationToServer(String token) {
//        // send token to web service ??
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("server/saving-data/IDs");
//        // then store your token ID
//        ref.push().setvalue(token);
//    }
    }
