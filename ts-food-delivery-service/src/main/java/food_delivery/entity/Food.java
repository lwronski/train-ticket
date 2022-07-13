package food_delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food implements Serializable {

    private String foodName;
    private double price;
}