package com.kb.bookapp.business.component.kakaoApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KakaoDateTimeUtil {
    /**
     * @param dateTime SO 8601. [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
     * @return
     */
    public static LocalDateTime convertStringToLocalDateTime(String dateTime){
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
