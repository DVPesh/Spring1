package ru.peshekhonov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.peshekhonov.model.dto.ItemDto;
import ru.peshekhonov.model.mapper.ItemDtoMapper;
import ru.peshekhonov.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemDtoMapper mapper;
    private final ItemRepository itemRepository;

    @Transactional
    public List<ItemDto> findAll() {
        return itemRepository.findAll().stream().map(mapper::map).toList();
    }

    @Transactional
    public Optional<ItemDto> findById(Long id) {
        return itemRepository.findById(id).map(mapper::map);
    }

    @Transactional
    public ItemDto save(ItemDto itemDto) {
        return mapper.map(itemRepository.save(mapper.map(itemDto)));
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
