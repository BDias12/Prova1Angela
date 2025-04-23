import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterrupcaoTeclado extends KeyAdapter{
    RaqueteJogador objetoRaqueteJogador;

    InterrupcaoTeclado(RaqueteJogador obj){
        objetoRaqueteJogador = obj;
    }

    @Override
    public void keyPressed(KeyEvent e){
        try{
            this.objetoRaqueteJogador.Mutex.acquire();
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                objetoRaqueteJogador.direita = true;
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                objetoRaqueteJogador.esquerda = true;
            }
        }   catch (InterruptedException e1){
            e1.printStackTrace();
        }
        objetoRaqueteJogador.Mutex.release();
    }
    @Override
    public void keyReleased(KeyEvent e){
        try{
            objetoRaqueteJogador.Mutex.acquire();
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                objetoRaqueteJogador.direita = false;
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                objetoRaqueteJogador.esquerda = false;
            }
        }   catch (InterruptedException e1){
            e1.printStackTrace();
        }
        objetoRaqueteJogador.Mutex.release();

    }

    @Override
    public void keyTyped(KeyEvent arg0){
        
    }
    
}