package Bateria04_Proyecto_FicherosTexto;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.scene.shape.Path;

public class GeneradorXML {
	
	final static String csvExtension = ".csv";
	final static String textExtension = ".txt";
	final static String xmlExtension = ".xml";
	public static String logFileDirectory = "";
	public static int fileCount = 0;

	public static void main(String[] args) throws IOException {
		// Directorio o archivo CSV
		System.out.println("Introduce el directorio de tu archivo/s CSV (sin extension): ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String csvDirectory = reader.readLine();
	
		// Comprobar si es una ruta o un fichero
		File fileCSV = new File(csvDirectory);
		Boolean csvIsDirectory = isDirectory(fileCSV);
		
		// Generar archivo log en la misma ruta
		logFile(logFileDirectory+"\\logFile.log", "Directorio CSV registrado.");

		// Directorio del archivo de etiquetas
		System.out.println("Introduce la ruta del archivo de etiquetas (sin extension): ");
		String tagFileDirectory = reader.readLine();
		File tagFile = new File(tagFileDirectory+textExtension);
		
		logFile(logFileDirectory+"\\logFile.log", "Directorio de etiquetas registrado.");
		String [] labels = getLabels(tagFile);
		
		for (int i=0; i < labels.length; i++) {
			System.out.println(labels[i]);
		}
		

		// Directorio archivo de salida
		System.out.println("Nombre del fichero/s de salida (sin extension): ");
		String outputFileName = reader.readLine();
		
		
		//Conversion en funcion de si es directorio o fichero
		if (csvIsDirectory == true) {
			for (int i=0; i < fileCount; i++) {
				File outputDirectory = new File(csvDirectory+"\\"+outputFileName+i+xmlExtension);
			}
		} else {
			String directory = fileCSV.getParent();
			File outputDirectory = new File(directory+"\\"+outputFileName+xmlExtension);
		}
		
		logFile(logFileDirectory+"\\logFile.log", "Estableciendo directorio de salida...");

	}


	public static void findCsvFiles(File carpeta, String extension) {
		for (File ficheroEntrada : carpeta.listFiles()) {
			if (ficheroEntrada.isDirectory()) {
				findCsvFiles(ficheroEntrada, extension);
			} else {
				String fileName = ficheroEntrada.getName();

				if (fileName.endsWith(extension)) {
					System.out.println(fileName);
					fileCount += 1;
					String completeDirectory = carpeta+fileName; 
				}	           
			}
		}
	}
	
	
	public static boolean isDirectory(File ruta) {
		String rutaSalida = "";
		if (ruta.isDirectory()) {
			logFileDirectory = ruta.toString();
			logFile(logFileDirectory+"\\logFile.log", "Los datos introducidos corresponden a un direcotrio.");
			return true;
		} else {
			logFileDirectory = ruta.getParent();
			logFile(logFileDirectory+"\\logFile.log", "Los datos introducidos corresponden a un archivo.");
			return false;
		}		
	}


	public static void logFile (String mainDirectory, String log_text) {
		File logFile = new File (mainDirectory);

		try {
			if (!logFile.exists()) {
				logFile.createNewFile();
			}

			FileWriter log = new FileWriter(mainDirectory, true);
			DateFormat log_format = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
			Date date = new Date();
			log.write(log_format.format(date) + " --> " + log_text + "\r\n");
			log.close();

		} catch (Exception e) {
			System.out.println("ERROR");
		}
	}
	
	
    private static String[] getLabels(File tag) throws IOException {
    	// Get all the labels from the file converting to an array of strings
    	
		BufferedReader br = new BufferedReader(new FileReader(tag));
		
    	String label = null;
    	String labels = "";
    	while ((label = br.readLine()) != null) {
    		labels += label + ",";
    	}
		return labels.split(",");
	}
}
