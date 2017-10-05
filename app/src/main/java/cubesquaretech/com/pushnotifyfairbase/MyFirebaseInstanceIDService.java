package cubesquaretech.com.pushnotifyfairbase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Anand on 18-09-2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseInsIDService";

    @Override
    public void onTokenRefresh() {
        //Get updated token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "New Token: " + refreshedToken);

//        sendRegistrationToServer(refreshedToken);
        //You can save the token into third party server to do anything you want
    }

//    private void sendRegistrationToServer(String token) {
//        // send token to web service ??
//
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("server/saving-data/IDs");
//        // then store your token ID
//       // ref.push().setvalue(token);
//    }
}
