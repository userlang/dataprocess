package com.dataprocess.mq;

import com.dataprocess.core.factory.CoreStrategyFactory;
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

     private CoreStrategyFactory executeFactory= CoreStrategyFactory.getInstance();

    @Override
    public void onMessage(String message) {
        try {

            logger.info("本次操作的数据类型="+message);
           boolean exu=executeFactory.analysis(message);

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
