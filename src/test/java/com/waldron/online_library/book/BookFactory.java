package com.waldron.online_library.book;

import com.waldron.online_library.book.dto.BookDTO;

import java.util.Arrays;
import java.util.List;

public class BookFactory {

    public static BookDTO createDracula() {
        return BookDTO.builder()
                .id(1L)
                .title("Dracula")
                .author("Bram Stoker")
                .isbn("9780141439846")
                .publicationYear(1897)
                .description("A gothic horror novel introducing the iconic Count Dracula.")
                .build();
    }

    public static BookDTO createGrapesOfWrath() {
        return BookDTO.builder()
                .id(2L)
                .title("The Grapes of Wrath")
                .author("John Steinbeck")
                .isbn("9780143039433")
                .publicationYear(1939)
                .description("A classic novel depicting the struggles of a poor family during the Great Depression.")
                .build();
    }

    public static BookDTO createNineteenEightyFour() {
        return BookDTO.builder()
                .id(3L)
                .title("1984")
                .author("George Orwell")
                .isbn("9780451524935")
                .publicationYear(1949)
                .description("A dystopian novel about a totalitarian regime and the dangers of surveillance.")
                .build();
    }

    public static BookDTO createSpyWhoCameInFromTheCold() {
        return BookDTO.builder()
                .id(4L)
                .title("The Spy Who Came in from the Cold")
                .author("John le Carré")
                .isbn("9780143124757")
                .publicationYear(1963)
                .description("A gripping Cold War spy thriller exploring espionage and betrayal.")
                .build();
    }

    public static BookDTO createTheTrial() {
        return BookDTO.builder()
                .id(5L)
                .title("The Trial")
                .author("Franz Kafka")
                .isbn("9780805209990")
                .publicationYear(1925)
                .description("A novel about a man arrested and prosecuted by an inaccessible authority without knowing his crime.")
                .build();
    }

    public static BookDTO createTinkerTailorSoldierSpy() {
        return BookDTO.builder()
                .id(6L)
                .title("Tinker Tailor Soldier Spy")
                .author("John le Carré")
                .isbn("9780143119784")
                .publicationYear(1963)
                .description("A gripping Cold War spy thriller exploring espionage and betrayal.")
                .build();
    }

    public static List<BookDTO> createListOfAllBooks() {
        return List.of(
                createDracula(),
                createGrapesOfWrath(),
                createNineteenEightyFour(),
                createSpyWhoCameInFromTheCold(),
                createTheTrial(),
                createTinkerTailorSoldierSpy());
    }
}

