package com.waldron.online_library.cohereai;

import com.waldron.online_library.book.dto.BookDTO;

public interface CohereAiService {
    String getInsightForBook(BookDTO bookDTO);

    String getInsightForBookViaHttp(BookDTO bookDTO);
}
