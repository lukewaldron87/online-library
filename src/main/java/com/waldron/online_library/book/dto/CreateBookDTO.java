package com.waldron.online_library.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for creating books")
public class CreateBookDTO {

    @NotBlank(message = "Title is required")
    @Schema(description = "The title of the book", example = "Effective Java")
    private String title;

    @NotBlank(message = "Author is required")
    @Schema(description = "The author of the book", example = "Joshua Bloch")
    private String author;

    @NotBlank(message = "ISBN is required")
    @Schema(description = "The ISBN of the book", example = "978-0134685991")
    private String isbn;

    @Min(value = 1, message = "Publication year must be a positive number")
    @Schema(description = "The publication year of the book", example = "2018")
    private int publicationYear;

    @NotBlank(message = "Description is required")
    @Schema(description = "The description of the book", example = "A guide to best practices in Java")
    private String description;
}
