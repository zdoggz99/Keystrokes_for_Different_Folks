import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    public PixHandler handler;
    public Pix pix;
    Random r = new Random();
    private Thread thread;
    private boolean running = false;
    Dimension screenSize;

    public Game(Dimension screenSize){
        handler = new PixHandler();
        this.screenSize=screenSize;
        new Display(screenSize, "Test", this);
    }
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        //Setting the background color here
        g.setColor(Color.black);
        g.fillRect(0, 0, screenSize.width, screenSize.height);

        //render things here!!!
        handler.render(g);

        g.dispose();
        bs.show();
    }

    public void tick() {
        Color color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255), r.nextInt(255));
        handler.addObject(new Pix(r.nextInt(screenSize.width),color,screenSize));
        handler.tick();
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
                if (ticks != fps) {
                    System.out.println("FPS: " + ticks);
                }
                ticks = 0;
                timer = 0;
            }

        }

        stop();
    }
}
