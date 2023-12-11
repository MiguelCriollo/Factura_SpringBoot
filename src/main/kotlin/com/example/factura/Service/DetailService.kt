package com.example.factura.Service

import com.example.factura.Model.Client
import com.example.factura.Model.Detail
import com.example.factura.Model.Invoice
import com.example.factura.Repository.ClientRepository
import com.example.factura.Repository.DetailRepository
import com.example.factura.Repository.InvoiceRepository
import com.example.factura.Repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DetailService {
    @Autowired
    lateinit var detailRepository: DetailRepository
    @Autowired
    lateinit var productRepository: ProductRepository
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Autowired
    lateinit var productService: ProductService;

    //-------------Para Queries--------------
    fun bestProductSeller(value:Long):List<*>{
        return detailRepository.bestProductSeller(value)
    }
    //---------------------------------------
    fun list ():List<Detail>{
        return detailRepository.findAll()
    }
    fun save(modelo: Detail): Detail{
        var productToUpdate=productRepository.findById(modelo.productId)
            ?:throw Exception("Id del Producto no existe")
        invoiceRepository.findById(modelo.invoiceId)
            ?:throw Exception("Id del Invoice no existe")
        try{
            productToUpdate.apply {
                stock -= modelo.quantity;
            }
            return detailRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update(modelo: Detail): Detail{
        try {

            detailRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")

            return detailRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(modelo:Detail): Detail{
        try{
            val response = detailRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")
            response.apply {
                price=modelo.price
            }
            return detailRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Detail?{
        return detailRepository.findById(id)
    }

    fun delete (id: Long?):String?{
        try{
            val response = detailRepository.findById(id)
                ?: throw Exception("ID no existe")
            detailRepository.deleteById(id!!)
            return "ID eliminado Correctamente!!!"
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}
