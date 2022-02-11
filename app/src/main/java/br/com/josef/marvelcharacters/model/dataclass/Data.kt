package br.com.josef.marvelcharacters.model.dataclass

data class Data(
    val count: Long,

    val limit: Long,

    val offset: Long,

    val results: List<Result>,

    val total: Long
)

