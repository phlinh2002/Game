import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static String raten;
    private static char[] wort = {'P', 'R','O','G','R','A','M','M'};
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String playername;

    public  Client(Socket socket, String playername){
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.playername = playername;
        }catch(IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void sendMessage(){
        try{
            bufferedWriter.write(playername);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while(socket.isConnected()) {
                String raten = scanner.nextLine();
                bufferedWriter.write(playername + ": " + raten);


                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

        }catch(IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void listenToClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msg;

                while(socket.isConnected()){
                    try{
                        msg = bufferedReader.readLine();
                        System.out.println(msg);

                    }catch (IOException e){
                        closeEverything(socket,bufferedReader,bufferedWriter);
                    }
                }
            }
        }).start();

    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();

            }
            if (bufferedWriter != null) {
                bufferedWriter.close();

            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws IOException {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Welcome to Computermannchen version 0.1");
        System.out.println("\t\t       COMPUTERMÄNNCHEN");
        System.out.println("\nUnd so geht's:");
        System.out.println(
                "\nSie bekommen ein beliebiges Wort gegeben, welches sie selbst \ndurch das Erraten von Buchstaben versuchen müssen zu entziffern. \nSie können es aber auch mit Wörtern versuchen. :D ");
        System.out.println(
                "Aber aufgepasst!! Bei jeder falschen Eingabe, \nwird der unschuldige Roboter mehr zur Verdamnis gebracht! >_<");
        System.out.println("\nAlso geben Sie sich Mühe und retten sie den Roboter!!!!!");
        System.out.println("Geben Sie bitte Ihr Name");
        String playername = scanner.nextLine();
        System.out.println("Willkommen " + playername);
        System.out.println("Lass uns Spielen ! ");
        System.out.println("\nViel Erfolg! :)");
        System.out.println("Das gegebene Wort hat " + wort.length + " Buchstaben");
        for(int i =0; i< wort.length; i++){
            System.out.print("_");
        }
        do {
            System.out.println("\nErraten Sie mal ein Buchstabe");
            String raten = scanner.nextLine().toUpperCase();
            Game game =new Game(raten);
        } while (Game.leben > -1);


        Socket socket = new Socket("localhost", 4444);
        Client client = new Client(socket, playername);
        client.listenToClient();

        client.sendMessage();
    }
}

