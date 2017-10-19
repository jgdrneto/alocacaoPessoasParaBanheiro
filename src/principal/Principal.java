package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmos.AlgoritmoSincronizacao;
import algoritmos.Monitor;
import algoritmos.Semaforo;
import entidades.Banheiro;
import entidades.Pessoa;

public class Principal {
    
    /**
     * Executa o algoritmo de alocação do banheiro
     * 
     * @param algSinc Algoritmo de sincronização que será utilizado
     */
    private static void start(AlgoritmoSincronizacao algSinc){
        
        Random random = new Random();
        
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        
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
        
        ArrayList<Thread> threads = new ArrayList<Thread>();
        
        //Colocando o algoritmo de sicronização para cada objeto Pessoa
        for(Pessoa p : pessoas){
            
            p.setAlgSinc(algSinc);
            
            threads.add(new Thread(p));
        }
        
        for(Thread t : threads){
            t.start();
        }
        
    }
    
    /**
     * Instancia o algoritmo de sincronização apartir do seu nome
     * 
     * @param algSinc   Nome do algoritmo de sincronização que será utilizado   
     * @param banheiro  Banheiro a ser controlado
     * @return          Algoritmo de sincronização a ser utilizado
     */
    private static AlgoritmoSincronizacao obterAlgoritmo(String algSinc, Banheiro banheiro){
        if(algSinc.equals("Semaforo")){
           return  new Semaforo(banheiro);
        }else if(algSinc.equals("Monitor")){
           return new Monitor(banheiro);
        }else{
           return null;
        }
    }
    
    /**
     * Pequeno tutorial com instruções para execução do programa
     */
    private static void instrucoes(){
        System.out.println("Informe os argumentos corretamente");
        System.out.println("Sintaxe: <Nome do .jar> <número natural> <nome do algoritmo de sincronização>");
        System.out.println("Em que: ");
        System.out.println("<Nome do .jar>  : nome do Arquivo .jar do projeto");
        System.out.println("<número natural>  : Campo opcional para informar quantidade máximas de pessoas dentro do banheiro");
        System.out.println("<nome do algoritmo de sincronização>  : Nome de um dos dois algoritmos de sicronização utilizados: Semaforo ou Monitor");
        System.out.println("Exemplos: ");
        System.out.println("1º Ex: ./alocacaoBanheiro 3 Semaforo");
        System.out.println("2º Ex: ./alocacaoBanheiro Monitor");
    }
    
    /**
     * Método principal
     * 
     * @param args Argumentos a serem inseridos no terminal
     */
    public static void main(String[] args) {
        
        //Se forem informados argumentos de entrada no terminal
        if(args.length>0){
            
            //Capacidade padrão adotada caso não seja informada
            int capacidadeMaxima = 3;
            
            AlgoritmoSincronizacao algSinc;
            
            Banheiro banheiro;
            
            switch(args.length){
                //Caso forem 2 argumentos informados
                case 2:
                    
                    capacidadeMaxima = Integer.parseInt(args[0]);
                    
                    banheiro = new Banheiro(capacidadeMaxima);
                   
                    algSinc = obterAlgoritmo(args[1], banheiro);
                    
                    if(algSinc!=null){
                        start(algSinc);
                    }else{
                        System.out.println("Verifique o nome do algoritmo informado");
                      //Imprime as instruções caso o algoritmo de nome informado não foi implementado
                        instrucoes();
                    }
                    
                break;
                //Caso for 1 argumento
                case 1:
                    
                    banheiro = new Banheiro(capacidadeMaxima);
                    
                    //Obtendo qual algoritmo de sincronização será utilizado
                    algSinc = obterAlgoritmo(args[0], banheiro);
                    
                    if(algSinc!=null){
                        start(algSinc);
                    }else{
                        System.out.println("Verifique o nome do algoritmo informado");
                        //Imprime as instruções caso o algoritmo de nome informado não foi implementado
                        instrucoes();
                    }
                    
                break;
                default:
                    System.out.println("Verifique o número de parâmetros");
                    //Imprime as instruções caso o número de parâmetros esteja incorreto
                    instrucoes();
                break;    
            }
            
        }else{
            
        }
    }     
}
