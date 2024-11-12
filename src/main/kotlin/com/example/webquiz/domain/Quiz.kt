package com.example.webquiz.domain

import jakarta.persistence.*

@Entity
class Quiz(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val question: String,

    @Column(nullable = false)
    val answer: Boolean,
)
