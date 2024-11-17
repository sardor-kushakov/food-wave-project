package sarik.dev.foodwaveproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

// BaseResponse - API javoblarini standartlashtirish uchun generik model.
@JsonInclude(JsonInclude.Include.NON_NULL)
public record BaseResponse<T>(T result, ErrorData error, boolean success) {

    public BaseResponse(T result) { // Muvaffaqiyatli javob uchun konstruktor
        this(result, null, true);
    }

    public BaseResponse(ErrorData error) { // Xatolik javobi uchun konstruktor
        this(null, error, false);
    }
}
