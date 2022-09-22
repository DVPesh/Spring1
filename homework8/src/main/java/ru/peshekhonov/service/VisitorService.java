package ru.peshekhonov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.peshekhonov.model.Role;
import ru.peshekhonov.model.dto.VisitorDto;
import ru.peshekhonov.model.mapper.VisitorDtoMapper;
import ru.peshekhonov.repository.VisitorRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitorService {

    private final VisitorDtoMapper mapper;
    private final VisitorRepository visitorRepository;
    private final PasswordEncoder encoder;

    public List<VisitorDto> findAll() {
        return visitorRepository.findAll().stream().map(mapper::map).toList();
    }

    public Optional<VisitorDto> findById(Long id) {
        return visitorRepository.findById(id).map(mapper::map);
    }

    public VisitorDto save(VisitorDto visitorDto) {
        return mapper.map(visitorRepository.save(mapper.map(visitorDto, encoder)));
    }

    public void deleteById(Long id) {
        visitorRepository.deleteById(id);
    }

    @Transactional
    public User findByUsername(String username) {
        return visitorRepository.findByUsername(username)
                .map(v -> new User(
                        v.getUsername(),
                        v.getPassword(),
                        mapRolesToAuthorities(v.getRoles())
                )).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
