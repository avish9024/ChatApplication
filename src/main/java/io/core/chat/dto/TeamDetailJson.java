package io.core.chat.dto;

import lombok.Data;
import java.util.Date;

@Data
public class TeamDetailJson {
    private long id;
    private long teamId;
    private String teamName;
    private String teamDescription;
    private String teamPhoto;
    private Date createdDate;
    private long createdById;
    private Date lastUpdatedDate;
    private long lastUpdatedById;
    private int deleted;
    private long companyId;
}
