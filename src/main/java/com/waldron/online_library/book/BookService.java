package com.waldron.online_library.book;

import java.util.List;

public interface BookService {
    BookDTO createBook(CreateBookDTO createBookDTO);

    List<BookDTO> getBooks();
}
