package sw.swayni.marvelheros.data.model

import com.google.gson.annotations.SerializedName

data class ComicList(
    @SerializedName("available") val available : Int,
    @SerializedName("returned") val returned : Int,
    @SerializedName("collectionURI") val collectionURI : String,
    @SerializedName("items") val items : List<ComicSummary>
)
