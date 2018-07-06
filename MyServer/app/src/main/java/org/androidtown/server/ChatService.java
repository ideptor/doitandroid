package org.androidtown.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatService extends Service {
    public ChatService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServerThread thread = new ServerThread();
        thread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class ServerThread extends Thread {
        public void run() {
            int port = 5001;
            ServerSocket server = null;
            Socket socket = null;
            try {
                server = new ServerSocket(port);
                Log.d("ServerThread", "Server started");

                while(true) {
                    socket = server.accept();
                    ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                    Object input = instream.readObject();
                    Log.d("ServerThread", "input :" + input);

                    ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                    outstream.writeObject(input + " from server.");
                    outstream.flush();

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        socket = null;
                    }
                }

            }
        }
    }
}
