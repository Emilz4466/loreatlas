package pl.com.ezdev.loreatlas.modules.user.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.com.ezdev.loreatlas.modules.user.domain.User;
import pl.com.ezdev.loreatlas.modules.user.api.response.UserResponse;

@Mapper
public interface UserResponseMapper {

    UserResponseMapper INSTANCE = Mappers.getMapper(UserResponseMapper.class);

    UserResponse entityToResponse(User user);
    User responseToEntity(UserResponse response);

}

