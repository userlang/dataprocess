package com.dataprocess.mq;

import com.dataprocess.core.factory.CoreExecuteFactory;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
@RocketMQMessageListener(topic = "${rocketmq.producer.topic}", consumerGroup = "test")
public class MqMessage implements RocketMQListener<String> {
    private final static Logger logger = LoggerFactory.getLogger(MqMessage.class);

     private CoreExecuteFactory executeFactory= CoreExecuteFactory.getInstance();

    @Override
    public void onMessage(String message) {
        try {
            /**
             * 本次操作的数据类型={"data":[{"id":"59","login_account":"wendy.wang","account_type":"2","region":null,"organize":"商务拓展部","login_time":"2021-08-30 18:26:00","login_state":"1","login_message":"登录成功","employee_id":"10121"}],"database":"bpm","es":1642499513000,"id":913,"isDdl":false,"mysqlType":{"id":"int(50)","login_account":"varchar(150)","account_type":"int(5)","region":"varchar(100)","organize":"varchar(100)","login_time":"datetime","login_state":"int(5)","login_message":"varchar(255)","employee_id":"int(50)"},"old":[{"account_type":"222"}],"pkNames":["id"],"sql":"","sqlType":{"id":4,"login_account":12,"account_type":4,"region":12,"organize":12,"login_time":93,"login_state":4,"login_message":12,"employee_id":4},"table":"login_info","ts":1642499513461,"type":"UPDATE"}
             */
            logger.info("本次操作的数据类型="+message);
           boolean exu=executeFactory.execute(message);

        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (InstantiationException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
