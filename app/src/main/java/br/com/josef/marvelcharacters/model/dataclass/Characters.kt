package br.com.josef.marvelcharacters.model.dataclass

import com.google.gson.annotations.Expose

data class Characters(
    @Expose private val available : Long,
    @Expose private val collectionURI: String,
    @Expose private val items: List<Item>,
    @Expose private val returned: Long? = null
)



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