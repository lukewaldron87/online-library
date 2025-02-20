package com.waldron.online_library.book;

import com.waldron.online_library.cohereai.CohereAiService;
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

    private final CohereAiService cohereAiService;

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

    //todo• AI-Powered Book Insights
    //◦ Endpoint: GET /books/{id}/ai-insights
    //◦ Description:
    //            ▪ Retrieve the specified book using its ID.
    //▪ Build a prompt using the book’s description (and optionally its title and
    //            author).
    //            ▪ Integrate with an external AI service (e.g., OpenAI) by making an HTTP call
    //    to generate a short, engaging tagline or summary.
    //            ▪ Return the AI-generated insights along with the book’s details.
    //            ◦ Notes:
    //            ▪ Externalize API keys and endpoints in your configuration (e.g., using
    //            application.properties or application.yml).
    //            ▪ Use Spring’s RestTemplate or WebClient for the HTTP call.
    //▪ Gracefully handle errors (e.g., API timeouts or failures) with appropriate
    //    HTTP statuses and error messages.
    @Override
    public BookWithAiInsightDTO getBookAIInsightsForID(long id) {
        Book book = getBookEntityForId(id);
        BookDTO bookDTO = bookMapper.toBookDTO(book);

        // todo: move to a generic aiService and make more generic
        String insights = cohereAiService.getInsightForBookViaHttp(bookDTO);
        return BookWithAiInsightDTO.builder()
                .book(bookDTO)
                .insight(insights)
                .build();
    }

    private Book getBookEntityForId(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(BOOK_NOT_FOUND_ERROR_MESSAGE, id)));
    }
}
