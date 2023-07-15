package dev.hayohtee.quicknote.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.hayohtee.quicknote.ui.screens.addnote.AddNoteScreen
import dev.hayohtee.quicknote.ui.screens.addnote.AddNoteViewModel
import dev.hayohtee.quicknote.ui.screens.detail.NoteDetailScreen
import dev.hayohtee.quicknote.ui.screens.detail.NoteDetailViewModel
import dev.hayohtee.quicknote.ui.screens.editnote.EditNoteScreen
import dev.hayohtee.quicknote.ui.screens.editnote.EditNoteViewModel
import dev.hayohtee.quicknote.ui.screens.notes.NoteListScreen
import dev.hayohtee.quicknote.ui.screens.notes.NotesViewModel
import dev.hayohtee.quicknote.ui.screens.search.SearchNoteScreen
import dev.hayohtee.quicknote.ui.screens.search.SearchNotesViewModel

@Composable
fun QuickNotesApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "notes") {
        composable(route = "notes") {
            val viewModel: NotesViewModel = hiltViewModel()

            NoteListScreen(
                state = viewModel.state.value,
                onSearchClick = { navController.navigate(route = "search") },
                onItemClick = { id ->
                    navController.navigate("notes/$id")
                },
                onAddNoteClick = { navController.navigate(route = "add_note") }
            )
        }

        composable(route = "search") {
            val viewModel: SearchNotesViewModel = hiltViewModel()

            SearchNoteScreen(
                notes = viewModel.filteredNote.value,
                query = viewModel.query.value,
                onQueryChange = viewModel.onQueryChange,
                onSearch = { viewModel.filterNotes(it) },
                active = viewModel.active.value,
                onActiveChange = { isActive ->
                    viewModel.onActiveChange
                    if (!isActive) {
                        navController.popBackStack()
                    }
                },
                onNoteItemClick = { id -> navController.navigate("notes/$id") },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(route = "add_note") {
            val viewModel: AddNoteViewModel = hiltViewModel()

            AddNoteScreen(
                note = viewModel.state.value,
                onTitleChange = viewModel.onTitleChange,
                onContentChange = viewModel.onContentChange,
                onBackButtonClick = { navController.popBackStack() },
                saveNote = {
                    viewModel.addNote()
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "notes/{note_id}",
            arguments = listOf(navArgument("note_id") {
                type = NavType.LongType
            })
        ) {
            val viewModel: NoteDetailViewModel = hiltViewModel()

            NoteDetailScreen(
                state = viewModel.state.value,
                onEditNote = { id -> navController.navigate("notes/edit/$id") },
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable(
            route = "notes/edit/{note_id}",
            arguments = listOf(navArgument("note_id") {
                type = NavType.LongType
            })
        ) {
            val viewModel: EditNoteViewModel = hiltViewModel()

            EditNoteScreen(
                note = viewModel.state.value,
                onTitleChange = viewModel.onTitleChange,
                onContentChange = viewModel.onContentChange,
                onBackButtonClick = { navController.popBackStack() },
                updateNote = {
                    viewModel.updateNote()
                    navController.popBackStack()
                }
            )
        }
    }
}