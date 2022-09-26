package ru.peshekhonov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.peshekhonov.exceptions.EntityNotFoundException;
import ru.peshekhonov.model.dto.CartDto;
import ru.peshekhonov.model.dto.ItemDto;
import ru.peshekhonov.model.dto.ProductDto;
import ru.peshekhonov.model.dto.VisitorDto;
import ru.peshekhonov.model.mapper.CartDtoMapper;
import ru.peshekhonov.repository.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartDtoMapper mapper;
    private final CartRepository cartRepository;
    private final ItemService itemService;
    private final ProductService productService;

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

    public void putProductToCart(Long productId, long quantity, VisitorDto visitor) {
        CartDto cart = null;
        if (visitor.getCarts().size() == 0) {
            cart = save(new CartDto(visitor));
        } else {
            cart = mapper.map(visitor.getCarts().get(0));
        }
        ProductDto product = productService.findProductById(productId)
                .orElseThrow(() -> new EntityNotFoundException("нет продукта с id = " + productId));
        itemService.save(new ItemDto(product, quantity, cart, product.getCost()));
    }
}
