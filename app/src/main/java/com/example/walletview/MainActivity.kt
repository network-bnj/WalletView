package com.example.walletview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Expense(val name: String, val amount: Float, val color: Color)
val expenses = listOf(
 Expense("Comida",3200f,Color(0xFF4F7CAC)),
 Expense("Transporte",1800f,Color(0xFFFFB547)),
 Expense("Servicios",2500f,Color(0xFF43A58A)),
 Expense("Otros",1500f,Color(0xFFB07CC6))
)

class MainActivity : ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContent { WalletScreen() }
 }
}

@Composable
fun WalletScreen() {
 val total = expenses.sumOf { it.amount.toDouble() }.toFloat()
 val months = listOf("Ene","Feb","Mar","Abr","May","Jun")
 val values = listOf(6500f,7200f,5900f,8100f,7600f,total)
 MaterialTheme {
  Surface(Modifier.fillMaxSize(), color=Color(0xFFF3F5F8)) {
   Column(Modifier.verticalScroll(rememberScrollState()).padding(18.dp), verticalArrangement=Arrangement.spacedBy(15.dp)) {
    Text("WalletView", fontSize=28.sp, fontWeight=FontWeight.Bold, color=Color(0xFF203A5F))
    Text("Resumen financiero mensual", color=Color.Gray)
    Card(shape=RoundedCornerShape(18.dp), colors=CardDefaults.cardColors(containerColor=Color(0xFF203A5F))) {
     Column(Modifier.fillMaxWidth().padding(20.dp)) {
      Text("Gastos registrados · Junio", color=Color.White)
      Text("$${"%,.0f".format(total)} MXN", fontSize=30.sp, fontWeight=FontWeight.Bold, color=Color.White)
      Text("Datos ficticios de ejemplo", color=Color.White.copy(alpha=.8f), fontSize=12.sp)
     }
    }
    Card(colors=CardDefaults.cardColors(containerColor=Color.White)) {
     Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(12.dp)) {
      Text("Distribución de gastos", fontSize=18.sp, fontWeight=FontWeight.SemiBold)
      Row(verticalAlignment=Alignment.CenterVertically) {
       PieChart(Modifier.size(150.dp))
       Spacer(Modifier.width(14.dp))
       Column(verticalArrangement=Arrangement.spacedBy(9.dp)) {
        expenses.forEach { e ->
         Row(verticalAlignment=Alignment.CenterVertically) {
          Box(Modifier.size(10.dp).background(e.color, RoundedCornerShape(3.dp)))
          Spacer(Modifier.width(6.dp))
          Column {
           Text(e.name, fontSize=13.sp)
           Text("$${"%,.0f".format(e.amount)}", fontSize=12.sp, color=Color.Gray)
          }
         }
        }
       }
      }
     }
    }
    Card(colors=CardDefaults.cardColors(containerColor=Color.White)) {
     Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(10.dp)) {
      Text("Gastos por mes", fontSize=18.sp, fontWeight=FontWeight.SemiBold)
      BarChart(months, values, Modifier.fillMaxWidth().height(170.dp))
     }
    }
    Text("Proyecto académico · WalletView", color=Color.Gray, fontSize=12.sp, modifier=Modifier.align(Alignment.CenterHorizontally))
   }
  }
 }
}

@Composable
fun PieChart(modifier: Modifier) {
 Canvas(modifier) {
  val sum=expenses.sumOf { it.amount.toDouble() }.toFloat()
  var start=-90f
  expenses.forEach { e ->
   val sweep=e.amount/sum*360f
   drawArc(e.color,start,sweep,true)
   start+=sweep
  }
 }
}

@Composable
fun BarChart(labels: List<String>, values: List<Float>, modifier: Modifier) {
 Canvas(modifier) {
  val max=(values.maxOrNull() ?: 1f)*1.15f
  val chartH=size.height-26.dp.toPx()
  val slot=size.width/values.size
  values.forEachIndexed { i,v ->
   val h=v/max*chartH
   drawRoundRect(Color(0xFF4F7CAC), Offset(i*slot+slot*.22f,chartH-h), Size(slot*.56f,h), androidx.compose.ui.geometry.CornerRadius(7f,7f))
  }
  drawLine(Color.LightGray,Offset(0f,chartH),Offset(size.width,chartH),2f)
 }
 Row(Modifier.fillMaxWidth(), horizontalArrangement=Arrangement.SpaceBetween) {
  labels.forEach { Text(it,fontSize=11.sp,color=Color.Gray) }
 }
}
