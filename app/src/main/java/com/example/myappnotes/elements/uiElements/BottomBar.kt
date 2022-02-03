package com.example.myappnotes.elements.uiElements


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//Eine einfache BottomBar die Platz lässt für einen FloatinActionButton

@Composable
fun AppBottomBar(fabShape : RoundedCornerShape) {

    BottomAppBar(
        elevation = 10.dp,
        cutoutShape = fabShape
    ) {

        Text(
            text = "",
            style = MaterialTheme.typography.h5
        )
        // The actions should be at the end of the BottomAppBar
        Spacer(Modifier.weight(1f, true))
        Text(
            text = "",
            style = MaterialTheme.typography.h5
        )

    }

}
