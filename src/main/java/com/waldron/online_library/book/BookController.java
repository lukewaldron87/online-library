package com.waldron.online_library.book;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    //todo add swagger notation
    //todo add tests

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody @Valid CreateBookDTO createBookDTO){
        return bookService.createBook(createBookDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBooks(){
        //todo add pagination/sorting
        return bookService.getBooks();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookForID(@PathVariable("id") @Parameter(example = "1") long id){
        return bookService.getBookForId(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO updateBookForId (
            @PathVariable("id") @Parameter(example = "1") long id,
            @RequestBody @Valid UpdateBookDTO updateBookDTO){
        return bookService.updateBookForId(id, updateBookDTO);
    }

    //todo• Delete a Book
    //◦ Endpoint: DELETE /books/{id}
    //◦ Description: Remove the book with the given ID from the database.

    //todo  • Search for Books
    //◦ Endpoint: GET /books/search
    //◦ Description: Allow searching by title and/or author using query parameters.
    //◦ Examples:
    //todo  ▪ GET /books/search?title=Spring
    //▪ GET /books/search?author=Smith
    //▪ GET /books/search?title=Spring&author=Smith

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
}
