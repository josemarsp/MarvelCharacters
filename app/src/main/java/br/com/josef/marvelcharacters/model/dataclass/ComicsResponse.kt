package br.com.josef.marvelcharacters.model.dataclass

data class ComicsResponse(
    var attributionHTML: String,
    var attributionText: String,
    var code: Long,
    var copyright: String,
    var data: Data,
    var etag: String,
    var status: String
)

//@Expose var attributionHTML: String,
//@Expose var attributionText: String,
//@Expose var code: Long,
//@Expose var copyright: String,
//@Expose var data: Data,
//@Expose var etag: String,
//@Expose var status: String