package br.com.josef.marvelcharacters.model.dataclass

data class Item(
    private val name: String,
    private val resourceURI: String,
    private val role: String,
    private val type: String
)
