package com.mzheng9.kotlin_fleamarket.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mzheng9.kotlin_fleamarket.R
import com.mzheng9.kotlin_fleamarket.database.Product
import com.mzheng9.kotlin_fleamarket.databinding.RecyclerviewItemBinding

var highlightedIndex: Int = -1

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var products: List<Product> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        RecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(products[position])


    override fun getItemCount() = products.size

    fun updateProducts(newProducts: List<Product>) {
        this.products = newProducts
        notifyDataSetChanged()
    }
    fun updateProductImageView(effectIntdex: Int) {
        notifyDataSetChanged()
    }
    fun updateBG(bgIndex: Int) {
        notifyDataSetChanged()
    }
    fun getProductAtPosition(position: Int): Product {
        return products[position]
    }

    class ProductViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var product: Product
        private val productTextView: TextView = itemView.findViewById(R.id.item_textView)
        private val productNameTextView: TextView = itemView.findViewById(R.id.product_name_textview)
        private val artistTextView: TextView = itemView.findViewById(R.id.artist_textview)
        private val releasedTextView: TextView = itemView.findViewById(R.id.released_textview)
        private val commentTextView: TextView = itemView.findViewById(R.id.comment_textview)
        private val productCoverImageView: ImageView = itemView.findViewById(R.id.product_imageview)
//        private val backgroundView: TextView = itemView.findViewById(R.id.yourproduct_textview) //not sure what type is it

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(product: Product) {
            this.product = product
            productNameTextView.text = productTextView.context.getString(R.string.details_product_name, product.productName)
            artistTextView.text = artistTextView.context.getString(R.string.details_artist,product.productPrice)
            releasedTextView.text = releasedTextView.context.getString(R.string.details_released,product.productDate)

                commentTextView.text = commentTextView.context.getString(R.string.details_comment,product.productDescription)


            when(product.productImgLink){
                ""->loadUrl("https://coverartarchive.org/release-group/1237b040-fb8f-4f23-8000-fb6909486c83/front.jpg")
                else->loadUrl(product.productImgLink)
//                else->loadUrl(product.productLink)
            }
//            productTextView.text =
//                    "Artist: " + product.productArtist + " released: " + product.productRelease
        }

        override fun onClick(v: View?) {
            /*if one product is already highlighted*/
            if (highlightedIndex != -1) {
                /*if one product is already highlighted*/
                if (adapterPosition == highlightedIndex) {
                    highlightedIndex = -1
                    productTextView.setBackgroundResource(R.drawable.product_cell_background)
                } else {
                    productTextView.context?.toast(productTextView.context.getString(R.string.unselect_first))
                }
            }
            /*if no product is selected*/
            else {
                highlightedIndex = adapterPosition
                productTextView.setBackgroundResource(R.drawable.product_cell_selected_background)
            }
        }
        private fun loadUrl(request: String) {
//                picasso.load(request)
//                    .placeholder(R.drawable.placeholder_ari)
//                    .transform(effect)
//                    .into(binding?.productImageview)

        }


    }
}
