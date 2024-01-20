package screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Birds",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun BottomBar(selectedItem: Int, select: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomItem(
                selected = selectedItem == 0,
                selectedIcon = Icons.Filled.Home,
                normalIcon = Icons.Outlined.Home,
                text = "Home",
                onClick = {
                },
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 8.dp)
                    .clickable {
                        select.invoke(0)
                    }
            )
            BottomItem(
                selected = selectedItem == 1,
                selectedIcon = Icons.Filled.Favorite,
                normalIcon = Icons.Outlined.Favorite,
                text = "Favt",
                onClick = {
                },
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 8.dp)
                    .clickable {
                        select.invoke(1)
                    }
            )
        }
    }
}

@Composable
fun RowScope.BottomItem(
    selected: Boolean,
    selectedIcon: ImageVector,
    normalIcon: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .weight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val color = if (selected) {
            Color.Black
        } else {
            Color.Gray
        }
        Image(
            imageVector = if (selected) {
                selectedIcon
            } else {
                normalIcon
            },
            contentDescription = null,
            modifier = Modifier
                .size(35.dp),
            colorFilter = ColorFilter.tint(color)
        )
        Spacer(
            Modifier
                .height(4.dp)
        )
        Text(text, fontSize = 13.sp, color = color)
    }

}