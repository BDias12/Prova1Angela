import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.Semaphore;

public class RaqueteJogador {
    public boolean direita, esquerda;
    public int x, y;
    public int largura_raquete, altura_raquete;
    public Semaphore Mutex;

    public RaqueteJogador(int x, int y) {
        this.x = x;
        this.y = y;
        this.largura_raquete = 40;
        this.altura_raquete = 5;
        Mutex = new Semaphore(1);
    }

    public void AtualizarPosicao() throws InterruptedException {

        Mutex.acquire();

        if (direita) {
            x ++;
        } else if (esquerda) {
            x --;
        }

        Mutex.release();

        if (x + largura_raquete > Jogo.LARGURA) {
            x = Jogo.LARGURA - largura_raquete;
        } else if (x < 0) {
            x = 0;
        }
    }

    public void Desenhar(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, largura_raquete, altura_raquete);
    }
}
