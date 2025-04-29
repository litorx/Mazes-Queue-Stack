import java.io.*;

/**
 * A classe TxtRead é responsável por ler arquivos de texto de uma rota especificada.
 * Implementa a interface Cloneable para permitir a clonagem de instâncias.
 */
public class TxtRead implements Cloneable {
    private String route;
    private BufferedReader in;

    public TxtRead(String route) throws Exception {
        if (route == null) throw new Exception("Rota não encontrada");
        this.route = route;
        in = new BufferedReader(new FileReader(route));
    }

    public String lerUmaLinha() throws Exception {
        return in.readLine();
    }

    public String getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "Rota: " + route;
    }

    @Override
    public int hashCode() {
        int ret = 8;
        ret = ret * 7 + this.route.hashCode();
        if (ret < 0) ret = -ret;
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TxtRead txtRead = (TxtRead) obj;
        return this.route.equals(txtRead.route);
    }

    public TxtRead(TxtRead modelo) throws Exception {
        if (modelo == null) throw new Exception("Modelo ausente");
        this.route = modelo.route;
        this.in = new BufferedReader(new FileReader(this.route));
    }

    @Override
    public Object clone() {
        TxtRead ret = null;
        try {
            ret = new TxtRead(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void close() throws IOException {
        if (in != null) {
            in.close();
        }
    }

    public void reset() throws IOException {
        this.in.close();
        this.in = new BufferedReader(new FileReader(this.route));
    }
}
