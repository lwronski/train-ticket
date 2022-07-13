package food_delivery.controller;

import com.alibaba.fastjson.JSONObject;
import edu.fudan.common.util.Response;
import food_delivery.entity.FoodDeliveryOrder;
import food_delivery.service.FoodDeliveryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(JUnit4.class)
public class FoodDeliveryControllerTest {

    @InjectMocks
    private FoodDeliveryController foodDeliveryController;

    @Mock
    private FoodDeliveryService foodDeliveryService;
    private MockMvc mockMvc;
    private Response response = new Response();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(foodDeliveryController).build();
    }

    @Test
    public void testHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fooddeliveryservice/welcome"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Welcome to [ food delivery service ] !"));
    }

    @Test
    public void testCreateFoodDeliveryOrder() throws Exception {
        FoodDeliveryOrder foodDeliveryOrder = new FoodDeliveryOrder();
        System.out.println(foodDeliveryOrder);
        Mockito.when(foodDeliveryService.createFoodDeliveryOrder(Mockito.any(FoodDeliveryOrder.class), Mockito.any(HttpHeaders.class))).thenReturn(response);
        String requestJson = JSONObject.toJSONString(foodDeliveryOrder);
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/fooddeliveryservice/orders").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(requestJson);
        System.out.println(result);
        Assert.assertEquals(response, JSONObject.parseObject(result, Response.class));
    }

//    @Test
//    public void testGetAllFoodStores() throws Exception {
//        Mockito.when(stationFoodService.listFoodStores(Mockito.any(HttpHeaders.class))).thenReturn(response);
//        String result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/foodmapservice/foodstores"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn().getResponse().getContentAsString();
//        Assert.assertEquals(response, JSONObject.parseObject(result, Response.class));
//    }
//
//    @Test
//    public void testGetFoodStoresOfStation() throws Exception {
//        Mockito.when(stationFoodService.listFoodStoresByStationId(Mockito.anyString(), Mockito.any(HttpHeaders.class))).thenReturn(response);
//        String result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/foodmapservice/foodstores/station_id"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn().getResponse().getContentAsString();
//        Assert.assertEquals(response, JSONObject.parseObject(result, Response.class));
//    }
//
//    @Test
//    public void testGetFoodStoresByStationIds() throws Exception {
//        List<String> stationIdList = new ArrayList<>();
//        Mockito.when(stationFoodService.getFoodStoresByStationIds(Mockito.anyList())).thenReturn(response);
//        String requestJson = JSONObject.toJSONString(stationIdList);
//        String result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/foodmapservice/foodstores").contentType(MediaType.APPLICATION_JSON).content(requestJson))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn().getResponse().getContentAsString();
//        Assert.assertEquals(response, JSONObject.parseObject(result, Response.class));
//    }

}
