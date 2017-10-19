package entidades;

import algoritmos.AlgoritmoSincronizacao;

public class Pessoa implements Runnable{
    
    private int id;
    private String nome;
    private char sexo;
    private int tempoDeUso;
    
    private AlgoritmoSincronizacao algSinc;
        
    /**
     * Construtor da classe
     * 
     * @param nId           Índice de identificação de uma pessoa
     * @param nNome         Nome da pessoa
     * @param nSexo         Sexo da pessoa 
     * @param nTempoDeUso   Tempo que a pessoa irá usar o banheiro
     */
    public Pessoa(int nId, String nNome, char nSexo, int nTempoDeUso) {
        
        this.id = nId;
        this.nome = nNome;
        this.sexo = nSexo;
        this.tempoDeUso = nTempoDeUso;
    }
    
    /**
     * Insere o tipo de algoritmo de sincronização que será utilizado
     * 
     * @param nAlgSinc  Algoritmo de sincronização que será utilizado 
     */
    public void setAlgSinc(AlgoritmoSincronizacao nAlgSinc) {
        this.algSinc = nAlgSinc;
    }
    
    /**
     * Obtem o nome da pessoa
     * 
     * @return Nome da pessoa
     */
    public String getNome() {
        return this.nome;
    }
    /**
     * Insere um novo nome para a pessoa
     * 
     * @param nNome Novo nome para a pessoa
     */
    public void setNome(String nNome) {
        this.nome = nNome;
    }
    /**
     * Obtem o tempo de uso do banheiro pela pessoa
     * 
     * @return Tempo de uso do banheiro da pessoa
     */
    public int getTempoDeUso() {
        return this.tempoDeUso;
    }
    
    /**
     * Insere um novo tempo de uso de banheiro pela pessoa
     * 
     * @param nTempoDeUso   Novo tempo de uso do banheiro
     */
    public void setTempoDeUso(int nTempoDeUso) {
        this.tempoDeUso = nTempoDeUso;
    }
    
    /**
     * Obtem o id de identificação da pessoa
     * 
     * @return Id da pessoa
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Obtem o sexo da pessoa 
     * 
     * @return  O sexo da pessoa
     */
    public char getSexo() {
        return this.sexo;
    }
    
    /**
     * Ação de entrar para o banheiro
     */
    private void entrarBanheiro(){
          
        /*  
         *  Enquanto a pessoa não entra no banheiro, ela fica "esperando" por 100 milissegundos
         *  até tentar entrar no banheiro novamente
         */ 
        while(!algSinc.addPessoa(this)){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Usar o banheiro
     */
    private void usarBanheiro(){
        try {
            
            /*  
             *  Simulação do tempo da pessoa usando o banheiro
             */
            while(this.tempoDeUso>0){
                this.tempoDeUso--;
                Thread.sleep(1000);
            }    
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Saindo do banheiro
     */
    private void sairBanheiro(){
        algSinc.removerPessoa(this);
    }
    
    /**
     * Sobrescrita do método run() presente na interface Runnable
     */
    @Override
    public void run() {
        
        this.entrarBanheiro();
        
        this.usarBanheiro();
        
        this.sairBanheiro();
    }
    
    /**
     * Sobreescrita do método toString() da classe Object
     */
    @Override
    public String toString() {
        return this.id +"-"+ this.nome;
    }
    
}
