

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server
{
	public static void main(String[] args) throws IOException{
	
		ServerSocket ss=new ServerSocket(8080);
		Socket socket=ss.accept();
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream ps=new PrintStream(socket.getOutputStream());
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String message;
		Boolean tf=true;
		
		while(tf) {
			
			message=br.readLine();
			if(message.equals("quit")){
				tf=false;
				System.out.print("\n"+"\n"+"\n"+"Chat disconnected.");
				break;
			}//end if 
			System.out.print(message+" \n"+ "Server: ");
			message=in.readLine();
			ps.println(message);
			
			
			
		}//end while
				
		ss.close();
		socket.close();
		br.close();
		ps.close();
		in.close();
	}//end main
}//end class Server 
