package com.phnm.laptopshop.service;

import com.phnm.laptopshop.domain.Cart;
import com.phnm.laptopshop.domain.CartDetail;
import com.phnm.laptopshop.domain.Product;
import com.phnm.laptopshop.domain.User;
import com.phnm.laptopshop.repository.CartDetailRepository;
import com.phnm.laptopshop.repository.CartRepository;
import com.phnm.laptopshop.repository.ProductRepository;
import com.phnm.laptopshop.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(
            ProductRepository productRepository,
            UserRepository userRepository,
            CartRepository cartRepository,
            CartDetailRepository cartDetailRepository,
            UserService userService) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
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

    public void addProductToCart(String email, long productId, HttpSession session) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            Cart cart = cartRepository.findByUser(user);

            if (cart == null) {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(0);
                cart = cartRepository.save(newCart);
                user.setCart(cart); // Đồng bộ quan hệ ngược
                userRepository.save(user); // Lưu User để cập nhật
            }

            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                CartDetail existCartDetail = cartDetailRepository.findByCartAndProduct(cart, product);

                if (existCartDetail == null) {
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setProduct(product);
                    cartDetail.setCart(cart);
                    cartDetail.setQuantity(1);
                    cartDetail.setPrice(product.getPrice());
                    cartDetailRepository.save(cartDetail);

                    int sum = cart.getSum() + 1;
                    cart.setSum(sum);
                    cartRepository.save(cart);
                    session.setAttribute("sum", sum);
                } else {
                    existCartDetail.setQuantity(existCartDetail.getQuantity() + 1);
                    cartDetailRepository.save(existCartDetail);
                }
            }
        }
    }

    public Cart findCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public void deleteCartDetail(long id, HttpSession session) {
        Optional<CartDetail> cartDetailOptional = cartDetailRepository.findById(id);
        if (cartDetailOptional.isPresent()) {
            CartDetail cartDetail = cartDetailOptional.get();
            Cart currentCart = cartRepository.findById(cartDetail.getCart().getId())
                    .orElseThrow(() -> new RuntimeException("Cart not found"));

            cartDetailRepository.deleteById(id);

            User user = currentCart.getUser();
            if (currentCart.getSum() > 1) {
                int sum = currentCart.getSum() - 1;
                currentCart.setSum(sum);
                session.setAttribute("sum", sum);
                cartRepository.save(currentCart);
            } else {
                if (user != null) {
                    user.setCart(null); // Ngắt liên kết
                    userRepository.save(user); // Lưu User để cập nhật
                }
                cartRepository.delete(currentCart); // Xóa Cart
                session.setAttribute("sum", 0);
            }
        }
    }
}
