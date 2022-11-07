import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String playerName;

    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.playerName = bufferedReader.readLine();
            clientHandlers.add(this);
            boardcastMessage("SERVER:" + playerName + " has entered the game!");

        } catch (IOException e) {
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    @Override
    public void run() {
        String message;

        while(socket.isConnected()) {
            try {
                message = bufferedReader.readLine();
                boardcastMessage(message);

            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void boardcastMessage(String messageToSend){
        for(ClientHandler clientHandler: clientHandlers){
            try{
                if(!clientHandler.playerName.equals(playerName));
                clientHandler.bufferedWriter.write(messageToSend);
                clientHandler.bufferedWriter.newLine();
                clientHandler.bufferedWriter.flush();
            }catch(IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        boardcastMessage("SERVER" + playerName + " has left");
    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter){
        removeClientHandler();
        try{
            if(bufferedReader != null){
                bufferedReader.close();

            }
            if(bufferedWriter != null){
                bufferedWriter.close();

            }
            if(socket !=null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

