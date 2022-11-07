import java.util.Locale;

public class Game {
    public static boolean prufen = false;
    public static int leben = 8;
    public static final char[] wort = {'P', 'R', 'O', 'G', 'R', 'A', 'M', 'M'};
    public static char[] ausgeben = new char[wort.length];
    public static char[] rate;
    private String raten;

    public Game(String raten) {

        this.raten = raten;
            prufen = false;
            rate = raten.toCharArray();
            printArray(check(rate, wort));
            ausgeben = check(rate, wort);
            leben = draw(leben);

    }


        public static void printArray(char[] arr){


            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]);
            }
        }
        public static char[] check ( char[] raten, char[] wort){

            for (int i = 0; i < raten.length; i++) {
                for (int j = 0; j < wort.length; j++) {
                    if (raten[i] != wort[j]) {
                        if (ausgeben[j] != wort[j]) {
                            ausgeben[j] = '_';
                        }

                    } else {
                        ausgeben[j] = raten[i];
                        prufen = true;
                    }
                }
            }
            return ausgeben;
        }

        public static int draw ( int leben){
            if (prufen == false) {
                System.out.println(galgenmannchen(leben));
                System.out.println("\nSie haben falsch erraten!");
                leben--;
            }
            return leben;
        }
        public static void verloren ( int leben){
            if (leben == 0) {
                System.out.println("Du bist verloren! :(");

            }

        }
        public static String galgenmannchen ( int lives){
            String[] mannchen = new String[9];
            mannchen[0] = ("\n|--------|" +
                    "\n|       (_)" +
                    "\n|      --|--" +
                    "\n|       / )" +
                    "\n---");
            mannchen[1] = ("\n|--------|" +
                    "\n|       (_)" +
                    "\n|      --|--" +
                    "\n|       /" +
                    "\n---");
            mannchen[2] = ("\n|--------|" +
                    "\n|       (_)" +
                    "\n|      --|--" +
                    "\n|" +
                    "\n---");
            mannchen[3] = ("\n|--------|" +
                    "\n|       (_)" +
                    "\n|        |" +
                    "\n|" +
                    "\n---");
            mannchen[4] = ("\n|--------|" +
                    "\n|       (_)" +
                    "\n|" +
                    "\n|" +
                    "\n---");
            mannchen[5] = ("\n|--------|" +
                    "\n|" +
                    "\n|" +
                    "\n|" +
                    "\n---"
            );
            mannchen[6] = ("\n|--------|" +
                    "\n|" +
                    "\n|" +
                    "\n|" +
                    "\n---");
            mannchen[7] = ("\n|" +
                    "\n|" +
                    "\n|" +
                    "\n|" +
                    "\n---");
            mannchen[8] = ("\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n---");
            return mannchen[lives];
        }



}
