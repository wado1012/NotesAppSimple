package com.example.myappnotes.elements.uiElements


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction

//TextFieldElement für das Ersetllen und Bearbeiten der Notizen.
//Selbst erstellt um manuelle Handhabung zu vereinfachen

@Composable
fun TextFieldElement(
    text: String,
    replace: String,
    modifier: Modifier = Modifier,
    replaceTextShown: Boolean = true,
    onValueChange: (String)-> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Default
){
    Box(modifier = modifier){
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = textStyle,
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(imeAction = imeAction),
            keyboardActions = keyboardActions,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                }

        )
        if (replaceTextShown){
            Text(text= replace, style= textStyle, color = MaterialTheme.colors.primary )
        }
    }
}