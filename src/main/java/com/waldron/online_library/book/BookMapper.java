package com.waldron.online_library.book;

import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    Book toEntity(CreateBookDTO createBookDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromDto(UpdateBookDTO dto, @MappingTarget Book entity);

    BookDTO toBookDTO(Book book);
}
