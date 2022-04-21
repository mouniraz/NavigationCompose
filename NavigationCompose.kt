package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.theme.MyApplicationTheme

class NavigationCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme

                    Myapp()

            }
        }
    }
}

@Composable
fun Myapp() {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "myapp" )
    {
        composable("dest/{name}", arguments = listOf(navArgument(name="name"){
            type= NavType.StringType
            defaultValue="test"
            nullable=true
        })){ entry->Destination(navController = navController, name = entry.arguments?.getString("name"))}
        composable("myapp"){ Firstscreen(navController) }


    }

}
@Composable
fun Destination(navController:NavController, name: String?) {
   Column() {

       Text(name?:"")
       Button(onClick = { navController.navigate("myapp") }) {
           Text("go to myaapp")

       }
   }
}
@Composable
fun Firstscreen(navController:NavController) {

    Button(onClick = { navController.navigate("dest/1") }, modifier = Modifier.height(100.dp))
    {
        Text("go to destination")
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    MyApplicationTheme {
        Myapp()
    }
}