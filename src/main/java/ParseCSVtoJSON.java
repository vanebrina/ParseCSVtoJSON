import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseCSVtoJSON {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario el nombre completo del archivo CSV
        System.out.print("Ingrese la ruta completa del archivo CSV: ");
        String csvFilePath = scanner.nextLine();

        // Verificar si el archivo CSV existe
        File csvFile = new File(csvFilePath);
        if (!csvFile.exists() || !csvFile.isFile()) {
            System.out.println("El archivo CSV especificado no existe.");
            return;
        }

        try {
            List<Estudiante> estudiantes = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String line;

            // Leer la primera línea para obtener los nombres de las columnas
            String[] columnNames = reader.readLine().split(",");

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                // Verificar que haya al menos 3 valores (id, nombre, apellido)
                if (values.length >= 3) {
                    Estudiante estudiante = new Estudiante(values[0], values[1], values[2]);
                    estudiantes.add(estudiante);
                }
            }

            reader.close();

            // Crear un arreglo JSON a partir de la lista de estudiantes
            JSONArray jsonArray = new JSONArray();
            for (Estudiante estudiante : estudiantes) {
                JSONObject studentObject = new JSONObject();
                studentObject.put("id", estudiante.getId());
                studentObject.put("nombre", estudiante.getNombre());
                studentObject.put("apellido", estudiante.getApellido());
                jsonArray.add(studentObject);
            }

            // Obtener la ruta y el nombre del archivo sin extensión
            String[] parts = csvFilePath.split("\\.");
            String jsonFilePath = parts[0] + ".json";

            FileWriter jsonFileWriter = new FileWriter(jsonFilePath);

            // Usar el método writeJSONString para escribir el JSON con indentación
            jsonArray.writeJSONString(jsonFileWriter);
            jsonFileWriter.close();

            System.out.println("Archivo JSON creado exitosamente en: " + jsonFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

