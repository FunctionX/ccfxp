package io.functionx.websocket;

import com.alibaba.fastjson.JSON;
import io.functionx.websocket.huobi.HuobiTopic;
import io.functionx.websocket.huobi.HuobiWebSocketClient;
import io.functionx.websocket.huobi.vo.HuobiMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;



@Slf4j
public class HuobiWebSocketTest implements IExchangeWebSocketService {


    private static HuobiWebSocketClient huobiClient;


    @Override
    public void onStart() {

        huobiClient = new HuobiWebSocketClient(this);

        huobiClient.onConnect();

    }


    @Override
    public void onSubTopics() {

        huobiClient.onSubChannl(HuobiTopic.MARKET_DEPTH,"ethusdt");
    }


    @Override
    public void onReceive(String msg) {

        HuobiMessage huobiMessage = JSON.parseObject(msg, HuobiMessage.class);

        Assert.assertNotNull(huobiMessage);
    }

    @Override
    public void online() {

        onSubTopics();

        // other

    }

    @Override
    public void offline() {

        //  step1

    }


    @Test
    public void test() {

        // start up
        onStart();

    }


}

