import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Dimension;
import java.util.Random;
import java.util.concurrent.Semaphore;
import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Jogo extends JPanel implements Runnable{

    public static final int LARGURA_TELA = 1300;
    public static final int ALTURA_TELA = 750;
    public static final int TAMANHO_BLOCO = 50;
    public static final int UNIDADES = LARGURA_TELA * ALTURA_TELA / (TAMANHO_BLOCO * TAMANHO_BLOCO);
    public static final int INTERVALO = 200;
    public static final String NOME_FONTE = "Ink Free";

    public boolean GameOver = false;
    Cobrinha objetoCobra;
    Comida objetoComida;
    Random random;

    public static Semaphore Mutex;

    public Jogo(){
        
        setPreferredSize (new Dimension (LARGURA_TELA, ALTURA_TELA));
        setBackground (Color.WHITE);
        setFocusable (true);

        addKeyListener (new InterrupcaoTeclado());
        objetoCobra = new Cobrinha();
        objetoComida = new Comida();
        objetoComida.CriarNovaPosicao();
        GameOver = false;
        Mutex = new Semaphore(1);

    }

    public void paintComponent (Graphics g){

        super.paintComponent (g);
        try{

            desenharTela(g);


        }catch (LineUnavailableException | IOException | UnsupportedAudioFileException e){
            e.printStackTrace();
        }

    }

    public void desenharTela (Graphics g) throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        if (GameOver == false){
            objetoComida.Desenhar(g);
            objetoCobra.Desenhar(g);

            g.setColor (Color.red);
            g.setFont(new Font (NOME_FONTE, Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            String texto = "Pontos: " + objetoCobra.QuantidadeComida;
            g.drawString (texto, (LARGURA_TELA - metrics.stringWidth(texto)) / 2, g.getFont() .getSize());

        }else{
            fimDeJogo(g);
        }

    }

    public void fimDeJogo (Graphics g) throws LineUnavailableException, IOException, UnsupportedAudioFileException{

        g.setColor (Color.red);
        g.setFont (new Font (NOME_FONTE, Font.BOLD, 75));
        FontMetrics fonteFinal = getFontMetrics(g.getFont());
        g.drawString ("Fim do Jogo. ", (LARGURA_TELA - fonteFinal.stringWidth("Fim do Jogo")) / 2, ALTURA_TELA / 2);

        File arquivo = new File ("C://Users//Pichau//Downloads//SomPerdeu.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(arquivo);
        Clip clip = AudioSystem.getClip();
        clip.open (audioInputStream);
        clip.start();

    }
    
    public void run() {
        while (GameOver == false){
            try{
                objetoCobra.andar();

            }catch (InterruptedException e1){
                e1.printStackTrace();
            }
            try{
                if (objetoCobra.alcancouComida() == true)
                objetoComida.CriarNovaPosicao();

            }catch (LineUnavailableException | IOException | UnsupportedAudioFileException e){
                e.printStackTrace();
            }
            GameOver = objetoCobra.VerificarGameOver();
            repaint();

            try{
                Thread.sleep(200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
