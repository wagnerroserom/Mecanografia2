import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SelectorFrases {
    private List<String> frases;
    private Random random;

    public SelectorFrases() {
        random = new Random();
        cargarFrasesDesdeArchivo("frases.txt");
    }

    private void cargarFrasesDesdeArchivo(String nombreArchivo) {
        frases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(nombreArchivo), StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                frases.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String obtenerFraseAleatoria() {
        if (!frases.isEmpty()) {
            return frases.get(random.nextInt(frases.size()));
        } else {
            return "No hay frases disponibles";
        }
    }
}

