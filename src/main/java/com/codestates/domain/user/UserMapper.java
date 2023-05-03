package com.codestates.domain.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userPostDtoToUser(UserPostDto userPostDto);

}
