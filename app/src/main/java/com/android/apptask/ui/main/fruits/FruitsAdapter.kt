package com.android.apptask.ui.main.fruits

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.apptask.R
import com.android.apptask.databinding.ListitemFruitBinding
import com.android.apptask.utils.OnClick
import com.android.apptask.domain.models.Fruit

class FruitsAdapter(
    private val fruits: List<Fruit>,
    var onClick: OnClick<Fruit>? = null
) :
    RecyclerView.Adapter<FruitsAdapter.Companion.FruitHolder>() {

    companion object {
        class FruitHolder(val binding: ListitemFruitBinding) : RecyclerView.ViewHolder(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitHolder {
        val binding: ListitemFruitBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.listitem_fruit,
            parent,
            false
        )

        return FruitHolder(binding)
    }

    override fun onBindViewHolder(holder: FruitHolder, position: Int) {
        val fruit = fruits[position]
        val binding = holder.binding

        binding.title.text = fruit.type ?: ""
        binding.weight.text = fruit.weight.toString()
        binding.price.text = fruit.price.toString()

        binding.cardParent.setOnClickListener { onClick?.invoke(fruit, position) }

    }

    override fun getItemCount() = fruits.size

}