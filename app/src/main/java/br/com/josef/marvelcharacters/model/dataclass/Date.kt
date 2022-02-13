package br.com.josef.marvelcharacters.model.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Date(
    private val date: String,
    private val type: String
): Parcelable