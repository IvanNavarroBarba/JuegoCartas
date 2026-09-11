import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FrmJuego extends JFrame {
    
    JPanel pnlJugador1, pnlJugador2;
    private JTabbedPane tpJugadores;

    public FrmJuego(){
        setSize(500, 300);
        setTitle("Juego de cartas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        add(btnVerificar);

        // agregar un conjunto de pestañas
        JTabbedPane tpJugadores = new JTabbedPane();
        tpJugadores.setBounds(10, 45, 465, 200);
        add(tpJugadores);

        // crear el panel para el JUGADOR 1
        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(14, 150, 30));
        tpJugadores.add("Martín Estrada", pnlJugador1);

        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(12, 208, 234));
        tpJugadores.add("Raúl Vidal", pnlJugador2);

        //eventos
        btnRepartir.addActionListener(evento -> {
            repartir();
        });

        btnVerificar.addActionListener(evento -> {
            verificar();
        });

        
    }

    private Jugador jugador1 = new Jugador();
    private Jugador jugador2 = new Jugador();

     private void repartir() {

        jugador1.repartir();
        jugador1.mostrar(pnlJugador1);

        jugador2.repartir();
        jugador2.mostrar(pnlJugador2);        
    }

    private void verificar() {
        String mensaje = "";
        switch (tpJugadores.getSelectedIndex()) {
            case 0:
                mensaje = jugador1.getGrupos();
                break;
            case 1:
                mensaje = jugador2.getGrupos();
                break;
        }
        if (!mensaje.isEmpty()) {
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }

    }


