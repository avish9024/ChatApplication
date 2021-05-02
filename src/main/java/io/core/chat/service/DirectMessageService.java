package io.core.chat.service;

import io.core.chat.dto.DirectMessageJson;
import io.core.chat.response.ResponseCodeJson;
import io.core.chat.response.UniversalResponse;

public interface DirectMessageService {
    ResponseCodeJson sendMessage(DirectMessageJson directMessageJson);

    UniversalResponse updateMessage(DirectMessageJson directMessageJson);
}
