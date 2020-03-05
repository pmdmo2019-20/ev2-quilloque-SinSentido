package es.iessaladillo.pedrojoya.quilloque.pojo

data class LLamadaContacto(
    val idLLamada: Int,
    val tipo: String,
    val numeroLLamada: String,
    val fecha: String,
    val hora: String,
    val idContacto: Int?,
    val nombre: String?,
    val numeroContacto: String?
)