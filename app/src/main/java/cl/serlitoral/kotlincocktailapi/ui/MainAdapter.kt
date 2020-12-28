package cl.serlitoral.kotlincocktailapi.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.serlitoral.kotlincocktailapi.R
import cl.serlitoral.kotlincocktailapi.base.BaseViewHolder
import cl.serlitoral.kotlincocktailapi.data.model.Drink
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.drink_item.view.*

class MainAdapter(private val context: Context, private val drinkList: List<Drink>, private val itemClickListener: OnDrinkClickListener): RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnDrinkClickListener {
        fun onDrinkClick(drink: Drink, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.drink_item, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is MainViewHolder -> holder.bind(drinkList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<Drink>(itemView) {
        override fun bind(item: Drink, position: Int) {
            Glide.with(context)
                .load(item.image)
                .centerCrop()
                .into(itemView.img_drink)

            itemView.tv_drinkName.text = item.name
            itemView.tv_drinkDescription.text = item.description

            itemView.setOnClickListener { itemClickListener.onDrinkClick(item, position) }
        }
    }
}
