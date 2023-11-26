package com.example.factura.Model

import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null;
    var description: String? = null;
    var brand: String? = null;
    var price: Double? = null;
    var stock: Int? = null;
}