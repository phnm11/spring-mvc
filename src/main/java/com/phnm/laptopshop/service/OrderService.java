package com.phnm.laptopshop.service;

import com.phnm.laptopshop.domain.*;
import com.phnm.laptopshop.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(long id) {
        return orderRepository.findById(id);
    }

    public void deleteOrderById(long id) {
        Optional<Order> orderOptional = this.getOrderById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            List<OrderDetail> orderDetails = order.getOrderDetails();

            for (OrderDetail orderDetail : orderDetails) {
                orderDetailRepository.deleteById(orderDetail.getId());
            }
        }
        orderRepository.deleteById(id);
    }

    public void updateOrderStatus(Order order) {
        Optional<Order> orderOptional = this.getOrderById(order.getId());
        if (orderOptional.isPresent()) {
            Order currentOrder = orderOptional.get();
            currentOrder.setStatus(order.getStatus());
            orderRepository.save(order);
        }
    }

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findOrderByUser(user);
    }

    public void placeOrder(
            User user,
            HttpSession session,
            String receiverName,
            String receiverAddress,
            String receiverPhone) {

        // Get cart by user
        Cart cart = cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();

            if (cartDetails != null) {
                // create order
                Order order = new Order();
                order.setUser(user);
                order.setReceiverName(receiverName);
                order.setReceiverAddress(receiverAddress);
                order.setReceiverPhone(receiverPhone);
                order.setStatus("Chờ xử lý");

                double sum = 0;
                for(CartDetail cartDetail : cartDetails) {
                    sum += cartDetail.getPrice() * cartDetail.getQuantity();
                }

                order.setTotalPrice(sum);
                orderRepository.save(order);

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
