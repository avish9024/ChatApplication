package io.core.chat.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UniversalResponse<T> {
    private ResponseCodeJson responseCodeJson;
    private List list;
    private T object;
    private Integer reqid;
    private Map<?,?> map;
}

