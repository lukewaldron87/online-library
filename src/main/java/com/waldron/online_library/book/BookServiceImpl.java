package com.waldron.online_library.book;

import com.waldron.online_library.exception.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    //todo add tests
    private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    private static final String BOOK_NOT_FOUND_ERROR_MESSAGE = "Could not find book with id %d";

    @Override
    public BookDTO createBook(CreateBookDTO createBookDTO) {
        Book book = bookMapper.toEntity(createBookDTO);
        book = bookRepository.save(book);
        return bookMapper.toBookDTO(book);
    }

    @Override
    public List<BookDTO> getBooks(){
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookDTO)
                .toList();
    }

    @Override
    public BookDTO getBookForId(long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toBookDTO)
                .orElseThrow(() -> new NotFoundException(String.format(BOOK_NOT_FOUND_ERROR_MESSAGE,id)));
    }

    @Override
    public BookDTO updateBookForId(long id, @Valid UpdateBookDTO updateBookDTO) {

        //todo Validate input
        Book existingBook = getBookEntityForId(id);
        bookMapper.updateCustomerFromDto(updateBookDTO, existingBook);
        bookRepository.save(existingBook);
        return bookMapper.toBookDTO(existingBook);
    }

    private Book getBookEntityForId(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(BOOK_NOT_FOUND_ERROR_MESSAGE, id)));
    }

    @Override
    public void deleteBookForId(long id) {
        getBookEntityForId(id);
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDTO> getBooksForTitleOrAuthor(String title, String author) {
        return bookRepository.findAllByTitleOrAuthor(title, author).stream()
                .map(bookMapper::toBookDTO)
                .toList();
    }
}
