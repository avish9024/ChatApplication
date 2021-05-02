package io.core.chat.service;

import io.core.chat.response.ResponseCodeJson;

public interface UserTeamMappingService {
    ResponseCodeJson addMemberInTeam(long teamId, long userId, long companyId);
}
