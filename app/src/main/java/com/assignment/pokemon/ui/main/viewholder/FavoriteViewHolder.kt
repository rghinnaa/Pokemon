package com.assignment.pokemon.ui.main.viewholder

import android.graphics.Bitmap
import android.graphics.Color
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.assignment.pokemon.R
import com.assignment.pokemon.data.local.entity.PokemonFavorite
import com.assignment.pokemon.databinding.ItemPokemonBinding
import com.assignment.pokemon.utils.capital
import com.assignment.pokemon.utils.colorStateList
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable

class FavoriteViewHolder(
    private val binding: ItemPokemonBinding
): RecyclerView.ViewHolder(binding.root) {
    val context = binding.root

    fun bind(item: PokemonFavorite) {
        binding.run {
            tvNumber.text =
                root.context.getString(R.string.hashtag, item.number.toString())
            tvName.text = item.name?.capital()

            cbFavorite.isChecked = true

            cgType.removeAllViews()
            val type = listOf(item.type1, item.type2)
            type.forEach {
                if(it != null) {
                    val chipDrawable =
                        ChipDrawable.createFromAttributes(
                            root.context,
                            null,
                            0,
                            R.style.Chip_Filter
                        )

                    val chip = Chip(root.context).apply {
                        setChipDrawable(chipDrawable)
                        text = it.capital()
                        chipBackgroundColor = root.context.colorStateList(R.color.white)

                        isClickable = false
                        isFocusable = false

                    }

                    cgType.addView(chip)
                }
            }

            Glide.with(root.context)
                .asBitmap()
                .load(item.image)
                .listener(object : RequestListener<Bitmap> {
                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (resource != null) {
                            Palette.Builder(resource).generate { builder ->
                                builder?.let { palette ->
                                    cvPokemon.setCardBackgroundColor(
                                        palette.getLightMutedColor(
                                            Color.GRAY
                                        )
                                    )
                                }
                            }
                        }

                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(ivPokemon)

            cbFavorite.setOnClickListener {
                if(!cbFavorite.isChecked) {
                    onClickFavorite.invoke(item, false)
                } else onClickFavorite.invoke(item, true)
            }
        }
    }

    var onClickFavorite: (PokemonFavorite, Boolean) -> Unit = { _, _ -> }

    fun setOnFavoriteListener(callback: (item: PokemonFavorite, active: Boolean) -> Unit) {
        onClickFavorite = callback
    }
}