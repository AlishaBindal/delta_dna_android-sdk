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

package com.deltadna.android.sdk.exceptions;

import com.deltadna.android.sdk.net.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public final class ResponseExceptionFactoryTest {
    
    @Test
    public void create() {
        assertThat(ResponseExceptionFactory.create(new Response<String>(400, null, null)))
                .isInstanceOf(BadRequestException.class);
        assertThat(ResponseExceptionFactory.create(new Response<String>(404, null, null)))
                .isInstanceOf(ResponseException.class);
    }
}
