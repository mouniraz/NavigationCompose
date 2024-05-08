package com.example.navapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navapp.ui.theme.NavAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MyApp()
{ val navController= rememberNavController()
NavHost(navController = navController, startDestination = "MyApp" )
{
    composable(route="myApp"){FirstCompose(navController)}
    composable(route="Second/{p1}/{p2}", arguments = listOf(navArgument("p1")
    {type= NavType.StringType
    defaultValue=""
        nullable=true
   },navArgument("p2")
        {type= NavType.StringType
            defaultValue=""
            nullable=true
        }
        )){entry->SecondCompose(navController,entry.arguments?.getString("p1"),entry.arguments?.getString("p2"))}



}
}
@Composable
fun FirstCompose(navController: NavController)
{ val p1="param1"
    val p2="param2"
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "First Compose")
        Button(onClick = { navController.navigate("Second/$p1/$p2") }) {Text("next")
        }
    }


}


@Composable
fun SecondCompose(navController: NavController, p1: String?, p2: String?)
{
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Second Compose $p1 $p2")
        Button(onClick = { navController.navigate("MyApp") }) {Text("next")
       }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavAppTheme {
        Greeting("Android")
    }
}
