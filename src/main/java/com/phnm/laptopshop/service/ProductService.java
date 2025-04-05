package com.phnm.laptopshop.service;

import com.phnm.laptopshop.domain.Cart;
import com.phnm.laptopshop.domain.CartDetail;
import com.phnm.laptopshop.domain.Product;
import com.phnm.laptopshop.domain.User;
import com.phnm.laptopshop.repository.CartDetailRepository;
import com.phnm.laptopshop.repository.CartRepository;
import com.phnm.laptopshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(
            ProductRepository productRepository,
            CartRepository cartRepository,
            CartDetailRepository cartDetailRepository,
            UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product newProduct) {
        productRepository.save(newProduct);
    }

    public Product getProductById(long id) {
        return productRepository.findProductById(id);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public void addProductToCart(String email, long productId) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            Cart cart = cartRepository.findByUser(user);

            if (cart == null) {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(1);
                cart = cartRepository.save(newCart);
            }

            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();

                CartDetail cartDetail = new CartDetail();
                cartDetail.setProduct(product);
                cartDetail.setCart(cart);
                cartDetail.setQuantity(1);
                cartDetail.setPrice(product.getPrice());

                cartDetailRepository.save(cartDetail);
            }
        }
    }
}
