package jlwcrews.flaggame;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//This class is the server object, running in a separate thread
public class FlagGameServer implements Runnable {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket socket;
    private FlagGameServerController fgsc;
    private ArrayList<Flag> flags;

    //fgsc allows me to update the GUI elements in the controller
    public FlagGameServer(FlagGameServerController fgsc){
        this.fgsc = fgsc;
    }

    //required when implementing Runnable, starts the server
    @Override
    public void run() {
        startServer();
    }

    //start the server
    private void startServer() {
        //set up the server to accept messages
        try {
            server = new ServerSocket(9898, 100);
            while (true) {
                try {
                    waitForConnection();
                    setupStreams();
                    whileConnected();
                } catch (EOFException eof) {
                    fgsc.showMessage("Server ended the connection.");
                } finally {
                    closeConnection();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //while waiting for a connection
    private void waitForConnection() throws IOException {
        fgsc.showMessage("Waiting for connection");
        socket = server.accept();
        fgsc.showMessage("Connected to " + server.getInetAddress().getHostName());
    }

    //once the connection is established, set up the streams
    private void setupStreams() throws IOException {
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            output.flush();
            input = new ObjectInputStream(socket.getInputStream());
            fgsc.showMessage("Streams set up");
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    //while the user is connected, read the input stream and check for game mode
    //once the game difficulty request is received, creates a new instance of the
    //database handler class, and once it receives the populated flags arraylist
    //serializes the arraylist through the socket to the client
    private void whileConnected() throws IOException {
        do{
            try {
                String message = input.readObject().toString();
                fgsc.showMessage("Game difficulty request: " + message);
                FlagData flagData = new FlagData();
                flags = flagData.returnFlags(message);
                output.writeObject(flags);
                fgsc.showMessage(flags.size() + " flags sent");

            } catch (ClassNotFoundException cnfException) {
                fgsc.showMessage("Invalid input");
            }
        }while(!input.equals("CLIENT-END"));
    }

    //clean up when closing the connection
    private void closeConnection() {
        fgsc.showMessage("Closing connection");
        try {
            output.close();
            input.close();
            socket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}