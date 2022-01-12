package com.example.nilopartner

//primero se hace esta
data class Product( //clase para el modelo de datos de los producto
        var id: String? = null, //id
        var name: String? = null, //nombre
        var description: String? = null, //descripcion
        var imgUrl: String? = null, //url de img
        var quantity: Int = 0, // cantidad
        var price: Double = 0.0){ //precio

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
