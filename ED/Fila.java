public class Fila<X> {
    private X[] elemento;
    private final int tamanhoInicial;
    private int head = 0, tail = 0, size = 0;

    @SuppressWarnings("unchecked")
    public Fila(int capacidade) throws Exception {
        if (capacidade <= 0)
            throw new Exception("Tamanho inválido");
        this.elemento = (X[]) new Object[capacidade];
        this.tamanhoInicial = capacidade;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        X[] novo = (X[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            novo[i] = elemento[(head + i) % elemento.length];
        }
        elemento = novo;
        head = 0;
        tail = size;
    }

    public void guardeUmItem(X x) throws Exception {
        if (x == null)
            throw new Exception("Falta o que guardar");
        if (size == elemento.length)
            resize(elemento.length * 2);
        elemento[tail] = x;
        tail = (tail + 1) % elemento.length;
        size++;
    }

    public X recupereUmItem() throws Exception {
        if (size == 0)
            throw new Exception("Nada a recuperar");
        return elemento[head];
    }

    public void removaUmItem() throws Exception {
        if (size == 0)
            throw new Exception("Nada a remover");
        elemento[head] = null;
        head = (head + 1) % elemento.length;
        size--;
        if (elemento.length > tamanhoInicial && size <= elemento.length / 4)
            resize(elemento.length / 2);
    }

    public boolean isCheia() {
        return size == elemento.length;
    }

    public boolean isVazia() {
        return size == 0;
    }

    @Override
    public String toString() {
        return size + " elemento(s)";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Fila<X> fil = (Fila<X>) obj;

        if (this.size != fil.size)
            return false;

        for (int i = 0; i < size; i++) {
            if (!elemento[(head + i) % elemento.length].equals(fil.elemento[(fil.head + i) % fil.elemento.length]))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int ret = 666 /* qualquer positivo */;

        ret = ret * 7 /* primo */ + ((Integer) (this.size)).hashCode();

        for (int i = 0; i < size; i++)
            ret = ret * 7 /* primo */ + elemento[(head + i) % elemento.length].hashCode();

        if (ret < 0)
            ret = -ret;

        return ret;
    }

    // construtor de cópia
    public Fila(Fila<X> modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Modelo ausente");

        this.tamanhoInicial = modelo.tamanhoInicial;
        this.size = modelo.size;

        // para fazer a cópia dum vetor
        // precisa criar um vetor novo, com new
        // não pode fazer this.elemento=modelo.elemento
        // pois se assim fizermos estaremos com dois
        // objetos, o this e o modelo, compartilhando
        // o mesmo vetor
        this.elemento = (X[]) new Object[modelo.elemento.length];

        for (int i = 0; i < size; i++)
            this.elemento[i] = modelo.elemento[(modelo.head + i) % modelo.elemento.length];
    }

    @Override
    public Object clone() {
        Fila<X> ret = null;

        try {
            ret = new Fila<X>(this);
        } catch (Exception erro) {
        }

        return ret;
    }
}
