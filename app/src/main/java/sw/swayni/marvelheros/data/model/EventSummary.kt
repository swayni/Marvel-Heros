package sw.swayni.marvelheros.data.model

import com.google.gson.annotations.SerializedName

data class EventSummary(
    @SerializedName("resourceURI") val resourceURI : String,
    @SerializedName("name") val name : String
)
