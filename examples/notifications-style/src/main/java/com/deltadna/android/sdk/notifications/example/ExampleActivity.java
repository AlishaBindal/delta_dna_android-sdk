/*
 * Copyright (c) 2016 deltaDNA Ltd. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.deltadna.android.sdk.notifications.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.deltadna.android.sdk.DDNA;
import com.deltadna.android.sdk.notifications.DDNANotifications;
import com.google.firebase.iid.FirebaseInstanceId;

public class ExampleActivity extends AppCompatActivity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_example);
        
        DDNA.instance().startSdk();
        
        final String id = DDNA.instance().getUserId();
        Log.d(BuildConfig.LOG_TAG, "User id: " + id);
        ((TextView) findViewById(R.id.user_id)).setText(getString(
                R.string.user_id,
                id));
        
        final String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(BuildConfig.LOG_TAG, "Registration token: " + token);
        ((TextView) findViewById(R.id.registration_token)).setText(getString(
                R.string.registration_token,
                token));
    }
    
    @Override
    public void onDestroy() {
        DDNA.instance().stopSdk();
        
        super.onDestroy();
    }
    
    public void onUploadEvents(View view) {
        DDNA.instance().upload();
    }
    
    /**
     * Unregister from push notifications.
     * <p>
     * If you would like to handle the retrieval of the GCM registration token
     * manually then you can call {@link DDNA#clearRegistrationId()} instead.
     */
    public void onUnregister(View view) {
        DDNANotifications.unregister();
    }
    
    public void onNotificationOpened(View view) {
        // pretend the user opened a push notification
        DDNA.instance().recordNotificationOpened(false, Bundle.EMPTY);
    }
    
    public void onNotificationDismissed(View view) {
        // pretend the user dismissed a push notification
        DDNA.instance().recordNotificationDismissed(Bundle.EMPTY);
    }
    
    public void onStopSdk(View view) {
        DDNA.instance().stopSdk();
    }
    
    public void onStartSdk(View view) {
        DDNA.instance().startSdk();
    }
}