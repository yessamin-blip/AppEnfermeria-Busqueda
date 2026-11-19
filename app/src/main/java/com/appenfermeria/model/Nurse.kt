package com.appenfermeria.model

//la clase enfermero, como en el backend pero sin la contraseña
//no conectamos con el backend asi q los datos son de ejemplo
data class Nurse(

    val id: Int,

    val name: String,

    val user: String

)



//lista de enfermeros de prueba
//hay 3 q se llaman ana para q al buscar por nombre salgan 3 resultados
val listaEnfermeros = listOf(

    Nurse(1, "Ana García", "agarcia"),

    Nurse(2, "Carlos Pérez", "cperez"),

    Nurse(3, "Ana López", "alopez"),

    Nurse(4, "Laura Martín", "lmartin"),

    Nurse(5, "Ana Torres", "atorres"),

    Nurse(6, "Jordi Puig", "jpuig")

)
