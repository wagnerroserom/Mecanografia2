
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InterfazDesafio extends JFrame {
    private JTextField campoEntrada;
    private JButton botonVerificar;
    private JLabel etiquetaFrase, etiquetaEstado;
    private GestorDesafio gestor;
    private TecladoPersonalizado teclado;

    public InterfazDesafio() {
        gestor = new GestorDesafio();
        configurarComponentes();
    }

    private void configurarComponentes() {
        setTitle("Desafío de Recuerdo de Frases");
        setSize(700, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        campoEntrada = new JTextField(20);
        botonVerificar = new JButton("Verificar");
        etiquetaFrase = new JLabel("Presiona 'Iniciar Desafío' para comenzar", SwingConstants.CENTER);
        etiquetaEstado = new JLabel("", SwingConstants.CENTER);

        botonVerificar.addActionListener(this::verificarFrase);

        JPanel panelNorte = new JPanel(new GridLayout(2, 1));
        panelNorte.add(etiquetaFrase);
        panelNorte.add(etiquetaEstado);

        teclado = new TecladoPersonalizado(campoEntrada, this);

        add(panelNorte, BorderLayout.NORTH);
        add(campoEntrada, BorderLayout.CENTER);
        add(teclado, BorderLayout.SOUTH);
        add(botonVerificar, BorderLayout.EAST);

        iniciarNuevoDesafio();
    }

    private void verificarFrase(ActionEvent e) {
        String intento = campoEntrada.getText();
        boolean correcto = gestor.verificarFrase(intento.trim());
        etiquetaEstado.setText(correcto ? "¡Correcto!" : "Incorrecto. Intenta de nuevo.");

        if (correcto) {
            gestor.mostrarInformeFinal();
            JOptionPane.showMessageDialog(this, "Desafío completado.\nVerifica la consola para el informe de errores y el total de tipeos.", "Informe Final", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void procesarCaracter(String caracter) {
        String textoActual = campoEntrada.getText();
        String frase = gestor.getFraseActual();

        boolean esCaracterCorrecto = gestor.verificarCaracter(caracter.charAt(0));

        if (textoActual.length() < frase.length() && esCaracterCorrecto) {
            campoEntrada.setText(textoActual + caracter);
        }

        if (campoEntrada.getText().trim().equalsIgnoreCase(frase.trim())) {
            verificarFrase(null);
        }
    }

    private void iniciarNuevoDesafio() {
        String frase = gestor.iniciarNuevoDesafio();
        etiquetaFrase.setText(frase);
        campoEntrada.setText("");
    }
}
