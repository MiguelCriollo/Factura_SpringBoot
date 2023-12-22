package com.example.factura.Service

import com.example.factura.Mapper.ProductMapper
import com.example.factura.Model.Client
import com.example.factura.Model.Detail
import com.example.factura.Model.Invoice
import com.example.factura.Model.Product
import com.example.factura.Repository.ClientRepository
import com.example.factura.Repository.DetailRepository
import com.example.factura.Repository.InvoiceRepository
import com.example.factura.Repository.ProductRepository
import jakarta.transaction.Transactional
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
    @Transactional
    fun save(modelo: Detail): Detail{
        var productToUpdate=productRepository.findById(modelo.productId)
            ?:throw Exception("Id del Producto no existe")
        invoiceRepository.findById(modelo.invoiceId)
            ?:throw Exception("Id del Invoice no existe")


        //modelo.price=modelo.quantity* productToUpdate.price!!
        try{
            productToUpdate.apply {
                stock -= modelo.quantity;
            }
            val savedModel=detailRepository.save(modelo)

            var invoiceToCalculate=invoiceRepository.findById(modelo.invoiceId);
            var allDetailsList: List<Detail> =detailRepository.findByInvoiceId(modelo.invoiceId)
            var totalPrice:Double=0.0;
            allDetailsList.map{element: Detail ->
                totalPrice+=element.price*element.quantity;
            }

            invoiceToCalculate?.apply {
                total=totalPrice;
            }
            return savedModel
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }

    }

    fun update(modelo: Detail): Detail{
        var productToUpdate=productRepository.findById(modelo.productId)
            ?:throw Exception("Id del Producto no existe")
        invoiceRepository.findById(modelo.invoiceId)
            ?:throw Exception("Id del Invoice no existe")
        var oldQuantity= detailRepository.findById(modelo.id)?.quantity;
        try {

            detailRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")
            if (oldQuantity != null) {
                productToUpdate.apply {
                    stock += oldQuantity - modelo.quantity;
                }
            }
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
            var detailToDelete=detailRepository.findById(id)
                ?: throw Exception("ID no existe")
            var productToUpdate=productRepository.findById(detailToDelete.productId)

            productToUpdate?.apply {
                stock+=detailToDelete.quantity
            }

            detailRepository.deleteById(id!!)
            return "ID eliminado Correctamente!!!"
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}
