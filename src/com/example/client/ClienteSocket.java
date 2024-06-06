package com.example.client;
import java.io.*;
import java.net.*;

public class ClienteSocket {

	public static void main(String[] args) {
        String serverIP = "10.130.129.103"; // IP do servidor
        int port = 12345; // Porta do servidor
        String messageToSend = "MTk5OA=="; // Mensagem a ser enviada
        		
        		try (Socket socket = new Socket(serverIP, port)) {
                    // Enviar mensagem para o servidor
                    OutputStream out = socket.getOutputStream();
                    out.write(messageToSend.getBytes());
                    out.flush();

                    /// Receber resposta do servidor
                    InputStream in = socket.getInputStream();
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();


                    int bytesRead;
                    byte[] data = new byte[1024];
                    while ((bytesRead = in.read(data, 0, data.length)) != -1) {
                        buffer.write(data, 0, bytesRead);
                    }

                    String serverResponse = new String(buffer.toByteArray());

                    // Salvar resposta em um arquivo
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("server_response.txt"))) {
                        writer.write(serverResponse);
                    }

                    System.out.println("Resposta do servidor: " + serverResponse);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
}
