package com.example.connect4
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.connect4.ui.theme.Connect4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Connect4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    var showConnect4Game by remember { mutableStateOf(false) }
    var playwithAi by remember { mutableStateOf(false) }
    if (showConnect4Game) {
        if(playwithAi){ AiHomeScreen() }else{Connect4Game()}
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to Connect 4",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(24.dp),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(32.dp))

            OptionButton("2 Player") {

                showConnect4Game = true
            }

            Spacer(modifier = Modifier.height(16.dp))

            OptionButton("Play AI") {
                // Handle Play AI option
                showConnect4Game = true
                playwithAi = true
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun AiHomeScreen(){
var showConnect4Game by remember { mutableStateOf(false) }
    if (showConnect4Game) {
        Connect4Game()
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choose Difficulties",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(32.dp),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(24.dp))
            OptionButton("Easy") {
                showConnect4Game = true
            }
            Spacer(modifier = Modifier.height(24.dp))
            OptionButton("Medium") {
                // Handle Play AI option
                showConnect4Game = true
            }
            Spacer(modifier = Modifier.height(24.dp))
            OptionButton("Hard") {
                // Handle Play AI option
                showConnect4Game = true
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun OptionButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(6.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun Button(onClick: () -> Unit, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .size(150.dp, 50.dp)
            .background(Color.LightGray, shape = CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun Connect4Game() {
    var currentPlayer by remember { mutableStateOf(Player.RED) }
    val board by remember { mutableStateOf(Array(9) { Array(6) { Player.NONE } }) }
    var isWin by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Player's Turn: ${currentPlayer.name}",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp),
            color = Color.Black // Set text color to white for visibility
        )

        if (isWin) {
            Text(
                text = "Player ${currentPlayer.name} wins!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp),
                color = Color.Black
            )
        } else {
            Board(board) { row, col ->
                if (!isWin && board[row][col] == Player.NONE) {
                    var dropRow = row
                    for (r in board.indices.reversed()) {
                        if (board[r][col] == Player.NONE) {
                            dropRow = r
                            break
                        }
                    }
                    board[dropRow][col] = currentPlayer
                    if (checkWin(board, currentPlayer, dropRow, col)) {
                        isWin = true
                    } else {
                        currentPlayer = if (currentPlayer == Player.RED) Player.BLUE else Player.RED
                    }
                }
            }
        }
    }
}

@Composable
fun Board(board: Array<Array<Player>>, onColumnClicked: (Int, Int) -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        for (row in board.indices) {
            Row {
                for (col in board[row].indices) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(4.dp)
                            .clickable { onColumnClicked(row, col) },
                        contentAlignment = Alignment.Center
                    ) {
                        // Call AnimatePiece here
                        AnimatePiece(board[row][col], row)
                    }
                }
            }
        }
    }
}

@Composable
fun getColor(player: Player): Color {
    return when (player) {
        Player.RED -> Color.Red
        Player.BLUE -> Color.Blue
        else -> Color.Black
    }
}

enum class Player {
    NONE,
    RED,
    BLUE
}

fun checkWin(board: Array<Array<Player>>, player: Player, row: Int, col: Int): Boolean {
    // Check horizontally
    for (c in 0..board[row].size - 4) {
        if (board[row][c] == player &&
            board[row][c + 1] == player &&
            board[row][c + 2] == player &&
            board[row][c + 3] == player) {
            return true
        }
    }

    // Check vertically
    for (r in 0..board.size - 4) {
        if (board[r][col] == player &&
            board[r + 1][col] == player &&
            board[r + 2][col] == player &&
            board[r + 3][col] == player) {
            return true
        }
    }

    // Check diagonally (from bottom-left to top-right)
    for (r in 0..board.size - 4) {
        for (c in 0..board[row].size - 4) {
            if (board[r][c] == player &&
                board[r + 1][c + 1] == player &&
                board[r + 2][c + 2] == player &&
                board[r + 3][c + 3] == player) {
                return true
            }
        }
    }

    // Check diagonally (from top-left to bottom-right)
    for (r in 3 until board.size) {
        for (c in 0..board[row].size - 4) {
            if (board[r][c] == player &&
                board[r - 1][c + 1] == player &&
                board[r - 2][c + 2] == player &&
                board[r - 3][c + 3] == player) {
                return true
            }
        }
    }

    return false
}




@Composable
fun AnimatePiece(player: Player, targetRow: Int) {
    val transition = updateTransition(targetState = player, label = "")
    val offsetY by transition.animateDp(
        transitionSpec = {
            if (Player.NONE == player) {
                tween(durationMillis = 500)
            } else {
                spring()
            }
        }, label = ""
    ) { piece ->
        if (piece == Player.NONE) {
            (targetRow).dp
        } else {
            0.dp
        }
    }
    Box(
        modifier = Modifier
            .offset(y = offsetY)
            .size(40.dp)
            .background(getColor(player), shape = CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    Connect4Theme {
        HomeScreen()
    }
}
