package com.youctagh.purchasemanager.frontend.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ResponseError {
    private Date timestamp;
    private String message;
    private String details;
}
