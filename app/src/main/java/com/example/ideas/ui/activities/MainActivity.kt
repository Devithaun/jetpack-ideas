package com.example.ideas.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ideas.data.model.FunIdea
import com.example.ideas.domain.api.UiState
import com.example.ideas.ui.components.ImageButton
import com.example.ideas.ui.theme.IdeasTheme
import com.example.ideas.ui.viewmodels.IdeaViewModel
import com.example.ideas.ui.viewmodels.IdeaViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IdeasTheme {
                val viewModel: IdeaViewModel = viewModel(factory = IdeaViewModelFactory(this))
                IdeaTextView(viewModel)
            }
        }
    }
}

@Composable
fun IdeaTextView(viewModel: IdeaViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.generateIdea()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is UiState.Success -> {
                val idea = (uiState as UiState.Success<FunIdea>).data
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = idea.name, style = MaterialTheme.typography.headlineLarge)
                }
            }

            is UiState.Error -> {
                val message = (uiState as UiState.Error).message
                Text(
                    text = message,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        ImageButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp),
            Icons.Default.AddCircle,
            null,
            "Generate an Idea",
            onClick = { viewModel.generateIdea() }
        )

//        Button(
//            onClick = { viewModel.generateIdea() },
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(bottom = 40.dp)
//        ) {
//            Icon(
//                imageVector = Icons.Rounded.AddCircle,
//                contentDescription = "Generate an Idea",
//                modifier = Modifier.size(24.dp)
//            )
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun IdeaPreview() {
//    IdeasTheme {
//        IdeaTextView(viewModel)
//    }
}