package com.example.factura.Model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var username: String? = null

    @Column(nullable = false, length = 200)
    var password: String? = null

    @Column(length = 50)
    var email: String? = null

    @Column(nullable = false)
    var locked: Boolean? = null
    var disabled: Boolean? = null
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    var roles: List<RoleEntity>? = null


}