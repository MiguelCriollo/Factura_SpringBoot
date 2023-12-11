package com.example.factura.Mapper

import com.example.factura.Model.Product
import com.example.factura.dto.ProductDto

object ProductMapper {
    fun mapToDto(product:Product):ProductDto{
        return ProductDto(
            product.id,
            descriptionBrand="${product.descripction} ${product.brand}"
        )
    }
}





























