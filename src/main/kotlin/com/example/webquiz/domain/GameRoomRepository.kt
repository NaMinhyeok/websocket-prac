package com.example.webquiz.domain

import org.springframework.data.repository.CrudRepository

interface GameRoomRepository : CrudRepository<GameRoom, String>
