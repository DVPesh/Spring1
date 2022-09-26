package ru.peshekhonov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.peshekhonov.model.dto.RoleDto;
import ru.peshekhonov.model.mapper.RoleDtoMapper;
import ru.peshekhonov.repository.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleDtoMapper mapper;
    private final RoleRepository roleRepository;

    @Transactional
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream().map(mapper::map).toList();
    }

    @Transactional
    public List<RoleDto> findAllAndAddNull() {
        List<RoleDto> roles = roleRepository.findAll().stream().map(mapper::map).collect(Collectors.toList());
        roles.add(null);
        return roles;
    }

    public List<String> findAllNames() {
        List<String> roleNames = findAll().stream().map(r -> r.getName().substring(5)).collect(Collectors.toList());
        roleNames.add(0, null);
        return roleNames;
    }

    @Transactional
    public Optional<RoleDto> findById(Long id) {
        return roleRepository.findById(id).map(mapper::map);
    }

    @Transactional
    public RoleDto save(RoleDto roleDto) {
        return mapper.map(roleRepository.save(mapper.map(roleDto)));
    }

    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
