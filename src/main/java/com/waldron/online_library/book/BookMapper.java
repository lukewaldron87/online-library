package com.waldron.online_library.book;

import com.waldron.online_library.book.dto.BookDTO;
import com.waldron.online_library.book.dto.CreateBookDTO;
import com.waldron.online_library.book.dto.UpdateBookDTO;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    Book toEntity(CreateBookDTO createBookDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromDto(UpdateBookDTO dto, @MappingTarget Book entity);

    BookDTO toBookDTO(Book book);
}
