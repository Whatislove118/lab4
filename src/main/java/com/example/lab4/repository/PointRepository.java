package com.example.lab4.repository;

import com.example.lab4.entity.Point;
import com.example.lab4.entity.SimplePoint;
import com.example.lab4.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PointRepository extends CrudRepository<Point,Long> {
    Collection<Point> findByUser(User user);
}
