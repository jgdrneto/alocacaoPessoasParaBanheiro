package entidades;

import algoritmos.AlgoritmoSincronizacao;

public class Pessoa implements Runnable{
    
    private int id;
    private String nome;
    private char sexo;
    private int tempoDeUso;
    
    private AlgoritmoSincronizacao algSinc;
    
    public Pessoa(int nId, String nNome, char nSexo, int nTempoDeUso) {
        
        this.id = nId;
        this.nome = nNome;
        this.sexo = nSexo;
        this.tempoDeUso = nTempoDeUso;
    }
       
    public void setAlgSinc(AlgoritmoSincronizacao nAlgSinc) {
        this.algSinc = nAlgSinc;
    }

    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nNome) {
        this.nome = nNome;
    }
    
    public int getTempoDeUso() {
        return this.tempoDeUso;
    }
    
    public void setTempoDeUso(int nTempoDeUso) {
        this.tempoDeUso = nTempoDeUso;
    }
    
    public int getId() {
        return this.id;
    }
    
    public char getSexo() {
        return this.sexo;
    }

    @Override
    public void run() {
        algSinc.addPessoa(this);
        
        try {
            
            while(this.tempoDeUso>0){
                this.tempoDeUso--;
                Thread.sleep(1000);
            }
            
            algSinc.removerPessoa(this);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.id +"-"+ this.nome;
    }
    
}
