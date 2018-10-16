package edu.rupp.ckcc.networkcommunicate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {

    private TextView txtname,txtmajor,txtgender,txtdob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
       txtname=findViewById(R.id.name);
        txtmajor=findViewById(R.id.major);
        txtgender=findViewById(R.id.gender);
        txtdob=findViewById(R.id.dob);

        //load data from web service
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        String url="http://test.js-cambodia.com/ckcc/profile.php";
        JsonObjectRequest request=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){

//                    String name=response.getString("name");
//                    String major=response.getString("major");
//                    String gender=response.getString("gender");
//                    String dob=response.getString("dob");
//                    txtname.setText(name);
//                    txtmajor.setText(major);
//                    txtgender.setText(gender);
//                    txtdob.setText(dob);


                    //Get Data convert json to object
                    Gson gson=new Gson();
                    Profile profile=gson.fromJson(response.toString(),Profile.class);
                    txtname.setText(profile.getName());
                    txtmajor.setText(profile.getMajor());
                    txtgender.setText(profile.getGender());
                   txtdob.setText(profile.getDob());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);

    }
}
