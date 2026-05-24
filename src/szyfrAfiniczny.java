public class szyfrAfiniczny {
    public static void main(String[] args) {

        /*-----------------------UWAGA---------------------------
        * Pierwsza wersja kodu była wykonywana w main, bez pętli for (int n : dlugosc)
        * Dodałam funkcję szyfrAfiniczny, która jest wywoływana w pętli for,
        * dzięki czemu nie muszę już ręcznie wpisywać długości tekstu.
        * Wyniki po zmianie kodu odrobinę różnią się od tych, które są zapisane w Excelu
        * --------------------------------------------------------*/

        System.out.println("Klucz: 3, 5");

        for (int i = 0; i < 1000; i++) {
            String wyrazTest = "abcdefghij".repeat(10).toUpperCase();
            szyfrAfiniczny(wyrazTest, 3, 5);
        }

        int[] dlugosc = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000}; //Długość wyrazu

        for (int n : dlugosc) {
            String wyraz = "abcdefghij".repeat(n).toUpperCase(); //Wyraz ma początkowo 10 znaków i jest mnożony przez długość tablicy
            System.out.println("Długość wyrazu: " + wyraz.length());
            //String zaszyfrowany = "";

            Runtime runtime = Runtime.getRuntime();
            runtime.gc(); //Czyszczenie pamięci
            long pamiecPrzed = runtime.totalMemory() - runtime.freeMemory(); //Pomiar pamięci i czasu przed szyfrowaniem
            long start = System.nanoTime();

            String zaszyfrowany = szyfrAfiniczny(wyraz, 3, 5); //Wywołanie szyfrowania

            long stop = System.nanoTime(); //Pomiar pamięci i czasu po szyfrowaniu
            long pamiecPo = runtime.totalMemory() - runtime.freeMemory();

            double wynikPamiecMB = (pamiecPo - pamiecPrzed) / (1024.0 * 1024.0); //Zamiana na megabajty
            double czasMS = (stop - start) / 1000000.0; //Zamiana na milisekundy

            /*System.out.println("Przed szyfrowaniem: " + wyraz);
            System.out.println("Po szyfrowaniu: " + zaszyfrowany);*/
            System.out.println(("Czas: " + czasMS + " ms").replace('.', ','));
            System.out.println(("Pamięć: " + wynikPamiecMB + " MB").replace('.', ','));
        }

    }
    public static String szyfrAfiniczny(String tekst, int a, int b) {
        StringBuilder zaszyfrowany = new StringBuilder(tekst.length());
        for (int i = 0; i < tekst.length(); i++) { //Przejście przez każdy znak tekstu
            int x = tekst.charAt(i) - 'A';
            int wynik = (a * x + b) % 26; //Szyfrowanie według klucza
            //zaszyfrowany += (char)(wynik + 'A');
            zaszyfrowany.append((char) (wynik + 'A')); //Zamiana z int na char i dodanie do Stringa
        }
        return zaszyfrowany.toString();
    }
}