package ru.peshekhonov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.peshekhonov.model.dto.CartDto;
import ru.peshekhonov.model.mapper.CartDtoMapper;
import ru.peshekhonov.repository.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartDtoMapper mapper;
    private final CartRepository cartRepository;

    @Transactional
    public List<CartDto> findAll() {
        return cartRepository.findAll().stream().map(mapper::map).toList();
    }

    @Transactional
    public Optional<CartDto> findById(Long id) {
        return cartRepository.findById(id).map(mapper::map);
    }

    @Transactional
    public CartDto save(CartDto cartDto) {
        return mapper.map(cartRepository.save(mapper.map(cartDto)));
    }

    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
}
