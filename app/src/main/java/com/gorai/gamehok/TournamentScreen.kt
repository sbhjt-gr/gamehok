package com.gorai.gamehok

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun TournamentScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(stringResource(R.string.nav_tournament))
    }
}
