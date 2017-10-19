package algoritmos;

import entidades.Banheiro;
import entidades.Pessoa;

public abstract class AlgoritmoSincronizacao {
    
    Banheiro banheiro;
    
    /**
     * Construtor da classe
     * 
     * @param nBanheiro Banheiro a ser controlado
     */
    public AlgoritmoSincronizacao(Banheiro nBanheiro) {
        banheiro = nBanheiro;
    }
    
    /**
     * Adiciona a pessoa ao banheiro
     * 
     * @param p Pessoa que deseja entrar no banheiro
     * @return  Se a pessoa entrou no banheiro ou não
     */
    public abstract boolean addPessoa(Pessoa p);
    
    /**
     * Remove a pessoa ao banheiro
     * 
     * @param p Pessoa que deseja sair do banheiro
     */
    public abstract void removerPessoa(Pessoa p);
}
