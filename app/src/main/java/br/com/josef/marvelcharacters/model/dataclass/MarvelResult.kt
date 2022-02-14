package br.com.josef.marvelcharacters.model.dataclass

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "table_favorites")
@Parcelize
data class MarvelResult(
    @PrimaryKey(autoGenerate = true)
    val sqlId: Long = 0L,
    val id: Int,
    val description: String?,
    val thumbnail: Thumbnail,
    val title: String?,
    val name: String?
): Parcelable