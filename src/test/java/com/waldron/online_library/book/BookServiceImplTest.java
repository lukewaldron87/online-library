package com.waldron.online_library.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void createBook_shouldReturnCreatedBook_whenBookSaved() {

        Instant now = Instant.now();

        CreateBookDTO createBookDTO = CreateBookDTO.builder()
                .title("title")
                .author("author")
                .isbn("isbn")
                .publicationYear(1994)
                .description("description")
                .build();

        bookService.createBook(createBookDTO);

//        verify()
    }
}