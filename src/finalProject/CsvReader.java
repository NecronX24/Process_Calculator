package finalProject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {
	public static void reader(DoubleList queue, DoubleList stack, DoubleList rr, String rutaArchivo) {
	        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
	        	if (queue.getHead()!=null) {
	        		queue.setHeadNull();
	        		stack.setHeadNull();
	        		rr.setHeadNull();
	        	}
	        	String linea;
	            while ((linea = br.readLine()) != null) {
	                String[] partes = linea.split(","); // Separa los datos por comas
	                int[] datos = new int[partes.length]; // Crea un arreglo de enteros del tamaño adecuado
	                
	                for (int i = 0; i < partes.length; i++) {
	                    datos[i] = Integer.parseInt(partes[i].trim()); // Convierte cada parte a entero
	                }
	                
	                queue.insertAtEnd(datos[0], datos[1]);
	                stack.insertAtEnd(datos[0], datos[1]);
	                rr.insertAtEnd(datos[0], datos[1]);
	            }
	            
	        	
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

}
