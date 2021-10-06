package com.example.demo.mapper

interface DTOMapper<D, M> {
    fun toModel(dto: D): M
    fun toDTO(model: M): D
}