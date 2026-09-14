import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN = 10;
    private final int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir(int cantidadBarajas, int[] usadas) {

        for (int i = 0; i < TOTAL_CARTAS; i++) {

            Carta carta;

            do {
                carta = new Carta(r);
            } while (usadas[carta.getIndice() - 1] >= cantidadBarajas);

            cartas[i] = carta;

            usadas[carta.getIndice() - 1]++;
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        pnl.setLayout(null);
        int posicion = MARGEN + TOTAL_CARTAS * DISTANCIA;
        for (Carta carta : cartas) {
            posicion -= DISTANCIA;
            carta.mostrar(pnl, posicion, MARGEN);
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
                    resultado += Grupo.values()[contadores[i]] + " DE " + NombreCarta.values()[i] + "\n";
                }
            }
        }
        return resultado;
    }

    public String getEscaleras() {
        String resultado = "";

        for (Carta carta : cartas) {

            int ordinal = carta.getNombre().ordinal();
            Pinta pinta = carta.getPinta();

            boolean tieneAnterior = false;

            if (ordinal > 0) {

                int ordinalAnterior = ordinal - 1;

                for (Carta otraCarta : cartas) {

                    if (otraCarta.getNombre().ordinal() == ordinalAnterior && otraCarta.getPinta() == pinta) {
                        tieneAnterior = true;
                    }
                }
            }

            if (!tieneAnterior) {

                int cantidad = 1;
                int siguienteOrdinal = ordinal + 1;
                boolean encontrada = true;

                while (encontrada && siguienteOrdinal <= 12) {

                    encontrada = false;

                    for (Carta otraCarta : cartas) {

                        if (otraCarta.getNombre().ordinal() == siguienteOrdinal && otraCarta.getPinta() == pinta) {
                            encontrada = true;
                        }
                    }

                    if (encontrada) {
                        cantidad++;
                        siguienteOrdinal++;
                    }
                }

                if (cantidad >= 2) {
                    resultado += Grupo.values()[cantidad] + " DE " + pinta + ": Desde " + NombreCarta.values()[ordinal] + " hasta " + NombreCarta.values()[siguienteOrdinal - 1] + "\n";
                }
            }
        }

        if (resultado.isEmpty()) {
            resultado = "No se encontraron escaleras";
        }

        return resultado;
    }

    private boolean esParteDeEscalera(Carta carta) {
        int ordinal = carta.getNombre().ordinal();
        Pinta pinta = carta.getPinta();

        boolean tieneAnterior = false;
        boolean tieneSiguiente = false;

        if (ordinal > 0) {

            int ordinalAnterior = ordinal - 1;

            for (Carta otraCarta : cartas) {

                if (otraCarta.getNombre().ordinal() == ordinalAnterior && otraCarta.getPinta() == pinta) {
                    tieneAnterior = true;
                }
            }
        }

        if (ordinal < 12) {

            int ordinalSiguiente = ordinal + 1;

            for (Carta otraCarta : cartas) {

                if (otraCarta.getNombre().ordinal() == ordinalSiguiente && otraCarta.getPinta() == pinta) {
                    tieneSiguiente = true;
                }
            }
        }

        return tieneAnterior || tieneSiguiente;
    }

    public int getPuntaje() {
        int puntaje = 0;

        int[] contadores = new int[NombreCarta.values().length];

        for (Carta carta : cartas) {
            int posicion = carta.getNombre().ordinal();
            contadores[posicion]++;
        }

        for (Carta carta : cartas) {

            int posicion = carta.getNombre().ordinal();

            if (contadores[posicion] == 1 && !esParteDeEscalera(carta)) {
                int valor;
                if (carta.getNombre() == NombreCarta.AS
                        || carta.getNombre() == NombreCarta.JACK
                        || carta.getNombre() == NombreCarta.QUEEN
                        || carta.getNombre() == NombreCarta.KING) {

                    valor = 10;

                } else {
                    valor = carta.getNombre().ordinal() + 1;
                }
            puntaje += valor;
            }
        }
        return puntaje;  
    }
    
}

