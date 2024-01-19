package org.care.packie.feature.stuffs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import org.care.packie.ui.DoneDialogType
import org.care.packie.ui.TextFieldDialogType
import org.care.packie.ui.component.dialog.DoneDialog
import org.care.packie.ui.component.dialog.TextFieldDialog
import org.care.packie.ui.component.stuff.rememberEditModeState
import org.care.packie.ui.theme.PackieTheme
import org.care.packie.utils.ui.LoadingScreen

private val ALREADY_ADDED = "이미 추가된 물건이에요"
private val ALREADY_REMOVED = "이미 삭제된 물건이에요"

@Composable
fun StuffsScreenRoot(
    viewModel: StuffsViewModel = hiltViewModel()
) {
    val category = "출근"
    val state by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    var isAddStuffTextFieldDialogOpen by remember {
        mutableStateOf(false)
    }
    var isDoneDialogOpen by remember {
        mutableStateOf(false)
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    val editMode = rememberEditModeState()
    var stuffs by remember {
        mutableStateOf(emptyMap<String, Boolean>())
    }
    val snackBarHostState = remember { SnackbarHostState() }
    when (state) {
        is StuffsUiState.Loading -> {
            isLoading = true
            viewModel.getStuffs(category)
        }

        is StuffsUiState.Success -> {
            isLoading = false
            editMode.disableEditMode()
            stuffs = (state as StuffsUiState.Success).stuffs
        }

        is StuffsUiState.SuccessEditMode -> {
            isLoading = false
            editMode.enableEditMode()
            stuffs = (state as StuffsUiState.SuccessEditMode).stuffs
        }

        is StuffsUiState.Complete -> {
            isLoading = false
            isDoneDialogOpen = true
        }
    }
    StuffsScreen(
        category = category,
        snackBarHostState = snackBarHostState,
        isEditMode = editMode.isEditMode,
        currentStuffs = stuffs,
        onClickToggle = viewModel::checkStuffs,
        onClickAdd = { isAddStuffTextFieldDialogOpen = true },
        onClickRemove = {
            viewModel.removeStuff(it).onFailure {
                scope.launch {
                    snackBarHostState.showSnackbar(ALREADY_REMOVED)
                }
            }
        },
        onClickUpdate = viewModel::saveStuffs,
        enableEditMode = viewModel::enableEditMode,
        disableEditMode = viewModel::disableEditMode,
        onCategoryClick = {}
    )
    ShowAddStuffTextFieldDialog(
        visible = isAddStuffTextFieldDialogOpen,
        onConfirmation = {
            viewModel.addStuff(it)
                .onSuccess {
                    isAddStuffTextFieldDialogOpen = false
                }
                .onFailure {
                    scope.launch {
                        snackBarHostState.showSnackbar(ALREADY_ADDED)
                    }
                }
        },
        onDismiss = { isAddStuffTextFieldDialogOpen = false }
    )
    ShowDoneDialog(
        visible = isDoneDialogOpen,
        onConfirmation = {
            scope.launch {
                snackBarHostState.showSnackbar("이동만 하면 될듯")
            }
        },
        onDismiss = { isDoneDialogOpen = false }
    )
    LoadingScreen(
        visible = isLoading,
        onDismissRequest = { isLoading = false }
    )
}

@Composable
fun ShowAddStuffTextFieldDialog(
    visible: Boolean = false,
    onConfirmation: (String) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        TextFieldDialog(
            type = TextFieldDialogType.ADD_STUFF,
            onConfirmation = onConfirmation,
            onDismiss = onDismiss
        )
    }
}

@Composable
fun ShowDoneDialog(
    visible: Boolean = false,
    onConfirmation: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        DoneDialog(
            type = DoneDialogType.COMPLETE,
            onConfirm = onConfirmation,
            onDismiss = onDismiss
        )
    }
}

@Preview
@Composable
fun StuffsScreenRootPreview() {
    PackieTheme {
        StuffsScreenRoot()
    }
}