package io.core.chat.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserTeamMessageJson {
    private long id;
    private long messageId;
    private String messageType;
    private String message;
    private long teamId;
    private long userId;
    private long companyId;
    private int deleted;
}
