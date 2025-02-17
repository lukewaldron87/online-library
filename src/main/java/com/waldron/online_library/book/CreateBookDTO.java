package com.waldron.online_library.book;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CreateBookDTO {

    private String title;

    private String author;

    private String isbn;

    private int publicationYear;

    private String description;
}
