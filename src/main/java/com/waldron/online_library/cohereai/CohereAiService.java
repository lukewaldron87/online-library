package com.waldron.online_library.cohereai;

import com.waldron.online_library.book.BookDTO;

public interface CohereAiService {
    String getInsightForBook(BookDTO bookDTO);

    String getInsightForBookViaHttp(BookDTO bookDTO);
}
