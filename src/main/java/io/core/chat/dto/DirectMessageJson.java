package io.core.chat.dto;

import lombok.Data;
import java.util.Date;

@Data
public class DirectMessageJson {
    private long id;
    private long messageId;
    private long mappingId;
    private String messageType;
    private String message;
    private long messageById;
    private long companyId;
    private Date sentDate;
    private int deleted;
}
