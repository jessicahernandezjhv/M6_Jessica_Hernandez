package Bateria03_ProyectoFicherosTexto;

/* Ejercicio 1. Averigua jer�rquicamente, �cu�l es la relaci�n entre DataInputStream y
FileInputStream? */

/* En el nivel m�s basico estaria la clase InputStream (clase padre). En general los 
 * Streams leen bytes en bruto. El resto son clases hijas que heredan de InputStream,
 * que permiten leer los datos a un nivel m�s alto.
 * 
 * En este caso FileInputStream es una clase hija que se utiliza para crear una
 * secuencia de entrada para leer bytes de un archivo.
 * 
 * En cambio, la clase DataInputStream es capaz de leer los bytes pero como enteros,
 * float, doubles, etc. (datos de tipo primitivo).
 * 
 * Por lo tanto, el orden jerarquico de las clases ser�a en primer termino InputStream,
 * seguida por FileInputStream y finalmente DataInputStream, ya que la primera permite
 * leer bytes en bruto, la siguiente permite leer los bytes de un archivo y la ultima
 * permite leer los bytes e interpretarlos como tipos de datos b�sicos. */
