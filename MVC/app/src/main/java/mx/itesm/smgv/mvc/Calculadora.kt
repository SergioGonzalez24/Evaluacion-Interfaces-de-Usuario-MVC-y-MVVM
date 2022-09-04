package mx.itesm.smgv.mvc

/**
 * @author Sergio Gonzalez
 * Actividad: Evaluable MVC
 *
 * Clase que genera los resultados y realiza los procesos de la app
 */

class Calculadora {
    var x1:String = ""
        private set
    var x2:String = ""
        private set
    var x1i:String = ""
        private set
    var x2i:String = ""
        private set
    var x1ii:String = ""
        private set
    var x2ii:String = ""
        private set



    fun x1(a:Float,b:Float,c:Float, con:Boolean): String {
        if (con == true) {
            x1 = (((-b+(Math.sqrt(((b*b)-(4*a*c)).toDouble())))/(2*a)).toFloat()).toString()
        }
        else {
            x1i = (((-b)/(2*a)).toFloat()).toString()
            x1ii =(((+(Math.sqrt(-(((b*b)-(4*a*c)).toDouble())))) / (2*a)).toFloat()).toString()
            x1=x1i+" + "+x1ii
        }
        return x1

    }
    fun x2(a:Float,b:Float,c:Float, con:Boolean):String {
        if (con == true) {
            x2 = (((-b-(Math.sqrt(((b*b)-(4*a*c)).toDouble())))/(2*a)).toFloat()).toString()
        }
        else {
            x2i = (((-b)/(2*a)).toFloat()).toString()
            x2ii =(((-(Math.sqrt(-(((b*b)-(4*a*c)).toDouble())))) / (2*a)).toFloat()).toString()
            x2=x2i+x2ii
        }
        return x2
    }

    fun comprobarDatos(a:Float,b:Float,c:Float) : Double{
        //Funcion de comprobacion
        var discriminante:Float = (b*b)-(4*a*c)

        if (discriminante == 0.0f) {
            return  1.0 //"Solamente si tienen 1 raiz"

        }
        else if (discriminante > 0.0f) {
            return 2.0 //"Solamente 2 raiz"


        }
        else if (discriminante < 0.0f) {
            return 3.0 //"Solamente tiene 2 raices complejas"


        }
        else {
            return 4.0 //  "Solamente 1 raiz " + (-c/b)
        }
    }
}