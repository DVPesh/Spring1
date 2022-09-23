package ru.peshekhonov.model.mapper;

import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.peshekhonov.model.Visitor;
import ru.peshekhonov.model.dto.VisitorDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface VisitorDtoMapper {

    @Mapping(target = "password", ignore = true)
    VisitorDto map(Visitor visitor);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "carts", ignore = true)
    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    Visitor map(VisitorDto visitorDto, @Context PasswordEncoder encoder);

    @Named("encode")
    default String encode(String password, @Context PasswordEncoder encoder) {
        return encoder.encode(password);
    }
}
