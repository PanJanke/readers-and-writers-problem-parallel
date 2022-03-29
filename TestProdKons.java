public class TestProdKons {


    private static int l_czyt =10;
    private static int l_pis =10;

    public static void main(String[] args) throws InterruptedException {


        Czytelnia czytelnia = new Czytelnia();
        Thread[] czytelnicy = new Thread[l_czyt];
        Thread[] pisarze = new Thread[l_pis];

        for(int i=0;i<l_czyt;i++)
            czytelnicy[i] = new Thread(new Czytelnik(czytelnia));

        for(int i=0;i<l_pis;i++)
            pisarze[i] = new Thread(new Pisarz(czytelnia));

        for(int i=0;i<l_pis;i++)
            pisarze[i].start();

        for(int i=0;i<l_czyt;i++)
            czytelnicy[i].start();


        for(int i=0;i<l_pis;i++)
            pisarze[i].join();

        for(int i=0;i<l_czyt;i++)
            czytelnicy[i].join();


    }
}
