package dev.hayohtee.quicknote.ui

import android.icu.text.DateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hayohtee.quicknote.domain.Note
import dev.hayohtee.quicknote.ui.theme.DarkRed
import dev.hayohtee.quicknote.ui.theme.LightGreen
import dev.hayohtee.quicknote.ui.theme.Orange
import dev.hayohtee.quicknote.ui.theme.Purple
import dev.hayohtee.quicknote.ui.theme.QuickNoteTheme
import java.util.Date

@Composable
fun NoteItem(
    note: Note,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    val color = remember {
        getRandomColors()
    }

    Card(
        modifier = modifier.clickable(
            role = Role.Button,
            onClick = { onClick(note.id) }
        ),
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 8,
                overflow = TextOverflow.Clip
            )

            Text(
                text = DateFormat.getPatternInstance(DateFormat.YEAR_MONTH_DAY).format(note.date),
                style = MaterialTheme.typography.labelLarge,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

fun getRandomColors(): Color {
    return listOf(Orange, DarkRed, Purple, LightGreen).random()
}

@Composable
fun NoteItemSpanned(
    note: Note,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    val color = remember {
        getRandomColors()
    }

    Card(
        modifier = modifier.clickable(
            role = Role.Button,
            onClick = { onClick(note.id) }
        ),
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(
                text = note.title,
                modifier = Modifier.weight(0.7f),
                style = MaterialTheme.typography.titleLarge,
                maxLines = 8,
                overflow = TextOverflow.Clip,
            )
            Text(
                text = DateFormat.getPatternInstance(DateFormat.YEAR_MONTH_DAY).format(note.date),
                style = MaterialTheme.typography.labelLarge,
                color = Color.DarkGray,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(0.3f)
                    .align(Alignment.Bottom)
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoteItemPreview() {
    QuickNoteTheme {
        NoteItem(
            note = Note(
                id = 1,
                title = "How to make your personal brand stand out online",
                content = "content",
                date = Date()
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoteItemSpannedPreview() {
    QuickNoteTheme {
        NoteItemSpanned(
            note = Note(
                id = 1,
                title = "How to make your personal brand stand out online",
                content = "content",
                date = Date()
            ),
            onClick = {}
        )
    }
}
