package com.example.tacocloud.repositories;

import com.example.tacocloud.models.Order;

public interface IOrderRepository {

    Order save(Order order);
}
