package io.core.chat.service;

import io.core.chat.dto.UserJson;
import io.core.chat.response.ResponseCodeJson;
import io.core.chat.response.UniversalResponse;

public interface UserService {
    UniversalResponse addUser(UserJson userJson);

    ResponseCodeJson updateUser(UserJson userJson);

    UniversalResponse getUserDetail(long userId, long companyId);
}
