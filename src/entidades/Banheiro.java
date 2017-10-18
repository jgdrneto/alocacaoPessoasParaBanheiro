package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Banheiro{
    
    private List<Pessoa> pessoas = Collections.synchronizedList(new ArrayList<Pessoa>());
    private int capacidadeMaxima;
    
    public Banheiro(int nCapacidadeMaxima) {
        this.capacidadeMaxima = nCapacidadeMaxima;
    }
    
    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
    
    public List<Pessoa> getPessoas() {
        return this.pessoas;
    }
    
    public boolean estarCheio(){
        return (this.capacidadeMaxima - pessoas.size()) == 0;
    }
    
}
