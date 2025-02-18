package com.waldron.online_library.book;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

//    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toEntity(CreateBookDTO createBookDTO);
    BookDTO toDTO(Book book);
}
