package co.istad.backend.mapper;

import co.istad.backend.domain.User;
import co.istad.backend.features.user.dto.ResponseUserDto;
import co.istad.backend.features.user.dto.UpdateUserDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    ResponseUserDto mapFromUserToUserResponseDto(User user);
    List<ResponseUserDto> mapFromListOfUserToListOfUserDto(List<User> userList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(@MappingTarget User user, UpdateUserDto userDto);

}
