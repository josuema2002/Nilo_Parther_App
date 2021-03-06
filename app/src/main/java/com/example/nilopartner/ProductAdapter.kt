package com.example.nilopartner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.nilopartner.databinding.ItemProductBinding
//despues esta (2)
class ProductAdapter(private val productList: MutableList<Product>,
                     private val listener: OnProductListener) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() { //adaptador de producto

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //crear producto
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_product,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //encontrar producto
        val product = productList[position]

        holder.setListener(product)
        val layoutRoot= holder.binding //llama a la vista de layout

        layoutRoot.tvName.text = product.name //el text de tvName sera igual al de product.name
        layoutRoot.tvPrice.text = product.price.toString()//tiene toString por que es un double
        layoutRoot.tvQuantity.text = product.quantity.toString()

        Glide.with(context) //carga la img
            .load(product.imgUrl) //ubicacion de la img
            .diskCacheStrategy(DiskCacheStrategy.ALL) //guarda el cache de la img
            .placeholder(R.drawable.ic_access_time)
            .error(R.drawable.ic_broken_image)
            .centerCrop() //se visualiza bien
            .into(layoutRoot.imgProduct) //donde se va a ver

    }

    override fun getItemCount(): Int = productList.size //contar el numero de productos

    fun add(product: Product){//añadir y visualizar los elementos de forma local
        if (!productList.contains(product)){//si el listado actual no contiene el producto, lo agrega
            productList.add(product) //add es añadir
            notifyItemInserted(productList.size - 1)//se añade al final
        }else{ //si existe el producto lo actualiza
            update(product)
        }
    }

    fun update(product: Product){//actualiza el producto
        val index = productList.indexOf(product) // es el id
        if (index != -1){//se actualiza
            productList.set(index,product) //set es actualizar
            notifyItemChanged(index)//se añade al final
        }
    }

    fun delete(product: Product){//elimina el producto
        val index = productList.indexOf(product) // es el id
        if (index != -1){//se actualiza
            productList.removeAt(index)//removeAt es eliminar
            notifyItemRemoved(index)//se añade al final
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemProductBinding.bind(view)

        fun setListener(product: Product){ //cuando hay un click
            binding.root.setOnClickListener {
                listener.onClick(product)
            }

            binding.root.setOnLongClickListener { //cuando hay un click largo
                listener.onLongClick(product)
                true
            }
        }
    }

}