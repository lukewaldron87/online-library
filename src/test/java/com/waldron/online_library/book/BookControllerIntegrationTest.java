package com.waldron.online_library.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waldron.online_library.book.dto.BookDTO;
import com.waldron.online_library.book.dto.BookWithAiInsightDTO;
import com.waldron.online_library.book.dto.CreateBookDTO;
import com.waldron.online_library.book.dto.UpdateBookDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.beans.Transient;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    void createBook_shouldCreateBook_whenDtoIsValid() throws Exception {

        CreateBookDTO bookDTO = CreateBookDTO.builder()
                .title("Test Book")
                .author("Test Author")
                .isbn("9781234567890")
                .publicationYear(2024)
                .description("A test book description.")
                .build();

        mvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    void createBook_shouldThrowError_whenDtoIsNotValid() throws Exception {
        CreateBookDTO invalidDTO = CreateBookDTO.builder()
                .title("") // Invalid title
                .author("Test Author")
                .isbn("invalid-isbn")
                .publicationYear(2024)
                .description("A test book description.")
                .build();

        mvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getBooks_shouldReturnAllBooks_whenBooksExist() throws Exception {

        MvcResult result = mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<BookDTO> books = objectMapper.readValue(json, new TypeReference<>() {});

        assertThat(books).map(BookDTO::getTitle)
                .containsExactlyInAnyOrder(
                        "Dracula",
                        "The Grapes of Wrath",
                        "1984",
                        "The Spy Who Came in from the Cold",
                        "The Trial",
                        "Tinker Tailor Soldier Spy");
    }

    @Test
    void getBookForID_shouldReturnBook_whenExistingIdGiven() throws Exception {
        MvcResult result = mvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        BookDTO book = objectMapper.readValue(json, new TypeReference<>() {});

        assertThat(book)
                .usingRecursiveComparison()
                .isEqualTo(BookFactory.createDracula());
    }

    @Test
    void getBookForID_shouldReturn404_whenIdDoesNotExist() throws Exception {
        mvc.perform(get("/books/9999"))
                .andExpect(status().isNotFound());
    }

    //todo fix updateBookForId tests
    @Test
    @Transactional
    void updateBookForId_shouldUpdateBook_whenBookExists() throws Exception {

        UpdateBookDTO updateDTO = UpdateBookDTO.builder()
                .title("Updated Title")
                .author("Updated Author")
                .build();

        MvcResult result = mvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        BookDTO book = objectMapper.readValue(json, new TypeReference<>() {});

        assertThat(book.getTitle()).isEqualTo(updateDTO.getTitle());
        assertThat(book.getAuthor()).isEqualTo(updateDTO.getAuthor());
    }

    @Test
    void updateBookForId_shouldReturn404_whenIdDoesNotExist() throws Exception {
        UpdateBookDTO updateDTO = UpdateBookDTO.builder()
                .title("Non-existent Book")
                .build();

        mvc.perform(put("/books/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateBookForId_shouldThrowError_whenDtoIsNotValid() throws Exception {
        UpdateBookDTO invalidDTO = UpdateBookDTO.builder()
                .title("")
                .build();

        mvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteBookForId_shouldDeleteBook_whenBookExists() throws Exception {
        mvc.perform(delete("/books/1"))
                .andExpect(status().isOk());

        mvc.perform(get("/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteBookForId_return404_whenBookExists() throws Exception {
        mvc.perform(delete("/books/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getBooksForTitleOrAuthor_shouldReturnBooks_whenTitleExists() throws Exception {
        MvcResult result = mvc.perform(get("/books/search")
                        .param("title", "Dracula"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<BookDTO> books = objectMapper.readValue(json, new TypeReference<>() {});

        assertThat(books).map(BookDTO::getTitle)
                .containsExactlyInAnyOrder(
                        "Dracula");
    }

    @Test
    void getBooksForTitleOrAuthor_shouldReturnBooks_whenAuthorExists() throws Exception {
        MvcResult result = mvc.perform(get("/books/search")
                        .param("author", "John le Carré"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<BookDTO> books = objectMapper.readValue(json, new TypeReference<>() {});

        assertThat(books).map(BookDTO::getTitle)
                .containsExactlyInAnyOrder(
                        "The Spy Who Came in from the Cold",
                        "Tinker Tailor Soldier Spy");
    }

    // fix test works intermittently
    @Test
    void getBooksForTitleOrAuthor_shouldReturnBooks_whenTitleAndAuthorExist() throws Exception {
        MvcResult result = mvc.perform(get("/books/search")
                        .param("title", "Dracula")
                        .param("author", "John le Carré"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<BookDTO> books = objectMapper.readValue(json, new TypeReference<>() {});

        assertThat(books).map(BookDTO::getTitle)
                .containsExactlyInAnyOrder(
                        "Dracula",
                        "The Spy Who Came in from the Cold",
                        "Tinker Tailor Soldier Spy");
    }

    // todo fix test
    @Test
    void getBookAIInsightsForID_shouldReturnInsight_whenBookExists() throws Exception {
        MvcResult result = mvc.perform(get("/books/1/ai-insights"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        BookWithAiInsightDTO bookWithAiInsightDTO = objectMapper.readValue(json, new TypeReference<>() {});

        assertThat(bookWithAiInsightDTO.getInsight()).isNotBlank();
    }
}