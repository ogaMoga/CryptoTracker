import com.squareup.moshi.Json

data class Quote (
	@Json(name = "USD") val usd : USD
)