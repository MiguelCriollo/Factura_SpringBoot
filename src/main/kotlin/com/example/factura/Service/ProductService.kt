package com.example.factura.Service

import com.example.factura.Mapper.ProductMapper
import com.example.factura.Model.Client
import com.example.factura.Model.Product
import com.example.factura.Repository.ProductRepository
import com.example.factura.dto.ProductDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    fun getAll(modelo: Product):List<Product>{
        return productRepository.findAll();
    }
    fun list(pageable: Pageable, model:Product): Page<Product> {
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher(("field"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return productRepository.findAll(Example.of(model, matcher), pageable)
    }

    fun listDt():List<ProductDto>{

        val productList = productRepository.findAll()

        val mutableList:MutableList<ProductDto> = mutableListOf()

        productList.forEach{product:Product ->
            mutableList.add(ProductMapper.mapToDto(product))
        }
        return mutableList;
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
    fun updateStock(modelo:Product): Product{
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
