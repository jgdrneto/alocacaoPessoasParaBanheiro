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

    @Override
    public void addPessoa(Pessoa p) {
        
        try {
            semaforo.acquire();
            
            if(!banheiro.estarCheio()){
                if(banheiro.getPessoas().isEmpty() || p.getSexo()==banheiro.getPessoas().get(0).getSexo()){
                    banheiro.getPessoas().add(p);
                    System.out.print(p.getId() + " - " + p.getNome() + " entrou no banheiro");
                    System.out.println(" \t Banheiro: " + this.banheiro.getPessoas());
                    semaforo.release();
                }else{
                    //System.out.println(p.getId() + " - " + p.getNome() + " : Pessoa de outro sexo dentro banheiro");
                    semaforo.release();
                    Thread.sleep(1000);
                    this.addPessoa(p);
                }
            }else{
                //System.out.println(p.getId() + " - " + p.getNome() + ":O banheiro está cheio");
                semaforo.release();
                Thread.sleep(1000);
                this.addPessoa(p);
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    
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