package food_delivery.service;


import edu.fudan.common.util.Response;
import food_delivery.entity.FoodDeliveryOrder;
import food_delivery.repository.FoodDeliveryOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodDeliveryServiceImpl implements FoodDeliveryService {

    @Autowired
    FoodDeliveryOrderRepository foodDeliveryOrderRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(FoodDeliveryServiceImpl.class);

    @Override
    public Response createFoodDeliveryOrder(FoodDeliveryOrder fd, HttpHeaders headers) {
        String id = fd.getId();
        FoodDeliveryOrder t = foodDeliveryOrderRepository.findById(id).orElse(null);
        if (t != null) {
            LOGGER.error("[createFoodDeliveryOrder] Already exists id: {}", id);
            return new Response<>(0, "Already exists id", id);
        } else {
            FoodDeliveryOrder res = foodDeliveryOrderRepository.save(fd);
            return new Response<>(1, "Save success", res);
        }
    }

    @Override
    public Response deleteFoodDeliveryOrder(String id, HttpHeaders headers) {
        FoodDeliveryOrder t = foodDeliveryOrderRepository.findById(id).orElse(null);
        if (t == null) {
            LOGGER.error("[deleteFoodDeliveryOrder] No such food delivery order id: {}", id);
            return new Response<>(0, "No such food delivery order id", id);
        } else {
            foodDeliveryOrderRepository.deleteById(id);
            LOGGER.info("[deleteFoodDeliveryOrder] Delete success, food delivery order id: {}", id);
            return new Response<>(1, "Delete success", null);
        }
    }

    @Override
    public Response getFoodDeliveryOrderById(String id, HttpHeaders headers) {
        FoodDeliveryOrder t = foodDeliveryOrderRepository.findById(id).orElse(null);
        if (t == null) {
            LOGGER.error("[deleteFoodDeliveryOrder] No such food delivery order id: {}", id);
            return new Response<>(0, "No such food delivery order id", id);
        } else {
            LOGGER.info("[getFoodDeliveryOrderById] Get success, food delivery order id: {}", id);
            return new Response<>(1, "Get success", t);
        }
    }

    @Override
    public Response getAllFoodDeliveryOrders(HttpHeaders headers) {
        List<FoodDeliveryOrder> foodDeliveryOrders = foodDeliveryOrderRepository.findAll();
        if (foodDeliveryOrders == null) {
            LOGGER.error("[getAllFoodDeliveryOrders] Food delivery orders query error");
            return new Response<>(0, "food delivery orders query error", null);
        } else {
            LOGGER.info("[getAllFoodDeliveryOrders] Get all food delivery orders success");
            return new Response<>(1, "Get success", foodDeliveryOrders);
        }
    }

    @Override
    public Response getFoodDeliveryOrderByStoreId(String storeId, HttpHeaders headers) {
        List<FoodDeliveryOrder> foodDeliveryOrders = foodDeliveryOrderRepository.findByStationFoodStoreId(storeId);
        if (foodDeliveryOrders == null) {
            LOGGER.error("[getAllFoodDeliveryOrders] Food delivery orders query error");
            return new Response<>(0, "food delivery orders query error", storeId);
        } else {
            LOGGER.info("[getAllFoodDeliveryOrders] Get food delivery orders by storeId {} success", storeId);
            return new Response<>(1, "Get success", foodDeliveryOrders);
        }
    }

    @Override
    public Response updateTripId(String id, String tripId, HttpHeaders headers) {
        FoodDeliveryOrder t = foodDeliveryOrderRepository.findById(id).orElse(null);
        if (t == null) {
            LOGGER.error("[updateTripId] No such delivery order id: {}", id);
            return new Response<>(0, "No such delivery order id", id);
        } else {
            t.setTripId(tripId);
            foodDeliveryOrderRepository.save(t);
            LOGGER.info("[updateTripId] update tripId success. id:{}, tripId:{}", id, tripId);
            return new Response<>(1, "update tripId success", t);
        }
    }

    @Override
    public Response updateSeatNo(String id, int seatNo, HttpHeaders headers) {
        FoodDeliveryOrder t = foodDeliveryOrderRepository.findById(id).orElse(null);
        if (t == null) {
            LOGGER.error("[updateSeatNo] No such delivery order id: {}", id);
            return new Response<>(0, "No such delivery order id", id);
        } else {
            t.setSeatNo(seatNo);
            foodDeliveryOrderRepository.save(t);
            LOGGER.info("[updateSeatNo] update seatNo success. id:{}, seatNo:{}", id, seatNo);
            return new Response<>(1, "update seatNo success", t);
        }
    }

    @Override
    public Response updateDeliveryTime(String id, String deliveryTime, HttpHeaders headers) {
        FoodDeliveryOrder t = foodDeliveryOrderRepository.findById(id).orElse(null);
        if (t == null) {
            LOGGER.error("[updateDeliveryTime] No such delivery order id: {}", id);
            return new Response<>(0, "No such delivery order id", id);
        } else {
            t.setDeliveryTime(deliveryTime);
            foodDeliveryOrderRepository.save(t);
            LOGGER.info("[updateDeliveryTime] update deliveryTime success. id:{}, deliveryTime:{}", id, deliveryTime);
            return new Response<>(1, "update deliveryTime success", t);
        }
    }
}
