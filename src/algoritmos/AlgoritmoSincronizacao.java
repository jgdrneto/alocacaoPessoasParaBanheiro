package algoritmos;

import entidades.Banheiro;
import entidades.Pessoa;

public abstract class AlgoritmoSincronizacao {
    
    Banheiro banheiro;
    
    public AlgoritmoSincronizacao(Banheiro nBanheiro) {
        banheiro = nBanheiro;
    }

    public abstract void addPessoa(Pessoa p);
    
    public abstract void removerPessoa(Pessoa p);
}
