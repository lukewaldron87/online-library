package com.waldron.online_library.book;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
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

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookForId(@PathVariable("id") @Parameter(example = "1") long id){
        bookService.deleteBookForId(id);
    }

    @GetMapping("search")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBooksForTitleOrAuthor(
            @RequestParam(name = "title", required = false) @Parameter(example = "Dracula") String title,
            @RequestParam(name = "author", required = false) @Parameter(example = "Bram Stoker") String author){
        return bookService.getBooksForTitleOrAuthor(title, author);
    }

    @GetMapping("{id}/ai-insights")
    @ResponseStatus(HttpStatus.OK)
    public BookWithAiInsightDTO getBookAIInsightsForID(@PathVariable("id") @Parameter(example = "1") long id){
        return bookService.getBookAIInsightsForID(id);
    }
}
