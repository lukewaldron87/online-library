package com.waldron.online_library.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for books")
public class BookDTO {

    @Schema(description = "the id of the book")
    private long id;

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
