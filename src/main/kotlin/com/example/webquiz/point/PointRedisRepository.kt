package com.example.webquiz.point

import org.springframework.data.repository.CrudRepository

interface PointRedisRepository : CrudRepository<Point, String>
