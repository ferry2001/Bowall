package com.ferry.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.reggie.dto.DishDto;
import com.ferry.reggie.entity.Dish;

public interface DishService extends IService<Dish> {

    public void saveWithFalvor(DishDto dishDto);

    public DishDto getByWithFlavor(Long id);

    public void updateWithFalvor(DishDto dishDto);
}
