package br.com.josef.marvelcharacters.model.dataclass

data class BaseRequest(
    var attributionHTML: String,
    var attributionText: String,
    var code: Long,
    var copyright: String,
    var data: Data,
    var etag: String,
    var status: String
)