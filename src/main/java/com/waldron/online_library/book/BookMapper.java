package com.waldron.online_library.book;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

//    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toEntity(CreateBookDTO createBookDTO);
    Book toEntity(UpdateBookDTO updateBookDTO);
    void updateCustomerFromDto(UpdateBookDTO dto, @MappingTarget Book entity);
    BookDTO toBookDTO(Book book);
}
