package com.waldron.online_library.book;

import jakarta.validation.Valid;

import java.util.List;

public interface BookService {
    BookDTO createBook(CreateBookDTO createBookDTO);

    List<BookDTO> getBooks();

    BookDTO getBookForId(long id);

    BookDTO updateBookForId(long id, @Valid UpdateBookDTO updateBookDTO);

    void deleteBookForId(long id);

    List<BookDTO> getBooksForTitleOrAuthor(String title, String author);
}
