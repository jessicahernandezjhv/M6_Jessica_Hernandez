package Bateria04_Proyecto_FicherosTexto;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GeneradorXML {
	
	final static String csvExtension = ".csv";
	final static String textExtension = ".txt";
	final static String xmlExtension = ".xml";
	public static String logFileDirectory = "";
	public static int fileCount = 0;
	public static String mainDirectory = "";


	public static void main(String[] args) throws IOException {
		// Directorio o archivo CSV
		System.out.println("Introduce el directorio de tu archivo/s CSV: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String csvDirectory = reader.readLine();
	
		// Comprobar si es una ruta o un fichero
		File fileCSV = new File(csvDirectory);
		Boolean csvIsDirectory = checkIfDirectory(fileCSV);
		
		// Generar archivo log en la misma ruta
		logFile(logFileDirectory+"\\logFile.log", "Directorio CSV registrado.");

		// Directorio del archivo de etiquetas
		System.out.println("Introduce la ruta del archivo de etiquetas (sin extension): ");
		String tagFileDirectory = reader.readLine();
		File tagFile = new File(tagFileDirectory+textExtension);
		
		logFile(logFileDirectory+"\\logFile.log", "Directorio de etiquetas registrado.");

		// Directorio archivo de salida
		System.out.println("Nombre del fichero/s de salida (sin extension): ");
		String outputFileName = reader.readLine();
		
		//TODO : Hacer que cambie el archivo CSV
		//Conversion en funcion de si es directorio o fichero
		if (csvIsDirectory == true) {
			findCsvFiles(fileCSV, csvExtension);
			File csvFile = new File (mainDirectory);
			for (int i=0; i < fileCount; i++) {
				File outputDirectory = new File(csvDirectory+"\\"+outputFileName+(i+1)+xmlExtension);
				xmlConverter(tagFile, csvFile, outputDirectory);	
			}
			
		} else {
			String directory = fileCSV.getParent();
			File outputDirectory = new File(directory+"\\"+outputFileName+xmlExtension);
			xmlConverter(tagFile, fileCSV,outputDirectory);
		}
		
		logFile(logFileDirectory+"\\logFile.log", "Estableciendo directorio de salida...");
	}


	public static void findCsvFiles(File carpeta, String extension) {
		for (File ficheroEntrada : carpeta.listFiles()) {
//			if (ficheroEntrada.isDirectory()) {
//				findCsvFiles(ficheroEntrada, extension);
//			} else {
				String fileName = ficheroEntrada.getName();

				if (fileName.endsWith(extension)) {
					System.out.println(fileName);
					fileCount += 1;
					mainDirectory = carpeta+"\\"+fileName;
				}	           
			}
	}
	
	
	public static boolean checkIfDirectory(File ruta) {
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
	
	
    private static String[] getLabels(BufferedReader tag) throws IOException {
    	// Obtenemos todas las estiquetas del archivo que se ha introducido
    	String label = null;
    	String labels = "";
    	while ((label = tag.readLine()) != null) {
    		labels += label + ",";
    	}
		return labels.split(",");
	}
    
    private static void xmlConverter(File tagFile, File dataFile, File outputFile) throws IOException {
		System.out.println("Starting conversion... Please wait");
    	logFile(logFileDirectory+"\\logFile.log", "Starting Conversion");
		
		BufferedReader labelsReader = new BufferedReader(new FileReader(tagFile));
		BufferedReader dataReader = new BufferedReader(new FileReader(dataFile));
		BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFile));
		
		// Escribe la etiqueta raiz
		outputWriter.write(String.format("<%s>", "arrel") + "\n");
		
		String[] labels = getLabels(labelsReader);
		String data_line = null;
		int nr_line = 0;
		String text_line = ""; 
		String element = "";
		
		while ((data_line = dataReader.readLine()) != null) {
			// Recoge la informacion del csv
			String[] data = data_line.split(",\\s?");
			
			int max = data.length;
			if (labels.length > max) {
				max = labels.length;
			}
			
			
			String elements = "";
			for (int i = 0; i < max; i++) {
				String curdata = "";
				try {
					curdata = data[i];
				} catch(Exception e) {}
				curdata = data[i];
				
				String curlabel = "altre";
				try {
					curlabel = labels[i];
				} catch(Exception e) {}
				 
				elements += "\t" + "\t" + String.format("<%s>", curlabel);
				elements += curdata + String.format("</%s>\n", curlabel);
			}
			
			element = "elem nr=\"" + ++nr_line + "\"";
			text_line = "\t" + String.format("<%s>", element) + "\n";
			text_line += elements + "\t" + (String.format("</%s>", "elem") + "\n");
			outputWriter.write(text_line);
		}
		
		// Escribimos la etiqueta raiz de cierre
		outputWriter.write(String.format("</%s>", "arrel"));
		
		logFile(logFileDirectory+"\\logFile.log", "Confersion finished correctly");
		System.out.println("Conversion Finished!!\n");
	}
}
