package com.waldron.online_library.book;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "DTO for creating books")
public class CreateBookDTO {

    @Schema(description = "the title of the book")
    private String title;

    @Schema(description = "the author of the book")
    private String author;

    @Schema(description = "the isbn of the book")
    private String isbn;

    @Schema(description = "the publicationYear of the book")
    private int publicationYear;

    @Schema(description = "the description of the book")
    private String description;
}
