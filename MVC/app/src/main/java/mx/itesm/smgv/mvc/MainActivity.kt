package mx.itesm.smgv.mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import mx.itesm.smgv.mvc.databinding.ActivityMainBinding

/**
 * @author Sergio Gonzalez
 * Titulo: Evaluable MVC
 *
 * Se llaman 3 funciones las cuales nos daran como resultado X1, X2 y Comprobacion
 * Las cuales nos daran como resultado las raices y si es o no valido para una ecuacion de segundo grado
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ConfigurarGUI()

    }

    private fun ConfigurarGUI() {
        binding.btnCalcular.setOnClickListener {
            val a = binding.etvalorA.text.toString().toFloat()
            val b = binding.etvalorB.text.toString().toFloat()
            val c = binding.etvalorC.text.toString().toFloat()
            val modelo = Calculadora()
            val Comp: Double = modelo.comprobarDatos(a, b, c)
            comprobarValores(a, b, c, Comp)
        }
    }

    private fun comprobarValores(a: Float, b: Float, c: Float, comp: Double) {
        val modelo = Calculadora()
        val x1_true: String = modelo.x1(a, b, c, true)
        val x2_true: String = modelo.x2(a, b, c, true)
        val x1_false: String = modelo.x1(a, b, c, false)
        val x2_false: String = modelo.x2(a, b, c, false)

        if (comp == 1.0) {
            val builder = AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("No es una ecuacion de 2do Grado\n Ingrese nuevos valores")
                .setCancelable(false)
                .setPositiveButton("Aceptar") { _, _ -> }
            builder.show()
        }
        else if (comp == 2.0) {
            // Generar cuadro de alerta
            val builder = AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("La ecuacion tiene dos raices")
                .setCancelable(false)
                .setPositiveButton("Aceptar") { _, _ ->

                    registrarObservadores1(x1_true, true)
                    registrarObservadores2(x2_true, true)
                }
            builder.show()

        }
        else if (comp == 3.0) {
            // Generar cuadro de alerta
            val builder = AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("La ecuacion tiene dos raices complejas")
                .setCancelable(false)
                .setPositiveButton("Aceptar") { _, _ ->

                    registrarObservadores1(x1_false, false)
                    registrarObservadores2(x2_false, false)
                }
            builder.show()

        }
        else if (comp == 4.0) {
            // Generar cuadro de alerta
            val builder = AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("Los valores van deacuerdo con una ecuacion de segundo grado\n Intentelo de nuevo")
                .setCancelable(false)
                .setPositiveButton("Aceptar") { _, _ -> }
            builder.show()

        }

    }


    private fun registrarObservadores1(valor: String, condicion: Boolean) {
        val modelo = Calculadora()
        //se suscriben los cambios
        if (condicion == true) {
            binding.etX1.setText(valor)
        } else {
            binding.etX1.setText(valor + " i")
        }
    }

    private fun registrarObservadores2(valor: String, condicion: Boolean) {
        if (condicion == true) {
            //Mostrarlo en etX1
            binding.etX2.setText(valor)
        } else {
            //Mostrarlo en etX1
            binding.etX2.setText(valor + " i")
        }
    }

}