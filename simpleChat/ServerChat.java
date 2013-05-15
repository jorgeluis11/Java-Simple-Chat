// Students names: Edward Flores (841-08-2322) 
//					& Omar Ortiz (841-07-5918)
// Class: SICI 4037 
// Sockets Project

package simpleChat;

import java.io.*;
import java.net.*;

class main{
    public static void main(String[] args) throws Exception
    {
       
                ServerSocket serverSocket = new ServerSocket(3000);
              
                // Connect to player 1
                Socket player1 = serverSocket.accept();
                System.out.println( ": Player 1 joined session \n");
                System.out.println( ": Player 1's IP Address: \n");

                // Notify that the player is Player 1.
                new PrintWriter(player1.getOutputStream(), true).println("User 1 Conected");

                // Connect to player 2
                Socket player2 = serverSocket.accept();
                System.out.println(" Player 2 joined session \n");
          
                new PrintWriter(player2.getOutputStream(), true).println("User 1 Conected");
                // Notify that the player is Player 2.
                new PrintWriter(player2.getOutputStream(), true).println("User 2 Conected");

                // Create a new thread for this session of two players.
                HandleASession task = new HandleASession(player1, player2);

    }		
}
	


// Define the thread class for handling a new session for two players.
class HandleASession {
	
	protected Socket player1;
	protected Socket player2;

	public HandleASession(final Socket player1, final Socket player2) throws IOException 
        {
		this.player1 = player1;
		this.player2 = player2;

                final DataInputStream fromPlayer1 = new DataInputStream(player1.getInputStream());
                final PrintWriter toPlayer1 = new PrintWriter(player1.getOutputStream(), true);
                
                final DataInputStream fromPlayer2 = new DataInputStream(player2.getInputStream());
                final PrintWriter toPlayer2 = new PrintWriter(player2.getOutputStream(), true);
               new Thread()
                {
                    {
                        this.start();
                    }
                    @Override
                    public void run()
                    {
                        
                        
                       
                            while(true) 
                            { 
                                try{
                                    String message = fromPlayer1.readLine();	

                                    if (!message.isEmpty()) 
                                    {
                                            toPlayer1.println("User1: "+message);
                                            toPlayer2.println("User1: "+message);
                                    }
                                }catch(IOException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                    };
               
               
                new Thread()
                {
                    {
                        
                        this.start();
                    }
                    @Override
                    public void run()
                    {
                            while(true) 
                            { 
                                try{
                                    String message = fromPlayer2.readLine();	

                                    if (!message.isEmpty()) 
                                    {
                                            toPlayer1.println("User2: "+message);
                                            toPlayer2.println("User2: "+message);
                                    }
                                }catch(IOException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                    };
        }
}

class User1
{
    
    
}

