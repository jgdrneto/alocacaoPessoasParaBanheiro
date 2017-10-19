package entidades;

import java.util.ArrayList;
import java.util.List;

public class Banheiro{
    
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();
    private int capacidadeMaxima;
    
    /**
     * Construtor da classe
     * 
     * @param nCapacidadeMaxima Capacidade máxima de pessoas no banheiro
     */
    public Banheiro(int nCapacidadeMaxima) {
        this.capacidadeMaxima = nCapacidadeMaxima;
    }
    
    /**
     * Obtem o valor da capacidade máxima de pessoas simultaneamente no banheiro 
     * 
     * @return Capacidade máxima do banheiro
     */
    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
    /**
     * Obtem lista das pessoas que estão dentro do banheiro
     * 
     * @return Lista das pessas que estão no banheiro
     */
    public List<Pessoa> getPessoas() {
        return this.pessoas;
    }
    
    /**
     * Verifica se o banheiro está em sua capacidade máxima
     * 
     * @return Se o banheiro está cheio ou não
     */
    public boolean estarCheio(){
        return (this.capacidadeMaxima - pessoas.size()) == 0;
    }
    
}
