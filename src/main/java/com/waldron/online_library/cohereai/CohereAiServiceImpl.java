package com.waldron.online_library.cohereai;

import com.cohere.api.Cohere;
import com.cohere.api.requests.ChatRequest;
import com.cohere.api.requests.GenerateRequest;
import com.cohere.api.types.GenerateStreamEndResponse;
import com.cohere.api.types.NonStreamedChatResponse;
import com.cohere.api.types.SingleGenerationInStream;
import com.waldron.online_library.book.BookDTO;
import com.waldron.online_library.cohereai.exception.CohereException;
import com.waldron.online_library.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CohereAiServiceImpl implements CohereAiService {

    private final WebClient webClient;

    @Value("${cohere.api-key}")
    private String apiKey;

    private static final String COHERE_BOOK_PROMPT =
            "Create an engaging one-line summary for the book titled '%s' by %s. Description: %s";

    private static final String INSIGHT_GENERATION_ERROR =
            "Cohere did not generate an insight";

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

        GenerateRequest request = GenerateRequest.builder()
                .prompt(prompt)
                .maxTokens(50)
                .build();

        GenerateStreamEndResponse response = webClient.post()
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(
                                new NotFoundException("Could not connect to Cohere")
                        ))
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> Mono.error(
                                new CohereException("Error encountered using Cohere")
                        ))
                .bodyToMono(GenerateStreamEndResponse.class)
                .block();

        if(response == null){
            throw new CohereException(INSIGHT_GENERATION_ERROR);
        }

        return response.getGenerations().orElseThrow(() -> new CohereException(INSIGHT_GENERATION_ERROR)).stream()
                .map(SingleGenerationInStream::getText)
                .collect(Collectors.joining(" "))
                .trim();
    }
}
