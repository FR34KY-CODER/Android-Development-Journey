package com.example.constraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.constraintlayout.ui.theme.ConstraintLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val constrainsts = ConstraintSet {
                val greenBox = createRefFor("greenbox")
                val redBox = createRefFor("redbox")
                val guideline = createGuidelineFromTop(0.5f) //--> Basically ek invisible line baandi jisse object ko constrained
                // rakh paayien to ease centering or positoning of the element


                constrain(greenBox){
                    top.linkTo(guideline) //---> start se ek guideline hogi 50% of screeen tk uske neeche ayegi!
                    //top.linkTo(parent.top) ---> parent ke top se start karega
                    start.linkTo(parent.start)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }
                constrain(redBox){
                    top.linkTo(parent.top)
                    start.linkTo(greenBox.end)
                    end.linkTo(parent.end) // now it will come between kyunki dono taraf se constrained hai
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }
                //createHorizontalChain(greenBox, redBox) ----> isse dono contrained behave karenge yaani equally spaced walls se aur ek doosre se
                createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed) //-----> isse dono centre mai aa jayenge
            }
            ConstraintLayout (constrainsts, modifier = Modifier.fillMaxSize()){
                Box(modifier = Modifier
                    .background(Color.Green)
                    .layoutId("greenbox"))
                Box(modifier = Modifier
                    .background(Color.Red)
                    .layoutId("redbox"))
            }
        }
    }
}
