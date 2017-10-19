package algoritmos;

import java.util.concurrent.Semaphore;

import entidades.Banheiro;
import entidades.Pessoa;

public class Semaforo extends AlgoritmoSincronizacao{
    
    private Semaphore semaforo;
    
    public Semaforo(Banheiro nBanheiro) {
        super(nBanheiro);
        semaforo = new Semaphore(1,true);
    }
    
    /**
     * Sobreescrita do método addPessoa(Pessoa p) da interface AlgoritmoSincronizacao
     */
    @Override
    public boolean addPessoa(Pessoa p) {
        
        try {
            semaforo.acquire();
            
            if(!banheiro.estarCheio()){
                if(banheiro.getPessoas().isEmpty() || p.getSexo()==banheiro.getPessoas().get(0).getSexo()){
                    banheiro.getPessoas().add(p);
                    System.out.print(p.getId() + " - " + p.getNome() + " entrou no banheiro");
                    System.out.println(" \t Banheiro: " + this.banheiro.getPessoas());
                    semaforo.release();
                    return true;
                }else{
                    //Caso o banheiro tenha alguem de sexo distinto da pessoa atual
                    //System.out.println(p.getId() + " - " + p.getNome() + " : Existe uma pessoa de outro sexo dentro do banheiro");
                    semaforo.release();
                }
            }else{
                //Caso o banheiro esteja cheio
                //System.out.println(p.getId() + " - " + p.getNome() + ": O banheiro está cheio");
                semaforo.release();
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
        
    }
    
    /**
     * Sobreescrita do método removerPessoa(Pessoa p) da interface AlgoritmoSincronizacao
     */
    public void removerPessoa(Pessoa p){
        
        try {
            semaforo.acquire();
            
            banheiro.getPessoas().remove(p);
            System.out.print(p.getId() + " - " + p.getNome() + " saiu do banheiro");
            System.out.println(" \t Banheiro: " + this.banheiro.getPessoas());
            
            semaforo.release();    
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
    }
   
}
