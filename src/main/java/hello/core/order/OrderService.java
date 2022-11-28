package hello.core.order;

public interface OrderService {
    Order createOrder(Long membeId, String itemName, int itemPrice);
}
