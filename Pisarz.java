import java.util.Random;

public class Pisarz implements Runnable {
    private Czytelnia czytelnia;

    public Pisarz(Czytelnia czytelnia) {
        this.czytelnia = czytelnia;
    }


    public void pisze(){
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        Random random = new Random();
        while(true){
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Pisarz " + Thread.currentThread() + " przed drzwiami");
            try {
                czytelnia.chce_pisac();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Pisarz " + Thread.currentThread() + " wchodze:");
            pisze();
            System.out.println("Pisarz " + Thread.currentThread() + " Wychodzi");
            czytelnia.koniec_pisania();
            System.out.println("Pisarz " + Thread.currentThread() + " ZA drzwiami");


        }



    }
}

