package com.waldron.online_library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookDTO createBook(CreateBookDTO createBookDTO){
        return bookService.createBook(createBookDTO);
    }



//todo • Retrieve All Books
//◦ Endpoint: GET /books
//◦ Description: Return a list of all books. (Optional: add pagination/sorting)

//            • Retrieve a Single Book by ID
//◦ Endpoint: GET /books/{id}
//◦ Description: Return the book with the given ID, or respond with 404 if not found.

//todo  • Update an Existing Book
//◦ Endpoint: PUT /books/{id}
//◦ Description: Update the details of the book with the specified ID. Validate input and
//return an error if the book does not exist.

//• Delete a Book
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
