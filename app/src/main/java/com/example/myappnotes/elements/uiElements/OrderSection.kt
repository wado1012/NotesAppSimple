package com.example.myappnotes.elements.uiElements


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myappnotes.elements.NoteOrder
import com.example.myappnotes.elements.OrderType

//Option um die Ordnung der einzelnen Elemenet zu ändern
//Auswählbar zwischen Ascending und Descending auf den ErstellZeitpunkt bezogen

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    Box(
        modifier = Modifier.background(MaterialTheme.colors.surface)
            .height(60.dp)
    ){
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier= Modifier.width(12.dp))
                Column() {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Sort by Date:",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.secondary
                    )
                }

                Spacer(modifier= Modifier.width(4.dp))
                Column() {
                    Spacer(modifier = Modifier.height(20.dp))
                    DefaultRadioButton(
                        text = "Ascending",
                        selected = noteOrder.orderType is OrderType.Ascending,
                        onSelect = { onOrderChange(noteOrder.copy(OrderType.Ascending)) },
                    )
                }
                Spacer(modifier= Modifier.width(4.dp))
                Column() {
                    Spacer(modifier = Modifier.height(20.dp))
                    DefaultRadioButton(
                        text = "Descending",
                        selected = noteOrder.orderType is OrderType.Descending,
                        onSelect = { onOrderChange(noteOrder.copy(OrderType.Descending)) }
                    )
                }
            }
        }
    }

}