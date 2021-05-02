package io.core.chat.dto;

import lombok.Data;
import java.util.Date;

@Data
public class DirectMessageMappingJson {
    private long id;
    private long mappingId;
    private long userId1;
    private long userId2;
    private long companyId;
    private int deleted;
    private Date createdDate;
}
