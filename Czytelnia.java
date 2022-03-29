import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Czytelnia {

    private int liczba_pisarzy;
    private int liczba_czytelnikow;
    private ReentrantLock lock;

    Condition czytelnicy, pisarze;

    Czytelnia(){
        this.liczba_pisarzy=0;
        this.liczba_czytelnikow=0;
        this.lock= new ReentrantLock();
        this.czytelnicy = lock.newCondition();
        this.pisarze = lock.newCondition();

    }



    public  void chce_czytac() throws InterruptedException {
        lock.lock();
        if( liczba_pisarzy > 0 || lock.hasWaiters(pisarze) )
            czytelnicy.await();
        liczba_czytelnikow++;
        czytelnicy.signal();
        lock.unlock();
    }

    public  void koniec_czytania(){
        lock.lock();
            liczba_czytelnikow--;
            if(liczba_czytelnikow==0)
                pisarze.signal();
        lock.unlock();
    }


    public  void chce_pisac() throws InterruptedException {
        lock.lock();
        if(liczba_czytelnikow + liczba_pisarzy > 0)
            pisarze.await();
        liczba_pisarzy++;
        lock.unlock();
    }


    public  void koniec_pisania(){
        lock.lock();
        liczba_pisarzy--;

        if(lock.hasWaiters(czytelnicy))
            czytelnicy.signal();
        else
            pisarze.signal();

        lock.unlock();
    }


}
