package br.com.josef.marvelcharacters.model.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(

    val characters: Characters,

//    val collectedIssues: List<Any>,

//    val collections: List<Any>,

//    val creators: Creators,

    val dates: List<Date>,

    val description: String,

    val diamondCode: String,

    val digitalId: Long,

    val ean: String,

//    val events: Events,

    val format: String,

    val id: Int,

    val images: List<Image>,

    val isbn: String,

    val issn: String,

    val issueNumber: Long,

    val modified: String,

    val pageCount: Long,

//    val prices: List<Price>,

    val resourceURI: String,

//    val series: Series,

//    val stories: Stories,

//    val textObjects: List<TextObject>,

    val thumbnail: Thumbnail,

    val title: String,

    val upc: String,

//    val urls: List<Url>,

    val variantDescription: String,

//    val variants: List<Any>,

    val name: String
): Parcelable