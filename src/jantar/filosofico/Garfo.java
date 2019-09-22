package jantar.filosofico;

/**
 *
 * @author aleph
 */
public class Garfo {

    private int id;
    private Filosofo dono;

    public Garfo(int id) {
        this.id = id;
        this.dono = null;
    }

    public int getId() {
        return id;
    }

    public int getDonoId() {
        return (dono == null ? -1 : dono.getId());
    }

    public int getQtdGarfadasDono() {
        return (dono == null ? -1 : dono.getQtdGarfadas());
    }

    public synchronized void segurar(Filosofo f) throws Exception {
        if (f == null) {
            throw new Exception("Filósofo não pode ser nulo");
        }
        if (dono != null) {
            wait();
        }
        dono = f;
    }

    public synchronized void soltar(Filosofo f) throws Exception {
        if (f == null) {
            throw new Exception("Filósofo não pode ser nulo");
        }
        if (dono == f) {
            dono = null;
        }
        notify();
    }
}
