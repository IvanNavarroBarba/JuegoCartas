import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class FrmJuego extends JFrame {

    private JPanel pnlJugador1, pnlJugador2;
    private JTabbedPane tpJugadores;
    private JTextField txtBarajas;

    // metodo constructor
    public FrmJuego() {
        setSize(505, 343);
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblBarajas = new JLabel("Cantidad de barajas: ");
        lblBarajas.setBounds(10, 15, 120, 25);
        add(lblBarajas);

        txtBarajas = new JTextField();
        txtBarajas.setBounds(135, 15, 85, 25);
        add(txtBarajas);

        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 60, 100, 25);
        add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 60, 100, 25);
        add(btnVerificar);

        // agregar un conjunto de pestañas
        tpJugadores = new JTabbedPane();
        tpJugadores.setBounds(10, 95, 470, 200);
        add(tpJugadores);

        // crear el panel para el JUGADOR 1
        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(11, 120, 69));
        tpJugadores.add("Martín Estrada", pnlJugador1);

        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(13, 146, 205));
        tpJugadores.add("Raúl Vidal", pnlJugador2);

        // eventos
        btnRepartir.addActionListener(evento -> {
            repartir();
        });

        btnVerificar.addActionListener(evento -> {
            verificar();
        });

        /*
         * btnVerificar.addActionListener(new ActionListener(){
         * public void actionPerformed(ActionEvent evento){
         * verificar();
         * }
         * });
         */

    }

    private Jugador jugador1 = new Jugador();
    private Jugador jugador2 = new Jugador();

    private void repartir() {

        int cantidadBarajas;

        try {
            cantidadBarajas = Integer.parseInt(txtBarajas.getText());

            if (cantidadBarajas <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad de barajas debe ser mayor que 0");
                return;
            }

        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese una cantidad de barajas válida");
            return;
        }

        int[] usadas = new int[52];

        jugador1.repartir(cantidadBarajas, usadas);
        jugador1.mostrar(pnlJugador1);

        jugador2.repartir(cantidadBarajas, usadas);
        jugador2.mostrar(pnlJugador2);
    }

    
    private void verificar() {
    String mensaje = "";

    switch (tpJugadores.getSelectedIndex()) {

        case 0:
            mensaje = jugador1.getGrupos() + "\n"
                    + jugador1.getEscaleras() + "\n"
                    + "Puntaje: " + jugador1.getPuntaje();
            break;

        case 1:
            mensaje = jugador2.getGrupos() + "\n"
                    + jugador2.getEscaleras() + "\n"
                    + "Puntaje: " + jugador2.getPuntaje();
            break;
    }

    if (!mensaje.isEmpty()) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    if (tpJugadores.getSelectedIndex() == 1) {

        int puntaje1 = jugador1.getPuntaje();
        int puntaje2 = jugador2.getPuntaje();

        if (puntaje1 < puntaje2) {

            JOptionPane.showMessageDialog(null,"🏆 Gana el jugador Martín Estrada\n\n¡Felicitaciones!","Ganador",JOptionPane.INFORMATION_MESSAGE);

        } else if (puntaje2 < puntaje1) {

            JOptionPane.showMessageDialog(null,"🏆 Gana el jugador Raúl Vidal\n\n¡Felicitaciones!","Ganador",JOptionPane.INFORMATION_MESSAGE);
                    

        } else {

            JOptionPane.showMessageDialog(null, "🤝 ¡Tenemos un empate!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

}