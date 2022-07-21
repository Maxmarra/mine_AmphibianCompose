package com.example.amphibiancompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amphibiancompose.network.Amphibian
import com.example.amphibiancompose.ui.theme.AmphibianComposeTheme

class MainActivity() : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmphibianComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AmphibianApp(viewModel = AmphibianViewModel())
                }
            }
        }
    }
}

@Composable
fun AmphibianApp(viewModel: AmphibianViewModel) {

    val amphibians by viewModel.amphibians.observeAsState()

    amphibians?.let { AmphibianList(amphibians = it) }
}

@Composable
fun AmphibianList(amphibians: List<Amphibian>) {
    LazyColumn() {
        items(amphibians) {
            Amphibian(amphibian = it)
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
fun Amphibian(amphibian: Amphibian) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = amphibian.name)
        Text(text = amphibian.description)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AmphibianComposeTheme {

    }
}