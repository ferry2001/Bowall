package com.ferry.reggie.dto;


import com.ferry.reggie.entity.Setmeal;
import com.ferry.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
