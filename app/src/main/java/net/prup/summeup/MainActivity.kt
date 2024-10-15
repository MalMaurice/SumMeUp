package net.prup.summeup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import net.prup.summeup.ui.theme.SumMeUpTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
            SumMeUpTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        MediumTopAppBar(title = {Text("SumMeUp")}, scrollBehavior = scrollBehavior)
                    }) { innerPadding ->
                    Box(Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp)){
                        LazyColumn {
                            // Todo Empty States
                            item {
                                Box(modifier = Modifier.padding(bottom = 12.dp).clip(RoundedCornerShape(12.dp)).fillMaxWidth().background(MaterialTheme.colorScheme.secondaryContainer).padding(4.dp)){
                                    Column {
                                        Text("Monthly Cost:")
                                        Text("100000€")
                                    }
                                }
                            }
                            item {
                                SpendingElement(Money(120),Money(90))
                            }

                        }
                        FloatingActionButton(onClick = {}, shape = RoundedCornerShape(12.dp), containerColor = MaterialTheme.colorScheme.secondaryContainer, modifier = Modifier.align(Alignment.BottomEnd).padding(vertical = 24.dp)) {
                            Icon(Icons.Rounded.Add, null, modifier = Modifier.size(36.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SpendingElement(amountStart: Money, amountLeft: Money){
    var visible: Boolean by remember { mutableStateOf(false) }
    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).clickable { visible = !visible }.background(MaterialTheme.colorScheme.surfaceContainerLow).fillMaxWidth().sizeIn(minHeight = 120.dp).padding(8.dp)){
        Column {
            Row() {
                Text("Start Amount: ")
                Spacer(Modifier.weight(1f))
                Text("${amountStart.euro},${amountStart.cent}€"  )
            }
            Spacer(Modifier.width(16.dp))
            Row() {
                Text("Amount Left: ")
                Spacer(Modifier.weight(1f))
                Text("${amountLeft.euro},${amountLeft.cent}€"  )
            }
            Row() {
                Text("Cycles Left: ")
                Spacer(Modifier.weight(1f))
                Text("999"  )
            }
            // More info visual
            AnimatedVisibility(visible) {
                Column {
                    Text("Start Date ...")
                    Text("End Date ...")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("69% Gezahlt")
                        Spacer(Modifier.width(12.dp))
                        LinearProgressIndicator(progress = { 0.69f }, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

data class Money(val euro: Int, val cent: Int = 0)