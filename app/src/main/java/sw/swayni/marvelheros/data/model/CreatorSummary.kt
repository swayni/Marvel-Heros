package sw.swayni.marvelheros.data.model

import com.google.gson.annotations.SerializedName

data class CreatorSummary(
    @SerializedName("resourceURI") val resourceURI : String,
    @SerializedName("name") val name : String,
    @SerializedName("role") val role : String
)
