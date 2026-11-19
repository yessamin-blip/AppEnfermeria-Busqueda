package com.appenfermeria.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Search
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



//home sencilla solo para poder probar la navegacion Home <-> Busqueda en mi repo
//la home de verdad la hace mi compañera, al juntarlo usaremos la suya
@Composable
fun HomeScreen(
    onNavigateToSearch: () -> Unit = {}
) {

    val colorPrincipal = colorResource(id = R.color.medical_primary)

    val colorFondo = colorResource(id = R.color.medical_background)

    val colorTexto = colorResource(id = R.color.text_dark)

    val colorTarjeta = colorResource(id = R.color.medical_card)

    val colorGris = colorResource(id = R.color.text_dark)

    val colorBlanco = colorResource(id = R.color.white)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
    ) {

        //la barra de arriba azul como la de mi compañera
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorPrincipal)
                .padding(start = 16.dp, end = 16.dp, top = 40.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.LocalHospital,
                contentDescription = stringResource(id = R.string.logo_desc),
                tint = colorBlanco,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(id = R.string.home_title),
                color = colorBlanco,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

        }


        Column(modifier = Modifier.padding(24.dp)) {

            Text(
                text = stringResource(id = R.string.home_welcome),
                color = colorGris,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(24.dp))


            //el cuadrado para ir a la busqueda, al hacer click cambia la pantalla
            Column(
                modifier = Modifier
                    .size(110.dp)
                    .background(colorTarjeta, RoundedCornerShape(16.dp))
                    .clickable { onNavigateToSearch() },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.home_search_button),
                    tint = colorPrincipal,
                    modifier = Modifier.size(36.dp)
                )

            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.home_search_button),
                color = colorTexto,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

        }

    }

}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
