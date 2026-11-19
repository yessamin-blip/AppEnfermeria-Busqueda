package com.appenfermeria.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appenfermeria.R
import com.appenfermeria.model.Nurse
import com.appenfermeria.model.listaEnfermeros

@Composable
fun SearchScreen(
    onNavigateBack: () -> Unit = {},
    textoInicial: String = ""
) {

    //las variables con mutableStateOf para q la pantalla cambie cuando cambian
    //porId dice si busco por id (true) o por nombre (false)
    var porId by remember { mutableStateOf(true) }

    var texto by remember { mutableStateOf(textoInicial) }

    var resultados by remember { mutableStateOf(listOf<Nurse>()) }

    var mensaje by remember { mutableStateOf("") }

    //textoInicial lo uso solo en la preview para q salgan los 3 resultados
    if (textoInicial != "" && resultados.isEmpty()) {

        porId = false

        resultados = listaEnfermeros.filter { it.name.contains(textoInicial, ignoreCase = true) }

    }

    //los colores de colors.xml
    val colorPrincipal = colorResource(id = R.color.medical_primary)

    val colorFondo = colorResource(id = R.color.medical_background)

    val colorError = colorResource(id = R.color.medical_error)

    val colorTexto = colorResource(id = R.color.text_dark)

    val colorTarjeta = colorResource(id = R.color.medical_card)

    //los textos de strings.xml q uso en el click
    val textoVacio = stringResource(id = R.string.search_empty_field)

    val textoNoEncontrado = stringResource(id = R.string.search_no_results)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        //el icono q usamos tdo el grupo
        Icon(
            imageVector = Icons.Default.LocalHospital,
            contentDescription = stringResource(id = R.string.logo_desc),
            tint = colorPrincipal,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(id = R.string.search_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorTexto
        )

        Spacer(modifier = Modifier.height(24.dp))

        //el campo para escribir el id o el nombre
        OutlinedTextField(
            value = texto,
            onValueChange = { texto = it },
            label = {
                if (porId) {
                    Text(stringResource(id = R.string.search_id_label))
                } else {
                    Text(stringResource(id = R.string.search_name_label))
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        //boton para cambiar entre buscar por id o por nombre
        TextButton(onClick = {
            porId = !porId
            texto = ""
            resultados = listOf()
            mensaje = ""
        }) {

            if (porId) {
                Text(text = stringResource(id = R.string.search_change_name), color = colorPrincipal)
            } else {
                Text(text = stringResource(id = R.string.search_change_id), color = colorPrincipal)
            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        //el click q cambia los mutableState de resultados y mensaje
        Button(
            onClick = {

                mensaje = ""

                if (texto.isBlank()) {

                    resultados = listOf()
                    mensaje = textoVacio

                } else if (porId) {

                    //por id solo puede salir uno
                    resultados = listaEnfermeros.filter { it.id.toString() == texto.trim() }

                } else {

                    resultados = listaEnfermeros.filter { it.name.contains(texto.trim(), ignoreCase = true) }

                }

                if (texto.isNotBlank() && resultados.isEmpty()) {
                    mensaje = textoNoEncontrado
                }

            },
            colors = ButtonDefaults.buttonColors(containerColor = colorPrincipal),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.search_button))
        }

        //el mensaje si no hay nada o el campo esta vacio
        if (mensaje != "") {

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = mensaje, color = colorError, fontSize = 14.sp)

        }

        //una tarjeta por cada enfermero q encuentro
        for (enfermero in resultados) {

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = colorTarjeta),
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = stringResource(id = R.string.nurse_icon_desc),
                        tint = colorPrincipal,
                        modifier = Modifier.size(40.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {

                        Text(text = enfermero.name, fontWeight = FontWeight.Bold, color = colorTexto)

                        Text(text = stringResource(id = R.string.nurse_id_text, enfermero.id), color = colorTexto)

                        Text(text = stringResource(id = R.string.nurse_user_text, enfermero.user), color = colorTexto)

                    }

                }

            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        //volver a la home
        TextButton(
            onClick = onNavigateBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.back_home_button), color = colorPrincipal)
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}

//como se veria con 3 resultados buscando "Ana"
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreen3ResultadosPreview() {
    SearchScreen(textoInicial = "Ana")
}
