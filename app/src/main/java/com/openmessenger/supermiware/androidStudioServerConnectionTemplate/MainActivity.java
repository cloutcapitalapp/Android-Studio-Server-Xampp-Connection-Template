package com.openmessenger.supermiware.androidStudioServerConnectionTemplate;

// SuperMiWare
//
// TODO: Be sure to look at the manifest file to see what you'll need there as well.
//

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


// All of the Volley imports you'll need are below this line.
//
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

// Make sure you have the Volley dependency in your App.Gradle file
// implementation 'com.android.volley:volley:1.0.0' - you will need this dependency to use the Volley library.
// I've made sure the add implementation as apposed to 'compile'.  Make note that the compile terminology has been
// deprecated.

import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    // Create all of our variables for this project.
    private Button button;
    private TextView textView;

    // This is where you'll need to creat a string for your server URL.  I personally use Xampp.
    // You can go here if you'd like to check out Xampp - https://www.apachefriends.org/index.html.
    // I think it's a great OPEN SOURCE server option.
    private String serverUrl = "YOUR_IP_ADDRESS/phpFILE.php";

    // Create a variable making our requests.
    private RequestQueue mReqestQueue;

    // Create a variable for retrieving a string from our PHP file within our Xampp server.
    // TODO: By the way.  When you get Xampp, you'll need to create a PHP file and place the file in
    // TODO: your " HTDocs " folder.  Just click on the Volume tab button in the Xampp UI and then
    // TODO: click explore.  The file location pops up and you can then just locate the - htdocs folder.
    // TODO: Create an index.php file and put your index.php file in that folder. YES REPLACE THE ORIGINAL FOLDER IF
    // TODO: THERE IS ONE!
    //
    // Just make the php file echo 'HELLO' or whatever you'd like it to say for testing your connection.
    //
    private StringRequest mStringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize your general vars.
        button = findViewById(R.id.outPutButton);
        textView = findViewById(R.id.textView);


        // Initialize your RequestQueue.
        mReqestQueue = Volley.newRequestQueue(MainActivity.this);

        // Set an onClickListener for your button.
        //
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create a new StringRequest request.
                mStringRequest = new StringRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {

                        // Inside the onResponse method we will do what we'd like to happen with our
                        // data since it has now been retrieved.
                        textView.setText(response);

                        // You can stop the requests once you'd done with them what you like.
                        mReqestQueue.stop();

                    }
                    // Now we need to make sure we tell the system what to do if the information does
                    // not come through, or if something is otherwise wrong.
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        textView.setText("Wait something went wrong...");

                        // FYI stack traces are mad helpful for debugging.
                        error.printStackTrace();

                        // And once the onErrorResponse has run we'll stop the request.
                        mReqestQueue.stop();
                    }
                });

                // I couldn't figure this out for a while.  But without this line this entire template
                // means nothing.  Will need to tell our request that it needs to add the string we
                // get from the server to our application.  And then we're done.  Everything comes together.
                mReqestQueue.add(mStringRequest);
            }
        });
    }
}
