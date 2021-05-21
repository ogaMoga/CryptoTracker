import com.squareup.moshi.Json

data class Data (
	@Json(name = "id") val id : Int,
	@Json(name = "name") val name : String,
	@Json(name = "symbol") val symbol : String,
	@Json(name = "quote") val quote : Quote
)