/*
 * Copyright (c) 2018 deltaDNA Ltd. All rights reserved.
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

package com.deltadna.android.sdk.example;

import android.app.Application;
import android.util.Log;

import com.deltadna.android.sdk.BuildConfig;
import com.deltadna.android.sdk.DDNA;
import com.deltadna.android.sdk.consent.ConsentTracker;

public class ExampleApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        /*
         * Initialise the SDK with your environment key, collect/engage urls,
         * and any additional attributes. After this point an SDK instance
         * will always be guaranteed to be present, until Android terminates
         * the application.
         */
//        DDNA.initialise(new DDNA.Configuration(
//                this,
//                "7a4ab410-e9f7-4dbe-b951-38e457825839",
//                "production",
//                "a6bd5ab0-ea8b-41a4-968e-d58b6c86d530",
//                "https://collect.analytics.unity3d.com/api/analytics/collect/v1/projects",
//                "https://services.api.unity.com/remote-config/v1/projects")
//                .clientVersion(BuildConfig.VERSION_NAME));

        DDNA.instance().isPiplConsentRequired(new ConsentTracker.Callback() {
            @Override
            public void onSuccess(boolean requiresConsent) {
                if (requiresConsent) {
                    // In our example, we assume we have consent, but you should check to make sure this is the case!
                    DDNA.instance().setPiplConsent(true, true);
                }
            }

            @Override
            public void onFailure(Throwable exception) {
                Log.e("EXAMPLE", "Failed to check for PIPL consent " + exception.getMessage(), exception);
                // Try again later.
            }
        });
    }
}
