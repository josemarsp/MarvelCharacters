package br.com.josef.marvelcharacters.model.dataclass

data class Stories(
    private val available: Long,

    private val collectionURI: String,

    private val items: List<Item>,

    private val returned: Long
)
   