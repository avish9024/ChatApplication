package io.core.chat.service.serviceImpl;

import io.core.chat.components.MessageSender;
import io.core.chat.configuration.ApplicationConfigReader;
import io.core.chat.constants.Constants;
import io.core.chat.controller.UserController;
import io.core.chat.dto.DirectMessageJson;
import io.core.chat.response.ResponseCodeJson;
import io.core.chat.response.UniversalResponse;
import io.core.chat.service.DirectMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectMessageServiceImpl implements DirectMessageService, Constants {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final RabbitTemplate rabbitTemplate;
    private ApplicationConfigReader applicationConfig;
    private MessageSender messageSender;

    public ApplicationConfigReader getApplicationConfig() {
        return applicationConfig;
    }

    @Autowired
    public void setApplicationConfig(ApplicationConfigReader applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Autowired
    public DirectMessageServiceImpl(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public MessageSender getMessageSender() {
        return messageSender;
    }

    @Autowired
    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public ResponseCodeJson sendMessage(DirectMessageJson directMessageJson) {
        String exchange = getApplicationConfig().getApp2Exchange();
        String routingKey = getApplicationConfig().getApp2RoutingKey();
        /* Sending to Message Queue */
        try {
            messageSender.sendMessage(rabbitTemplate, exchange, routingKey, directMessageJson);
            return new ResponseCodeJson("message sent successfully", 200);

        } catch (Exception ex) {
            log.error("Exception occurred while sending message to the queue. Exception= {}", ex);
            return new ResponseCodeJson(Constants.MESSAGE_QUEUE_SEND_ERROR, 500);
        }
    }

    @Override
    public UniversalResponse updateMessage(DirectMessageJson directMessageJson) {
        return null;
    }
}
