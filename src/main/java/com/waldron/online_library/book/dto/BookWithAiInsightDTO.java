package com.waldron.online_library.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "DTO containing insights for hte given book")
public class BookWithAiInsightDTO {

    @Schema(description = "the book the insights are for")
    private BookDTO book;

    @Schema(description = "an AI generated insight on the book")
    private String insight;
}
