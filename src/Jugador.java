import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN = 10;
    private final int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar (JPanel pnl) {
        pnl.removeAll();
        pnl.setLayout(null);
        int posicion = MARGEN + TOTAL_CARTAS * DISTANCIA;
        for (Carta carta : cartas){
            posicion-=DISTANCIA;
            carta.mostrar(pnl,posicion,MARGEN);
            
        }
        pnl.repaint();
    }

    public String getGrupos() {
        String resultado = "No se encontraron grupos";

        int[] contadores = new int[NombreCarta.values().length];
        boolean hayGrupos = false;
        for (Carta carta : cartas) {
            int posicion = carta.getNombre().ordinal();
            contadores[posicion]++;
            if (!hayGrupos && contadores[posicion] >= 2) {
                hayGrupos = true;
            }
        }

        if (hayGrupos) {
            resultado = "Se encontraron los siguientes grupos:\n";
            // for (int contador : contadores) {
            for (int i = 0; i < contadores.length; i++) {
                // if (contador >= 2) {
                if (contadores[i] >= 2) {
                    resultado += Grupo.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";
                }
            }
        }
        return resultado;
    }

}
