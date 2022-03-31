
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{	
	

	public static void main(String[] args) throws UnknownHostException, IOException, NullPointerException{
	
		Socket socket=new Socket("127.0.0.1",8080);
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream ps=new PrintStream(socket.getOutputStream()); 
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String accessCode= "Pass",code, name, message;
		Boolean tf= true;
	
			
		System.out.print("Please enter your name: ");
		name=in.readLine();
		System.out.print("Please enter the access code: ");
		code= in.readLine();
		
		while(!code.contentEquals(accessCode)){
			System.out.print("I am sorry, that access code is incorrect. Please re-enter access code: ");
			code= in.readLine();
		}//end while 
		
		if(code.contentEquals(accessCode)){
			System.out.println("You have successfully connected! To end the chat, please type 'quit'."+"\n");
			
			while(tf){
	
				System.out.print(name+": ");
				message=in.readLine();
				ps.println(name+": "+message);
				message=br.readLine();
				System.out.print("Server: "+message+"\n");
				
				if(message.equals("quit")){
					tf=false;
					System.out.print("\n"+"\n"+"\n"+"Chat disconnected.");
					break;
				}//end if 
			
			}//end while 
		
		}//end if 
	
		System.out.println("The chat has been disconnected.");
		socket.close();
		br.close();
		ps.close();
		in.close();
	
	}//end main

}//end class Client