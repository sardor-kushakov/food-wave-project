package sarik.dev.foodwaveproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

// ErrorData - Xatolik haqida ma'lumotni saqlash uchun model.
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorData(String message, Object... params) {
}
