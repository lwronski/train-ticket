package food_delivery.service;

import edu.fudan.common.util.Response;
import food_delivery.entity.FoodDeliveryOrder;
import org.springframework.http.HttpHeaders;

public interface FoodDeliveryService {

    Response createFoodDeliveryOrder(FoodDeliveryOrder fd, HttpHeaders headers);

    Response deleteFoodDeliveryOrder(String id, HttpHeaders headers);

    Response getFoodDeliveryOrderById(String id, HttpHeaders headers);

    Response getAllFoodDeliveryOrders(HttpHeaders headers);

    Response getFoodDeliveryOrderByStoreId(String storeId, HttpHeaders headers);

    Response updateTripId(String id, String tripId, HttpHeaders headers);

    Response updateSeatNo(String id, int seatNo, HttpHeaders headers);

    Response updateDeliveryTime(String id, String deliveryTime, HttpHeaders headers);
}
