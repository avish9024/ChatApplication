package io.core.chat.service.serviceImpl;

import io.core.chat.components.MessageSender;
import io.core.chat.configuration.ApplicationConfigReader;
import io.core.chat.constants.Constants;
import io.core.chat.controller.UserController;
import io.core.chat.dto.UserTeamMessageJson;
import io.core.chat.repository.UserTeamMessageRepository;
import io.core.chat.response.ResponseCodeJson;
import io.core.chat.response.UniversalResponse;
import io.core.chat.service.UserTeamMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserTeamMessageServiceImpl implements UserTeamMessageService, Constants {

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
    public UserTeamMessageServiceImpl(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public MessageSender getMessageSender() {
        return messageSender;
    }

    @Autowired
    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Autowired
    UserTeamMessageRepository utmp;

    @Override
    public ResponseCodeJson sendMessage(UserTeamMessageJson userTeamMessageJson) {
        String exchange = getApplicationConfig().getApp1Exchange();
        String routingKey = getApplicationConfig().getApp1RoutingKey();
        /* Sending to Message Queue */
        try {
            messageSender.sendMessage(rabbitTemplate, exchange, routingKey, userTeamMessageJson);
            return new ResponseCodeJson("message sent successfully", 200);

        } catch (Exception ex) {
            log.error("Exception occurred while sending message to the queue. Exception= {}", ex);
            return new ResponseCodeJson(Constants.MESSAGE_QUEUE_SEND_ERROR, 500);
        }
    }

    @Override
    public UniversalResponse updateMessage(UserTeamMessageJson userTeamMessageJson) {
        return null;
    }
}
