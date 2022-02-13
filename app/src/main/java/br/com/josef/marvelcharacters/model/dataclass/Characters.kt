package br.com.josef.marvelcharacters.model.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Characters(
    private val available : Long,
    private val collectionURI: String,
    private val items: List<Item>,
    private val returned: Long? = null
):Parcelable



//class Characters {
//    @Expose
//    private val available: Long? = null
//
//    @Expose
//    private val collectionURI: String? = null
//
//    @Expose
//    private val items: List<Item>? = null
//
//    @Expose
//    private val returned: Long? = null
//}