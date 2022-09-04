package mx.itesm.smgv.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import mx.itesm.smgv.mvvm.R
import mx.itesm.smgv.mvvm.databinding.ActivityMainBinding
import mx.itesm.smgv.mvvm.viewmodel.EcuacionVM

/**
 * @author Sergio Gonzalez
 * Actividad Evaluacion MVVM
 *
 * Se llamaran 3 funcionnes diferentes para lograr el objetivo de resolver e identificar una ecuacion de segundo grados
 *
 */
class MainActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityMainBinding

    private  val ecuacionVM: EcuacionVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registrarEventos()
    }

    private fun registrarEventos() {
        binding.btnCalcular.setOnClickListener {
            val a = binding.etvalorA.text.toString().toFloat()
            val b = binding.etvalorB.text.toString().toFloat()
            val c = binding.etvalorC.text.toString().toFloat()
            ecuacionVM.comprobar(a,b,c)
            comprobarValores(a,b,c)

        }
    }

    private fun comprobarValores(a : Float,b  : Float,c  : Float){
        if (ecuacionVM.comprobacion.value == 1.0){
            val builder = AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("NO es una ecuacion de 2do Grado")
                .setCancelable(false)
                .setPositiveButton("Aceptar"){_, _->
                    ecuacionVM.reset()
                }
            builder.show()
        } else if (ecuacionVM.comprobacion.value == 2.0) {
            //Alert Dialog HAce un cuadro de alerta
            val builder = AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("La ecuacion solamente tiene dos raices")
                .setCancelable(false)
                .setPositiveButton("Aceptar") { _, _ ->
                    ecuacionVM.x1(a,b,c,true)
                    ecuacionVM.x2(a,b,c,true)
                    registrarObservadores1(true)
                    registrarObservadores2(true)
                }
            builder.show()

        }else if (ecuacionVM.comprobacion.value == 3.0)
        {
            //Alert Dialog HAce un cuadro de alerta
            val builder = AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("La ecuacion solamente tiene dos raices complejas")
                .setCancelable(false)
                .setPositiveButton("Aceptar") { _, _ ->
                    ecuacionVM.x1(a,b,c,false)
                    ecuacionVM.x2(a,b,c, false)
                    registrarObservadores1(false)
                    registrarObservadores2(false)
                }
            builder.show()

        }else if (ecuacionVM.comprobacion.value == 4.0)
        {
            //Alert Dialog HAce un cuadro de alerta
            val builder = AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("La ecuacion lineal solamente tiene una raiz : " + (-c/b))
                .setCancelable(false)
                .setPositiveButton("Aceptar") { _, _ ->
                    ecuacionVM.reset()
                }
            builder.show()

        }

    }




    private fun registrarObservadores1(condicion: Boolean) {
        //se suscriben los cambios
        if (condicion == true){
            ecuacionVM.x1.observe(this) {
                //Mostrarlo en etX1
                binding.etX1.setText(it.toString())
            }}else{
            ecuacionVM.x1.observe(this) {
                //Mostrarlo en etX1
                binding.etX1.setText(it.toString() + " i")
            }
        }
    }
    private fun registrarObservadores2(condicion:Boolean){
        if (condicion == true){
            ecuacionVM.x2.observe(this) {
                //Mostrarlo en etX1
                binding.etX2.setText(it.toString())
            }}else {
            ecuacionVM.x2.observe(this) {
                //Mostrarlo en etX1
                binding.etX2.setText(it.toString() + " i")
            }
        }
    }

}