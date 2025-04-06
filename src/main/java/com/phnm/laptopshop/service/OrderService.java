package com.phnm.laptopshop.service;

import com.phnm.laptopshop.domain.*;
import com.phnm.laptopshop.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartDetailRepository cartDetailRepository;

    public OrderService(
            UserRepository userRepository,
            CartRepository cartRepository,
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository,
            CartDetailRepository cartDetailRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.cartDetailRepository = cartDetailRepository;
    }

    public Order getOrderById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void placeOrder(
            User user,
            HttpSession session,
            String receiverName,
            String receiverAddress,
            String receiverPhone) {
        Order order = new Order();
        order.setUser(user);
        order.setReceiverName(receiverName);
        order.setReceiverAddress(receiverAddress);
        order.setReceiverPhone(receiverPhone);

        order = orderRepository.save(order);

        // Get cart by user
        Cart cart = cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();

            if (cartDetails != null) {
                for (CartDetail cartDetail : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cartDetail.getProduct());
                    orderDetail.setPrice(cartDetail.getPrice());
                    orderDetail.setQuantity(cartDetail.getQuantity());

                    orderDetailRepository.save(orderDetail);
                }

                // Delete cart details and cart after placing order
                for (CartDetail cartDetail : cartDetails) {
                    cartDetailRepository.deleteById(cartDetail.getId());
                }

                if (user.getCart() != null && user.getCart().getId() > 0) {
                    user.setCart(null);
                    userRepository.save(user);
                }

                // Tải lại Cart để đảm bảo trạng thái persistent
                Cart managedCart = cartRepository.findById(cart.getId())
                        .orElseThrow(() -> new RuntimeException("Cart not found"));
                cartRepository.delete(managedCart);

                // clear cart from session
                session.setAttribute("sum", 0);
            }
        }
    }
}
