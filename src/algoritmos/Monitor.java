package algoritmos;

import entidades.Banheiro;
import entidades.Pessoa;

public class Monitor extends AlgoritmoSincronizacao{
    
    /**
     * Construtor da classe
     * 
     * @param nBanheiro Banheiro a ser controlado
     */
    public Monitor(Banheiro nBanheiro) {
        super(nBanheiro);
    }
    
    /**
     * Sobreescrita do método addPessoa(Pessoa p) da interface AlgoritmoSincronizacao
     */
    @Override
    public synchronized boolean addPessoa(Pessoa p) {
        
        try {
            
            if(!banheiro.estarCheio()){
                if(banheiro.getPessoas().isEmpty() || p.getSexo()==banheiro.getPessoas().get(0).getSexo()){
                    
                    banheiro.getPessoas().add(p);
                    System.out.print(p.getId() + " - " + p.getNome() + " entrou no banheiro");
                    System.out.println(" \t Banheiro: " + this.banheiro.getPessoas());
                
                    notifyAll();
                
                    return true;
                }else{
                    //Caso o banheiro tenha alguem de sexo distinto da pessoa atual
                    //System.out.println(p.getId() + " - " + p.getNome() + " :  Existe uma pessoa de outro sexo dentro do banheiro");
                    wait();
                }
            }else{
                //Caso o banheiro esteja cheio
                //System.out.println(p.getId() + " - " + p.getNome() + ": O banheiro está cheio");
                wait();
            }
        
        }catch (InterruptedException e) {
            e.printStackTrace();
        }    
            
        return false;
    }
    
    /**
     * Sobreescrita do método removerPessoa(Pessoa p) da interface AlgoritmoSincronizacao
     */
    @Override
    public synchronized void removerPessoa(Pessoa p) {
        
        banheiro.getPessoas().remove(p);
        System.out.print(p.getId() + " - " + p.getNome() + " saiu do banheiro");
        System.out.println(" \t Banheiro: " + this.banheiro.getPessoas());
            
        notifyAll();    
        
    }

}
