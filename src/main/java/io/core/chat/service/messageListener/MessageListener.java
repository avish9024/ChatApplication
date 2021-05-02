package io.core.chat.service.messageListener;

import io.core.chat.configuration.ApplicationConfigReader;
import io.core.chat.constants.Constants;
import io.core.chat.dto.DirectMessageJson;
import io.core.chat.dto.UserTeamMessageJson;
import io.core.chat.model.DirectMessage;
import io.core.chat.model.UserTeamMessage;
import io.core.chat.repository.DirectMessageRepository;
import io.core.chat.repository.UserTeamMessageRepository;
import io.core.chat.utils.AtomicIdCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

@Service
public class MessageListener implements Constants {
    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    ApplicationConfigReader applicationConfigReader;
    @Autowired
    UserTeamMessageRepository utmp;
    @Autowired
    DirectMessageRepository dmp;

    /**
     * Message listener for app1
     * a user defined object used for deserialization of message
     */
    @RabbitListener(queues = "${app1.queue.name}")
    public void receiveFroupMessage(final UserTeamMessageJson data) {
        log.info("Received group message: {} from app1 queue.", data);
        try {
            UserTeamMessage userTeamMessage = new UserTeamMessage();
            userTeamMessage.setCompanyId(data.getCompanyId());
            userTeamMessage.setDeleted(NonDeleted);
            userTeamMessage.setMessage(data.getMessage());
            userTeamMessage.setMessageType(data.getMessageType());
            userTeamMessage.setMessageId(AtomicIdCounter.getUniqueID());
            userTeamMessage.setTeamId(data.getTeamId());
            userTeamMessage.setUserId(data.getUserId());
            utmp.save(userTeamMessage);
            log.info("Saved group message: {} from app1 queue.", userTeamMessage);

        } catch(HttpClientErrorException ex) {

            if(ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.info("Delay...");
                try {
                    Thread.sleep(Constants.MESSAGE_RETRY_DELAY);
                } catch (InterruptedException e) { }

                log.info("Throwing exception so that message will be requed in the queue.");
                // Note: Typically Application specific exception should be thrown below
                throw new RuntimeException();
            } else {
                throw new AmqpRejectAndDontRequeueException(ex);
            }

        } catch(Exception e) {
            log.error("Internal server error occurred in API call. Bypassing message requeue {}", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }

    }


    /**
     * Message listener for app2
     *
     */

    @RabbitListener(queues = "${app2.queue.name}")
    public void receiveDirectMessage(DirectMessageJson directMessageJson) {
        log.info("Received message: {} from app2 queue.", directMessageJson);

        try {
            DirectMessage directMessage = new DirectMessage();
            directMessage.setCompanyId(directMessageJson.getCompanyId());
            directMessage.setDeleted(NonDeleted);
            directMessage.setMappingId(directMessageJson.getMappingId());
            directMessage.setMessage(directMessageJson.getMessage());
            directMessage.setMessageById(directMessageJson.getMessageById());
            directMessage.setMessageId(AtomicIdCounter.getUniqueID());
            directMessage.setMessageType(directMessageJson.getMessageType());
            directMessage.setSentDate(new Date(System.currentTimeMillis()));
            dmp.save(directMessage);
            log.info("Saving direct message: {} from app2 queue.", directMessage);
        } catch(HttpClientErrorException  ex) {

            if(ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.info("Delay...");
                try {
                    Thread.sleep(Constants.MESSAGE_RETRY_DELAY);
                } catch (InterruptedException e) { }

                log.info("Throwing exception so that message will be requed in the queue.");
                // Note: Typically Application specific exception can be thrown below
                throw new RuntimeException();
            } else {
                throw new AmqpRejectAndDontRequeueException(ex);
            }

        } catch(Exception e) {
            log.error("Internal server error occurred in python server. Bypassing message requeue {}", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }

    }
}
