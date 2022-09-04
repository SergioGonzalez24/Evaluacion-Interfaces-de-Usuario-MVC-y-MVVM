package mx.itesm.smgv.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.smgv.mvvm.model.Ecuacion

class EcuacionVM  : ViewModel() {
    //Modelo
    private  val modelo = Ecuacion()
    //variables, se conectan el modelo y la vista
    val x1 = MutableLiveData<String>("")
    val x2 = MutableLiveData<String>("")
    val comprobacion = MutableLiveData<Double>(0.0)

    //Interface si son valores reales o complejos
    fun x1(a: Float, b:Float, c:Float, con:Boolean){
        x1.value = modelo.x1(a,b,c,con)
    }
    fun x2(a: Float, b:Float, c:Float,con:Boolean){
        x2.value = modelo.x2(a,b,c,con)
    }

    //Comprobacion de los valores
    fun comprobar(a: Float, b:Float, c:Float){
        comprobacion.value = modelo.comprobarDatos(a,b,c)
    }
    // Resetear x1 y x2
    fun reset() {
        modelo.reset()
        actualizarValores()
    }
    //Actualizar valores
    private fun actualizarValores() {
        x1.value = modelo.x1
        x2.value = modelo.x2
    }
}

