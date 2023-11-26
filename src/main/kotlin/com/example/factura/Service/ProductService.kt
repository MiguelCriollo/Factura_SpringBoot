package com.example.factura.Service

import com.example.factura.Model.Client
import com.example.factura.Model.Product
import com.example.factura.Repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    fun list ():List<Product>{
        return productRepository.findAll()
    }
    fun save(modelo: Product): Product{
        try{
            return productRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update(modelo: Product): Product{
        try {

            productRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")

            return productRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(modelo:Product): Product{
        try{
            val response = productRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")
            response.apply {
                stock=modelo.stock
            }
            return productRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Product?{
        return productRepository.findById(id)
    }

    fun delete (id: Long?):String?{
        try{
            val response = productRepository.findById(id)
                ?: throw Exception("ID no existe")
            productRepository.deleteById(id!!)
            return "ID eliminado Correctamente!!!"
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}
