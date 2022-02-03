package com.example.myappnotes.elements.uiElements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//Dies ist eine exklussive BottomBar, die zusätzlich einen Delete Button besitzt mit dem die
//ausgewählte Notiz gelöscht werden kann

@Composable
fun AppBottomBarIcons(
    fabShape : RoundedCornerShape,
    onDeleteClick: () -> Unit) {

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

        IconButton(onClick = onDeleteClick,
            modifier = Modifier
                .weight(0.5f)) {
            Text(text = "Delete",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.secondary,
                
            )

        }
    }
}
