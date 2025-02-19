package com.waldron.online_library.book;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "DTO for updating a book")
public class UpdateBookDTO {

    @Schema(description = "The title of the book", example = "Effective Java")
    private String title;

    @Schema(description = "The author of the book", example = "Joshua Bloch")
    private String author;

    @Schema(description = "The ISBN of the book", example = "978-0134685991")
    private String isbn;

    @Schema(description = "The publication year of the book", example = "2018")
    private int publicationYear;

    @Schema(description = "The description of the book", example = "A guide to best practices in Java")
    private String description;
}
