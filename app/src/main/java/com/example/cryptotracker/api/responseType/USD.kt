import com.squareup.moshi.Json

data class USD (
	@Json(name = "price") val price : Double,
	@Json(name = "percent_change_24h") val percent_change_24h : Double,
)