package com.waldron.online_library.book;

import com.waldron.online_library.book.dto.BookDTO;
import com.waldron.online_library.book.dto.BookWithAiInsightDTO;
import com.waldron.online_library.book.dto.CreateBookDTO;
import com.waldron.online_library.book.dto.UpdateBookDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface BookService {
    BookDTO createBook(CreateBookDTO createBookDTO);

    List<BookDTO> getBooks();

    BookDTO getBookForId(long id);

    BookDTO updateBookForId(long id, @Valid UpdateBookDTO updateBookDTO);

    void deleteBookForId(long id);

    List<BookDTO> getBooksForTitleOrAuthor(String title, String author);

    BookWithAiInsightDTO getBookAIInsightsForID(long id);
}
