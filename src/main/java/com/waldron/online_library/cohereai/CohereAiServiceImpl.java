package com.waldron.online_library.cohereai;

import com.cohere.api.Cohere;
import com.cohere.api.requests.ChatRequest;
import com.cohere.api.types.NonStreamedChatResponse;
import com.waldron.online_library.book.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class CohereAiServiceImpl implements CohereAiService {

    private final WebClient webClient;

    @Value("${cohere.api-key}")
    private String apiKey;

    private static final String COHERE_BOOK_PROMPT = "Create an engaging one-line summary for the book titled '%s' by %s. Description: %s";

    @Override
    public String getInsightForBook(BookDTO bookDTO) {

        String prompt = String.format(
                COHERE_BOOK_PROMPT,
                bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getDescription());

        Cohere cohere = Cohere.builder().token(apiKey).clientName("snippet").build();

        NonStreamedChatResponse response = cohere.chat(
                ChatRequest.builder()
                        .message(prompt)
                        .build());

        return response.getText();
    }


    @Override
    public String getInsightForBookViaHttp(BookDTO bookDTO){

        String prompt = String.format(
                COHERE_BOOK_PROMPT,
                bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getDescription());

        return webClient.post()
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue("{\"prompt\": \"" + prompt + "\", \"max_tokens\": 50}")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
