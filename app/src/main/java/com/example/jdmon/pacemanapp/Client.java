package com.example.jdmon.pacemanapp;

/**
 * Created by jdmon on 17/11/2018.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{

    private BufferedReader in;
    private PrintWriter out;
    private String moves_to_send;
    private boolean send_data_switch;

    private String ip;
    private int port;

    public void create_conection(){

        try {
            Socket socket = new Socket( ip, port);
            //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        create_conection();
        send_data_switch = true;

        while(true){
            if(send_data_switch){
                send_data_client(moves_to_send);
            }
        }
    }

    private void send_data_client(String data){
        try {
            out.println(data);
            out.flush();
        }catch(Exception e){
        }
    }

    public void send_data(String moves){
        this.moves_to_send=moves;
    }

    public void set_ip(String ip){
        this.ip=ip;
    }

    public String get_ip(){
        return ip;
    }

    public void set_port(int port){
        this.port=port;
    }

    public int get_port(){
        return this.port;
    }
}

   /*
    public String read(){

        String result = "";
        try {
            result = in.readLine();
        }catch (IOException s){
            s.printStackTrace();
        }
        return result;
    }
    */