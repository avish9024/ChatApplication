package io.core.chat.controller;

import io.core.chat.components.CustomException;
import io.core.chat.components.MessageSender;
import io.core.chat.configuration.ApplicationConfigReader;
import io.core.chat.constants.Constants;
import io.core.chat.dto.DirectMessageJson;
import io.core.chat.dto.UserTeamMessageJson;
import io.core.chat.exception.ExceptionThrower;
import io.core.chat.model.UserTeamMessage;
import io.core.chat.response.ResponseCodeJson;
import io.core.chat.service.DirectMessageService;
import io.core.chat.service.UserTeamMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/message")
public class MessageController implements Constants {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserTeamMessageService utmp;
    @Autowired
    DirectMessageService dms;

    @PostMapping(value = "/group")
    public ResponseEntity<?> sendGroupMessage(@RequestBody UserTeamMessageJson userTeamMessageJson) throws CustomException {
        ExceptionThrower eT = new ExceptionThrower();
        ResponseCodeJson rc = utmp.sendMessage(userTeamMessageJson);
        if (rc.getCode() != 200) {
            eT.throwCustomException(rc.getCode(), rc.getMessage());
        }
        return new ResponseEntity<>(rc, HttpStatus.OK);

    }

    @PostMapping(value = "/direct")
    public ResponseEntity<?> sendDirectMessage(@RequestBody DirectMessageJson directMessageJson) throws CustomException {
        ExceptionThrower eT = new ExceptionThrower();
        ResponseCodeJson rc = dms.sendMessage(directMessageJson);
        if (rc.getCode() != 200) {
            eT.throwCustomException(rc.getCode(), rc.getMessage());
        }
        return null;

    }
}
