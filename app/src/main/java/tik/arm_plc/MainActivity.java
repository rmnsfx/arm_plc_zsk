package tik.arm_plc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiInfo;
import android.os.Handler;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.IOException;
//import javax.swing.*;


import java.net.InetAddress;
import java.net.*;
//import net.wimpi.modbus.*;
//import net.wimpi.modbus.msg.*;
//import net.wimpi.modbus.io.*;
//import net.wimpi.modbus.net.*;
//import net.wimpi.modbus.util.*;

import com.invertor.modbus.*;
import com.invertor.modbus.tcp.TcpParameters;

import android.os.AsyncTask;
import java.nio.ByteBuffer;



public class MainActivity extends AppCompatActivity {

    private Button enableButton;
    private TextView mResultEditText;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private Switch mSwitch;


    /* The important instances of the classes mentioned before */
//    public TCPMasterConnection con; //the connection
//    public ModbusTCPTransaction trans = null; //the transaction
//    public ReadInputDiscretesRequest req = null; //the request
//    public ReadInputDiscretesResponse res = null; //the response

    public TcpParameters tcpParameters;
    public ModbusMaster m;

    /* Variables for storing the parameters */
    InetAddress addr = null; //the slave's address
    int port = 502;
    int ID = 1;
    int ref = 0; //the reference; offset where to start reading from
    int count = 1; //the number of DI's to read
    int repeat = 1; //a loop for repeating the transaction

    private Handler mHandler = new Handler();
    private Socket socket;

    int[] registerValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enableButton = (Button)findViewById(R.id.button1);
        mResultEditText = (TextView)findViewById(R.id.textView8);

        text1 = (TextView) findViewById(R.id.textView8);
        text2 = (TextView) findViewById(R.id.textView11);
        text3 = (TextView) findViewById(R.id.textView13);
        text4 = (TextView) findViewById(R.id.textView15);

        mSwitch = (Switch) findViewById(R.id.switch3);
        // устанавливаем переключатель программно в значение ON
        mSwitch.setChecked(false);
        // добавляем слушателя
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // в зависимости от значения isChecked выводим нужное сообщение
                if (isChecked) {

                    //Toast.makeText(getApplicationContext(), String.valueOf(isPortOpen("192.168.100.5", 300, 3000)), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Опрос включен", Toast.LENGTH_SHORT).show();





//                    Thread thread = new Thread() {
//                        @Override
//                        public void run() {
//                            try {
//                                receiveMyMessage();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    };
//
//                    thread.start();
                    try {
                        new SocketTask().execute();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    //Toast.makeText(getApplicationContext(), "Опрос выключен", Toast.LENGTH_SHORT).show();
                }
            }
        });



        enableButton.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = wifi.getConnectionInfo();
                String ssid = info.getSSID();
                mResultEditText.setText(ssid);

            }
        });
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public boolean isConecctedToInternet() {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 192.168.100.5");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }

    public static boolean isPortOpen(final String ip, final int port, final int timeout) {

        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeout);
            socket.close();
            return true;
        }

        catch(ConnectException ce){
            ce.printStackTrace();
            return false;
        }

        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
//    public void receiveMyMessage() {
//
//
//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//
//
//
//                // This gets executed on the UI thread so it can safely modify Views
//                try {
//                    InetAddress serverAddr = InetAddress.getByName("192.168.100.5");
//                    TCPMasterConnection con = new TCPMasterConnection(serverAddr);
//                    con.setPort(502);
//                    //con.connect();
//
//                    Socket socket = new Socket();
//                    socket.connect(new InetSocketAddress("192.168.100.5", 502), 1000);
//
//                    //text2.setText("conn2");
//
//                    Toast.makeText(getApplicationContext(), "Connect", Toast.LENGTH_SHORT).show();
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//
//                    Toast.makeText(getApplicationContext(), "NOT Connect", Toast.LENGTH_SHORT).show();
//
//                    //text2.setText("conn3");
//                }
//
//
//            }
//        });
//    }

    public float swapIntToFloat(int intValue1, int intValue2) {

        byte[] bytes = new byte[4];
        byte[] bytes1 = ByteBuffer.allocate(4).putInt(intValue1).array();
        byte[] bytes2 = ByteBuffer.allocate(4).putInt(intValue2).array();

        bytes[0] = bytes1[2];
        bytes[1] = bytes1[3];
        bytes[2] = bytes2[2];
        bytes[3] = bytes2[3];

        return ByteBuffer.wrap(bytes).getFloat();
    }


    public class SocketTask extends AsyncTask<Void, Void, Void> {

        float value = 0;

        protected Void doInBackground(Void... params) {
            try {

                InetAddress serverAddr = InetAddress.getByName("192.168.100.5");

                tcpParameters = new TcpParameters();
                tcpParameters.setHost(serverAddr);
                tcpParameters.setKeepAlive(true);
                tcpParameters.setPort(300);

                m = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
                Modbus.setAutoIncrementTransactionId(true);

                int slaveId = 1;
                int offset = 1117;
                int quantity = 40;




                m.connect();

                while(mSwitch.isChecked()) {
                    registerValues = m.readHoldingRegisters(slaveId, offset, quantity);

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            value = swapIntToFloat(registerValues[0], registerValues[1]);
                            text1.setText(String.valueOf( value ));

                            text2.setText(String.valueOf( (float) (registerValues[12] / (float)100) ));
                            text3.setText(String.valueOf( (float) (registerValues[23] / (float)100) ));
                            text4.setText(String.valueOf( (float) (registerValues[36] / (float)100) ));
                        }
                    });
                }

                m.disconnect();




            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }


        protected void onPostExecute(String result) {


            }
    }



//        public void connectTCPModbus() {
//            // Connect
//            try {
//
//                InetAddress serverAddr = InetAddress.getByName("192.168.100.5");
//                Socket socket = new Socket(serverAddr, 502);
//
//                //addr = InetAddress.getByName("192.168.100.5");
//                //con = new TCPMasterConnection(addr);
//                //2. Open the connection
//
//                //con.setPort(port);
//                //con.connect();
//
////                //3. Prepare the request
////                  req.setUnitID(ID)
////                req = new ReadInputDiscretesRequest(ref, count);
////
////                //4. Prepare the transaction
////                trans = new ModbusTCPTransaction(con);
////                trans.setRequest(req);
////
////                //5. Execute the transaction repeat times
////                int k = 0;
////                do {
////                    //trans.execute();
////                    //res = (ReadInputDiscretesResponse) trans.getResponse();
////                    System.out.println("Digital Inputs Status=" + res.getDiscretes().toString());
////                    k++;
////                } while (k < repeat);
//
//                //6. Close the connection
////                con.close();
//
//
//
//
//
//
//
//                Toast.makeText(getApplicationContext(), "Connect", Toast.LENGTH_SHORT).show();
//
//
//            }
//            catch(Exception e){
//                //text2 = (TextView)findViewById(R.id.textView11);
//                //text2.setText("Error connection");
//
//                Toast.makeText(getApplicationContext(), "Error connection", Toast.LENGTH_SHORT).show();
//            }
//
//            // Disconnect
//            finally {
//                //if (master != null) {
//                //    master.disconnect();
//                //}
//            }
//
//
//        }
//    }



//c boolean ConnectToNetwork( )
//
//ifiManager wifiManager = (WifiManager) getSystemService (Context.WIFI_SERVICE);
//ifiInfo info = wifiManager.getConnectionInfo ();
//tring ssid  = info.getSSID();
//


//    class InternetTask extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            try {
//
////                InetAddress serverAddr = InetAddress.getByName("192.168.100.5");
////                Socket socket = new Socket(serverAddr, 502);
//
//                text2.setText("connection2");
//
//            }
//            catch(Exception e){
//
//                text2.setText("Error connection");
//
//                //Toast.makeText(getApplicationContext(), "Error connection", Toast.LENGTH_SHORT).show();
//            }
//            return null;
//        }
//
//    }







}



