package pl.com.ezdev.loreatlas.modules.user.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import pl.com.ezdev.loreatlas.modules.user.User;
import pl.com.ezdev.loreatlas.modules.user.api.UserPostRequest;

@Mapper
public interface UserPostRequestMapper {

    UserPostRequestMapper INSTANCE = Mappers.getMapper(UserPostRequestMapper.class);

    User requestToEntity(UserPostRequest request);
    void updateEntityFromRequest(UserPostRequest request, @MappingTarget User user);
}
