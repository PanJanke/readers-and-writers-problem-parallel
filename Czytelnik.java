import java.util.Random;

public class Czytelnik implements Runnable {

    private Czytelnia czytelnia;

    public Czytelnik(Czytelnia czytelnia) {
        this.czytelnia = czytelnia;
    }

    public void czytam() throws InterruptedException {
        Random random = new Random();
        Thread.sleep(random.nextInt(1000));
    }

    public void run() {
        Random random = new Random();
      while(true){


          try {
              Thread.sleep(random.nextInt(1000));
          } catch (InterruptedException e) {
              e.printStackTrace();
          }

          System.out.println("czytelnik " + Thread.currentThread() + "przed drzwiami");
          try {
              czytelnia.chce_czytac();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }

          System.out.println("czytelnik " + Thread.currentThread() + "wchodze:");

          try {
              czytam();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }

          System.out.println("czytelnik " + Thread.currentThread() + "Wychodzi");

          czytelnia.koniec_czytania();

          System.out.println("czytelnik " + Thread.currentThread() + " ZA drzwiami");


      }





    }
}
                

