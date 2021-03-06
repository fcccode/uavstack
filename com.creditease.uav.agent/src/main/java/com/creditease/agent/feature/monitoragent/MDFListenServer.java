/*-
 * <<
 * UAVStack
 * ==
 * Copyright (C) 2016 - 2017 UAVStack
 * ==
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * >>
 */

package com.creditease.agent.feature.monitoragent;

import com.creditease.agent.http.api.UAVHttpMessage;
import com.creditease.agent.spi.AbstractHttpServiceComponent;
import com.creditease.agent.spi.HttpMessage;

/**
 * 
 * MDFListenServer description: 用于接收第三方推送的MDF或MDF数组，实现 实时数据的采集接收
 *
 */
public class MDFListenServer extends AbstractHttpServiceComponent<UAVHttpMessage> {

    public MDFListenServer(String cName, String feature, String initHandlerKey) {
        super(cName, feature, initHandlerKey);
    }

    @Override
    protected UAVHttpMessage adaptRequest(HttpMessage message) {

        String messageBody = message.getRequestBodyAsString("UTF-8");

        if (log.isDebugEnable()) {
            log.debug(this, "MDFListenServer Request: " + messageBody);
        }
        UAVHttpMessage msg = new UAVHttpMessage();

        msg.putRequest(UAVHttpMessage.BODY, messageBody);

        return msg;
    }

    @Override
    protected void adaptResponse(HttpMessage message, UAVHttpMessage t) {

        String response = t.getResponseAsJsonString();

        message.putResponseBodyInString(response, 200, "utf-8");

        if (log.isDebugEnable()) {
            log.debug(this, "MDFListenServer Response: " + response);
        }

    }
}
