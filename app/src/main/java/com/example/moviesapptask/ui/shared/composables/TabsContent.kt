package com.example.moviesapptask.ui.shared.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun TabsContent(
    selectedTabIndex: Int,
    tabsList: List<String>,
    onSelectTab: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
    ) {
        TabRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    RoundedCornerShape(10.dp)
                ),
            indicator = {},
            selectedTabIndex = selectedTabIndex
        ) {
            tabsList.forEachIndexed { index, tabText ->
                Tab(
                    modifier = Modifier.background(
                        if (selectedTabIndex == index) {
                            MaterialTheme.colorScheme.surfaceVariant
                        } else {
                            MaterialTheme.colorScheme.background
                        }
                    ),
                    text = {
                        Text(
                            text = tabText,
                            style =
                            if (selectedTabIndex == index) {
                                MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Black,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            } else {
                                MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onBackground)
                            }
                        )
                    },
                    selected = selectedTabIndex == index,
                    onClick = { onSelectTab(index) },
                )
            }
        }
    }
}