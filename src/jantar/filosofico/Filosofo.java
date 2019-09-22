package jantar.filosofico;

import java.util.Random;

/**
 *
 * @author aleph
 */
public class Filosofo implements Runnable {

    public static int TEMPO_MAXIMO_PENSANDO = 5000;
    public static int TEMPO_MAXIMO_COMENDO = 3000;

    private Random rand;
    private int id;
    private int qtdGarfadas;
    private Garfo primeiroGarfo, segundoGarfo;

    public Filosofo(int id, Garfo esquerda, Garfo direita) {
        this.id = id;
        if (esquerda.getId() < direita.getId()) {
            primeiroGarfo = esquerda;
            segundoGarfo = direita;
        } else {
            primeiroGarfo = direita;
            segundoGarfo = esquerda;
        }
        rand = new Random();
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando");
        int tempoPensando = rand.nextInt(TEMPO_MAXIMO_PENSANDO);
        Thread.sleep(tempoPensando);
    }

    private void pegarGarfos() throws Exception {
        System.out.println("Filósofo " + id + " está pegando os garfos");
        primeiroGarfo.segurar(this);
        segundoGarfo.segurar(this);
    }

    private void soltarGarfos() throws Exception {
        System.out.println("Filósofo " + id + " está soltando os garfos");
        primeiroGarfo.soltar(this);
        segundoGarfo.soltar(this);
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está comendo");
        int tempoComendo = rand.nextInt(TEMPO_MAXIMO_COMENDO);
        qtdGarfadas++;
        Thread.sleep(tempoComendo);
    }

    public int getId() {
        return id;
    }

    public int getQtdGarfadas() {
        return qtdGarfadas;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pegarGarfos();
                comer();
                soltarGarfos();
                pensar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
