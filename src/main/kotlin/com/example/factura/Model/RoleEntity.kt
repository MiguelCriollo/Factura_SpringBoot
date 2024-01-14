package com.example.factura.Model

import jakarta.persistence.*

@Entity
@Table(name = "role")
class RoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var role: String? = null
    @Column(name = "user_id")
    var userId: Long? = null
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable=false, updatable=false)
    var user:UserEntity? = null
}