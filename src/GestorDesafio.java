import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorDesafio {
    private SelectorFrases selector;
    private String fraseActual;
    private int posicionActual;
    private Map<Character, Integer> erroresPorLetra;
    private int totalTipeosCorrectos;
    private int totalTipeosIncorrectos;

    public GestorDesafio() {
        selector = new SelectorFrases();
        erroresPorLetra = new HashMap<>();
        totalTipeosCorrectos = 0;
        totalTipeosIncorrectos = 0;
    }

    public String iniciarNuevoDesafio() {
        fraseActual = selector.obtenerFraseAleatoria();
        posicionActual = 0;
        erroresPorLetra.clear();
        totalTipeosCorrectos = 0;
        totalTipeosIncorrectos = 0;
        return fraseActual;
    }

    public boolean verificarCaracter(char caracter) {
        if (posicionActual < fraseActual.length()) {
            if (caracter == fraseActual.charAt(posicionActual)) {
                totalTipeosCorrectos++;
                posicionActual++;
                return true;
            } else {
                totalTipeosIncorrectos++;
                erroresPorLetra.put(caracter, erroresPorLetra.getOrDefault(caracter, 0) + 1);
                return false;
            }
        }
        return false;
    }

    public void mostrarInformeFinal() {
        System.out.println("Informe Final de Errores:");
        erroresPorLetra.forEach((letra, errores) -> 
            System.out.println("Letra '" + letra + "': " + errores + " errores"));
        System.out.println("Total de tipeos correctos: " + totalTipeosCorrectos);
        System.out.println("Total de tipeos incorrectos: " + totalTipeosIncorrectos);

        String teclasDificiles = erroresPorLetra.entrySet().stream()
            .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
            .limit(3)
            .map(entry -> "'" + entry.getKey() + "': " + entry.getValue() + " errores")
            .collect(Collectors.joining(", "));

        System.out.println("Teclas más difíciles para el usuario: " + teclasDificiles);
    }

    public String getFraseActual() {
        return fraseActual;
    }

    public boolean verificarFrase(String frase) {
        return frase.trim().equalsIgnoreCase(fraseActual.trim());
    }
}

