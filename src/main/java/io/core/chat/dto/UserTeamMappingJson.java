package io.core.chat.dto;

import lombok.Data;

@Data
public class UserTeamMappingJson {
    private long id;
    private long mappingId;
    private long teamId;
    private long userId;
    private long companyId;
    private int deleted;
}
