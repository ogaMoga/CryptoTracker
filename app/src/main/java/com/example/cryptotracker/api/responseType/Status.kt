import com.squareup.moshi.Json

data class Status (
	@Json(name = "timestamp") val timestamp : String,
	@Json(name = "error_code") val error_code : Int,
	@Json(name = "error_message") val error_message : String?,
	@Json(name = "elapsed") val elapsed : Int,
	@Json(name = "credit_count") val credit_count : Int,
	@Json(name = "notice") val notice : String?,
	@Json(name = "total_count") val total_count : Int
)