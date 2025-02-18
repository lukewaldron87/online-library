package com.waldron.online_library.book;

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
        // todo why is book not mapping?
        book = bookRepository.save(book);
        return bookMapper.toDTO(book);
    }

    @Override
    public List<BookDTO> getBooks(){
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .toList();
    }
}
