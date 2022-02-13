package br.com.josef.marvelcharacters.model.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    private val name: String,
    private val resourceURI: String,
    private val role: String,
    private val type: String
): Parcelable
