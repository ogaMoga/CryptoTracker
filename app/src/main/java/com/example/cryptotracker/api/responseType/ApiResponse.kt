import com.squareup.moshi.Json

data class ApiResponse (
	@Json(name = "data") val data : List<Data>
)