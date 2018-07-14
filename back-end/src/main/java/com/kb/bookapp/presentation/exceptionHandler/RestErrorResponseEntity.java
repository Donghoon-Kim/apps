package com.kb.bookapp.presentation.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestErrorResponseEntity {
    private String status;
    private String message;
    private String code;
    private String moreInfo;
}