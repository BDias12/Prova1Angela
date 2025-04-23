import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Jogo extends Canvas implements KeyListener, Runnable {

    public static int LARGURA = 160;
    public static int ALTURA = 120;
    public static int ESCALA = 3;
    public BufferedImage TelaDoJogo = new BufferedImage(LARGURA,ALTURA,BufferedImage.TYPE_INT_RGB);

    public static RaqueteJogador objetoRaqueteJogador;
    public static Bola objetoBola;

    public Jogo(){
        this.setPreferredSize(new Dimension(LARGURA*ESCALA,ALTURA*ESCALA));

        objetoRaqueteJogador = new RaqueteJogador(100, ALTURA-5);
        objetoBola = new Bola(100, ALTURA/2 - 1);

        this.addKeyListener(new InterrupcaoTeclado(objetoRaqueteJogador));
    }

    public void AtualizarPosicoesObjetos() throws InterruptedException {
        objetoRaqueteJogador.AtualizarPosicao();
        objetoBola.AtualizarPosicao();
    }

    public void DesenharJogoNaTela(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = TelaDoJogo.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,LARGURA,ALTURA);
        objetoRaqueteJogador.Desenhar(g);
        objetoBola.Desenhar(g);

        g = bs.getDrawGraphics();
        g.drawImage(TelaDoJogo,0,0,LARGURA*ESCALA,ALTURA*ESCALA,null);
        bs.show();
    }
    @Override
    public void run(){
        while(true){
            try{
                AtualizarPosicoesObjetos();
            } catch (InterruptedException e1){
                e1.printStackTrace();
            }
            DesenharJogoNaTela();
            try{
                Thread.sleep(15);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void keyTyped(KeyEvent e){

    }

    public void keyPressed(KeyEvent e){

    }

    public void keyReleased(KeyEvent e){

    }
}
