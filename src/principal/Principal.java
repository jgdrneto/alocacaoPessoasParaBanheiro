package principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import algoritmos.AlgoritmoSincronizacao;
import algoritmos.Semaforo;
import entidades.Banheiro;
import entidades.Pessoa;

public class Principal {

    public static void main(String[] args) {
                
        List<Pessoa> pessoas = Collections.synchronizedList(new ArrayList<Pessoa>());
        
        Random random = new Random();
        
        pessoas.add(new Pessoa(1,"João", 'M',random.nextInt(15)));
        pessoas.add(new Pessoa(2,"Miguel", 'M',random.nextInt(15)));
        pessoas.add(new Pessoa(3,"Pedro", 'M',random.nextInt(15)));
        pessoas.add(new Pessoa(4,"Maria", 'F',random.nextInt(15)));
        pessoas.add(new Pessoa(5,"Lucia", 'F',random.nextInt(15)));
        pessoas.add(new Pessoa(6,"Lara", 'F',random.nextInt(15)));
        pessoas.add(new Pessoa(7,"Cleiton", 'M',random.nextInt(15)));
        pessoas.add(new Pessoa(8,"Caio", 'M',random.nextInt(15)));
        pessoas.add(new Pessoa(9,"Natan", 'M',random.nextInt(15)));
        pessoas.add(new Pessoa(10,"Inara", 'F',random.nextInt(15)));
        pessoas.add(new Pessoa(11,"Joana", 'F',random.nextInt(15)));
        pessoas.add(new Pessoa(12,"Lucas", 'M',random.nextInt(15)));
        
        Banheiro banheiro = new Banheiro(4);
        
        AlgoritmoSincronizacao algSinc = new Semaforo(banheiro);
        
        ArrayList<Thread> threads = new ArrayList<Thread>();
        
        //Colocando o algoritmo de sicronização para cada pessoa
        for(Pessoa p : pessoas){
            
            p.setAlgSinc(algSinc);
            
            //Thread t = new Thread(p);
                        
            threads.add(new Thread(p));
        }
        
        for(Thread t : threads){
            t.start();
        }
    }

}
