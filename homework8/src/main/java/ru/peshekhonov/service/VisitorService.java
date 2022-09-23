package ru.peshekhonov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Transactional
    public List<VisitorDto> findAll() {
        return visitorRepository.findAll().stream().map(mapper::map).toList();
    }

    @Transactional
    public Page<VisitorDto> findAllByFilter(String username, String role, int page, int size, String sortField) {
        username = username == null || username.isBlank() ? null : "%" + username.trim() + "%";
        role = role == null || role.isBlank() ? null : "ROLE_" + role.trim();
        return visitorRepository.visitorsByFilter(username, role, PageRequest.of(page, size, Sort.by(sortField)))
                .map(mapper::map);
    }

    @Transactional
    public Optional<VisitorDto> findById(Long id) {
        return visitorRepository.findById(id).map(mapper::map);
    }

    @Transactional
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
