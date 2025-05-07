import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Comida {

    public static int posicao_x;
    public static int posicao_y;
    Random random;

    public Comida (){
        random = new Random();

    }

    public void CriarNovaPosicao(){

        posicao_x = random.nextInt (Jogo.LARGURA_TELA / Jogo.TAMANHO_BLOCO) * Jogo.TAMANHO_BLOCO;
        posicao_y = random.nextInt (Jogo.ALTURA_TELA / Jogo.TAMANHO_BLOCO) * Jogo.TAMANHO_BLOCO;



    }

    public void Desenhar (Graphics g){
        
        ImageIcon imageIcon = new ImageIcon("C://Users//Pichau//Downloads//Comida.png");
        Image image = imageIcon.getImage();
        g.drawImage (image, posicao_x, posicao_y, null);

    }
    
}
