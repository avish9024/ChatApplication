package io.core.chat.service;

import io.core.chat.dto.UserTeamMessageJson;
import io.core.chat.response.ResponseCodeJson;
import io.core.chat.response.UniversalResponse;

public interface UserTeamMessageService {

    ResponseCodeJson sendMessage(UserTeamMessageJson userTeamMessageJson);

    UniversalResponse updateMessage(UserTeamMessageJson userTeamMessageJson);
}
