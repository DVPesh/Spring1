package ru.peshekhonov.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.peshekhonov.model.Role;
import ru.peshekhonov.model.dto.RoleDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoleDtoMapper {

    RoleDto map(Role role);

    @Mapping(target = "visitors", ignore = true)
    Role map(RoleDto roleDto);
}
