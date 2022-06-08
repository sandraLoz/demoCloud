package es.opplus.cloud.democloud.mapper;

import org.mapstruct.Mapper;
import es.opplus.cloud.democloud.domain.UserEntity;
import es.opplus.cloud.democloud.dto.UserDto;


@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDto, UserEntity>{


}
