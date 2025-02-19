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
                .orElseThrow(() -> new NotFoundException(String.format("Could not find book with id %d",id)));
    }

    @Override
    public BookDTO updateBookForId(long id, @Valid UpdateBookDTO updateBookDTO) {
        //todo Description: Update the details of the book with the specified ID. Validate input and
        //return an error if the book does not exist.

        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Could not find book with id %d",id)));;
        // todo this accepts null. How to change to not allow null or blank?
        bookMapper.updateCustomerFromDto(updateBookDTO, existingBook);
        return bookMapper.toBookDTO(existingBook);
    }
}
