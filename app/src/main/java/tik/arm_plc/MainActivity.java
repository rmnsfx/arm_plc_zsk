package tik.arm_plc;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiInfo;
import android.os.Handler;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.IOException;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
//import javax.swing.*;


import java.net.InetAddress;
import java.net.*;
//import net.wimpi.modbus.*;
//import net.wimpi.modbus.msg.*;
//import net.wimpi.modbus.io.*;
//import net.wimpi.modbus.net.*;
//import net.wimpi.modbus.util.*;

import com.invertor.modbus.*;
import com.invertor.modbus.exception.ModbusIOException;
import com.invertor.modbus.tcp.TcpParameters;

import android.os.AsyncTask;
import java.nio.ByteBuffer;

import android.view.View.OnFocusChangeListener;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.app.AlertDialog;
import android.content.DialogInterface;





public class MainActivity extends AppCompatActivity {

    private Button enableButton;
    private TextView mResultEditText;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    private TextView texttemper;

    private TextView setText1;
    private TextView setText2;
    private TextView setText3;
    private TextView setText4;
    private TextView setText5;
    private TextView setText6;
    private TextView setText7;
    private TextView setText8;
    private TextView setText9;
    private TextView setText10;
    private TextView setText11;
    private TextView setText12;
    private TextView setText13;
    private TextView setText14;
    private TextView setText15;
    private TextView setText16;
    private TextView setText17;
    private TextView setText18;
    private TextView setText19;
    private TextView setText20;
    private TextView setText21;
    private TextView setText22;

    private Switch mSwitch;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private TextView text5_0;
    private TextView text6_0;

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
    int[] registerValues2;

    int flag_write = 0;
    int input_number_register = 0;
    int input_value = 0;
    float float_input_value = (float) 0.0;

    int status_connection = 0;

    float vibr_1 = 0;
    float vibr_2 = 0;
    float x_1 = 0;
    float x_2 = 0;
    float y_1 = 0;
    float y_2 = 0;
    float z_1 = 0;
    float z_2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enableButton = (Button) findViewById(R.id.button1);
        mResultEditText = (TextView) findViewById(R.id.textView8);

        text1 = (TextView) findViewById(R.id.textView8);
        text2 = (TextView) findViewById(R.id.textView11);
        text3 = (TextView) findViewById(R.id.textView13);
        text4 = (TextView) findViewById(R.id.textView15);

        text5_0 = (TextView) findViewById(R.id.textView_relay1);
        text6_0 = (TextView) findViewById(R.id.textView2_relay3);
        text5 = (TextView) findViewById(R.id.textView_relay2);
        text6 = (TextView) findViewById(R.id.textView3_relay4);

        texttemper = (TextView) findViewById(R.id.textView5);

        setText1 = (TextView) findViewById(R.id.setText1);
        setText2= (TextView) findViewById(R.id.setText2);
        setText3= (TextView) findViewById(R.id.setText3);
        setText4= (TextView) findViewById(R.id.setText4);
        setText5= (TextView) findViewById(R.id.setText5);
        setText6= (TextView) findViewById(R.id.setText6);
        setText7= (TextView) findViewById(R.id.setText7);
        setText8= (TextView) findViewById(R.id.setText8);
        setText9= (TextView) findViewById(R.id.setText9);
        setText10= (TextView) findViewById(R.id.setText10);
        setText11= (TextView) findViewById(R.id.setText11);
        setText12= (TextView) findViewById(R.id.setText12);
        setText13= (TextView) findViewById(R.id.setText13);
        setText14= (TextView) findViewById(R.id.setText14);
        setText15= (TextView) findViewById(R.id.setText15);
        setText16= (TextView) findViewById(R.id.setText16);
        setText17= (TextView) findViewById(R.id.setText17);
        setText18= (TextView) findViewById(R.id.setText18);
        setText19= (TextView) findViewById(R.id.setText19);
        setText20= (TextView) findViewById(R.id.setText20);
        setText21= (TextView) findViewById(R.id.setText21);
        setText22= (TextView) findViewById(R.id.setText22);



        mSwitch = (Switch) findViewById(R.id.switch3);

        // устанавливаем переключатель программно в значение ON
        mSwitch.setChecked(false);

        // добавляем слушателя
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // в зависимости от значения isChecked выводим нужное сообщение
                if (isChecked) {
                    try {
                        new SocketTask().execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    //Toast.makeText(getApplicationContext(), "Опрос выключен", Toast.LENGTH_SHORT).show();
                }
            }
        });




        setText1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Виброскорость. Предупредительная уставка, мм/с");
                builder.setMessage("Введите новое значение");
                final EditText input = new EditText(MainActivity.this);
                //input.setId(TEXT_ID);
                builder.setView(input);

                builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //String value = input.getText().toString();
                        //Log.d(TAG, "User name: " + value);

                        flag_write = 1;
                        input_number_register = 118;
                        float_input_value = Float.parseFloat(input.getText().toString());

                        return;
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });



        setText3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Виброскорость. Аварийная уставка, мм/с");
                builder.setMessage("Введите новое значение");
                final EditText input = new EditText(MainActivity.this);
                //input.setId(TEXT_ID);
                builder.setView(input);

                builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //String value = input.getText().toString();
                        //Log.d(TAG, "User name: " + value);

                        flag_write = 1;
                        input_number_register = 120;
                        float_input_value = Float.parseFloat(input.getText().toString());

                        return;
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        setText5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ось X. Предупредительная уставка, град.");
                builder.setMessage("Введите новое значение");
                final EditText input = new EditText(MainActivity.this);
                //input.setId(TEXT_ID);
                builder.setView(input);

                builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //String value = input.getText().toString();
                        //Log.d(TAG, "User name: " + value);

                        flag_write = 1;
                        input_number_register = 132;
                        float_input_value = Float.parseFloat(input.getText().toString());

                        return;
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });



        setText7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ось X. Аварийная уставка, град.");
                builder.setMessage("Введите новое значение");
                final EditText input = new EditText(MainActivity.this);
                //input.setId(TEXT_ID);
                builder.setView(input);

                builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //String value = input.getText().toString();
                        //Log.d(TAG, "User name: " + value);

                        flag_write = 1;
                        input_number_register = 136;
                        float_input_value = Float.parseFloat(input.getText().toString());

                        return;
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });



        setText9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ось Y. Нижняя предупредительная уставка, град.");
                builder.setMessage("Введите новое значение");
                final EditText input = new EditText(MainActivity.this);
                //input.setId(TEXT_ID);
                builder.setView(input);

                builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //String value = input.getText().toString();
                        //Log.d(TAG, "User name: " + value);

                        flag_write = 1;
                        input_number_register = 144;
                        float_input_value = Float.parseFloat(input.getText().toString());

                        return;
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });



        setText11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ось Y. Аварийная уставка, град.");
                builder.setMessage("Введите новое значение");
                final EditText input = new EditText(MainActivity.this);
                //input.setId(TEXT_ID);
                builder.setView(input);

                builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //String value = input.getText().toString();
                        //Log.d(TAG, "User name: " + value);

                        flag_write = 1;
                        input_number_register = 148;
                        float_input_value = Float.parseFloat(input.getText().toString());

                        return;
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });



        setText13.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ось Z. Верхняя предупредительная уставка, град.");
                builder.setMessage("Введите новое значение");
                final EditText input = new EditText(MainActivity.this);
                //input.setId(TEXT_ID);
                builder.setView(input);

                builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //String value = input.getText().toString();
                        //Log.d(TAG, "User name: " + value);

                        flag_write = 1;
                        input_number_register = 156;
                        float_input_value = Float.parseFloat(input.getText().toString());

                        return;
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        setText15.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ось Z. Аварийная уставка, град.");
                builder.setMessage("Введите новое значение");
                final EditText input = new EditText(MainActivity.this);
                //input.setId(TEXT_ID);
                builder.setView(input);

                builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //String value = input.getText().toString();
                        //Log.d(TAG, "User name: " + value);

                        flag_write = 1;
                        input_number_register = 160;
                        float_input_value = Float.parseFloat(input.getText().toString());

                        return;
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });




        setText17.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Режим работы реле");
                builder.setMessage("Введите новое значение");
                final EditText input = new EditText(MainActivity.this);
                //input.setId(TEXT_ID);
                builder.setView(input);

                builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //String value = input.getText().toString();
                        //Log.d(TAG, "User name: " + value);

                        flag_write = 1;
                        input_number_register = 84;
                        input_value = Integer.parseInt(input.getText().toString());

                        return;
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });


        setText19.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Квитирование реле");
                builder.setMessage("Введите новое значение");
                final EditText input = new EditText(MainActivity.this);
                //input.setId(TEXT_ID);
                builder.setView(input);

                builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //String value = input.getText().toString();
                        //Log.d(TAG, "User name: " + value);

                        flag_write = 1;
                        input_number_register = 96;
                        input_value = Integer.parseInt(input.getText().toString());

                        return;
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        setText21.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Задержка реле на срабатывание, мс");
                builder.setMessage("Введите новое значение");
                final EditText input = new EditText(MainActivity.this);
                //input.setId(TEXT_ID);
                builder.setView(input);

                builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //String value = input.getText().toString();
                        //Log.d(TAG, "User name: " + value);

                        flag_write = 1;
                        input_number_register = 86;
                        input_value = Integer.parseInt(input.getText().toString());

                        return;
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });




        enableButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
//                WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
//                WifiInfo info = wifi.getConnectionInfo();
//                String ssid = info.getSSID();
//                mResultEditText.setText(ssid);

                flag_write = 1;
                input_number_register = 107;
                input_value = 0;


            }
        });

        image1 = (ImageView) findViewById(R.id.imageView);
        image2 = (ImageView) findViewById(R.id.imageView2);
        image3 = (ImageView) findViewById(R.id.imageView3);

        image1.setImageResource(R.drawable.a0_3);
        image2.setImageResource(R.drawable.a0_2);
        image3.setImageResource(R.drawable.a0_1);


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
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isPortOpen(final String ip, final int port, final int timeout) {

        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeout);
            socket.close();
            return true;
        } catch (ConnectException ce) {
            ce.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public float swapIntToFloat(int intValue1, int intValue2, int swap) {

        byte[] bytes = new byte[4];
        byte[] bytes1 = ByteBuffer.allocate(4).putInt(intValue1).array();
        byte[] bytes2 = ByteBuffer.allocate(4).putInt(intValue2).array();

        if (swap == 1) {
            bytes[0] = bytes1[2];
            bytes[1] = bytes1[3];
            bytes[2] = bytes2[2];
            bytes[3] = bytes2[3];
        } else {
            bytes[0] = bytes1[2];
            bytes[1] = bytes1[3];
            bytes[2] = bytes2[2];
            bytes[3] = bytes2[3];
        }

        return ByteBuffer.wrap(bytes).getFloat();
    }

    public static float round(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return ((float) ((int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp))) / pow;
    }

    public static byte [] float2ByteArray (float value)
    {
        return ByteBuffer.allocate(4).putFloat(value).array();
    }

    public static int[] byteArrayToInt(byte[] b)
    {
        int[] int_array =
                {
                        (b[0] << 8) | (b[1] & 0xFF),
                        (b[2] << 8) | (b[3] & 0xFF),
                };

        return int_array;
    }


    public class SocketTask extends AsyncTask<Void, Void, Void> {

        float value = 0;

        protected Void doInBackground(Void... params) {

            try {

                InetAddress serverAddr = InetAddress.getByName("192.168.5.241");

                tcpParameters = new TcpParameters();
                tcpParameters.setHost(serverAddr);
                tcpParameters.setKeepAlive(true);
                tcpParameters.setPort(300);

                m = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
                Modbus.setAutoIncrementTransactionId(true);

                int slaveId = 1;
                int offset = 1000;
                int quantity = 100;
                int offset2 = offset + quantity;
                String nameText;

                while (mSwitch.isChecked())
                {
                    if (status_connection == 0)
                    {
                        m.connect();
                        status_connection = 1;
                    }


                    try {

                        if (flag_write == 1) {

                            try {

                                byte[] byte_array = float2ByteArray(float_input_value);
                                int[] int_array = byteArrayToInt(byte_array);
                                //m.writeSingleRegister(slaveId, 1118, input_value);
                                if (input_number_register == 118)
                                    m.writeMultipleRegisters(slaveId, 1118, int_array);

                                else if (input_number_register == 120)
                                    m.writeMultipleRegisters(slaveId, 1120, int_array);

                                else if (input_number_register == 132)
                                    m.writeMultipleRegisters(slaveId, 1132, int_array);

                                else if (input_number_register == 136)
                                    m.writeMultipleRegisters(slaveId, 1136, int_array);

                                else if (input_number_register == 144)
                                    m.writeMultipleRegisters(slaveId, 1144, int_array);

                                else if (input_number_register == 148)
                                    m.writeMultipleRegisters(slaveId, 1148, int_array);

                                else if (input_number_register == 156)
                                    m.writeMultipleRegisters(slaveId, 1156, int_array);

                                else if (input_number_register == 160)
                                    m.writeMultipleRegisters(slaveId, 1160, int_array);

                                else if (input_number_register == 84)
                                    m.writeSingleRegister(slaveId, 1084, input_value);

                                else if (input_number_register == 96)
                                    m.writeSingleRegister(slaveId, 1096, input_value);

                                else if (input_number_register == 86)
                                    m.writeSingleRegister(slaveId, 1086, input_value);

                                else if (input_number_register == 107) {
                                    m.writeSingleRegister(slaveId, 1107, 43981);


                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), "Настройки применены", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }

                            } catch (ModbusIOException e) {
                                e.printStackTrace();

                            }

                            flag_write = 0;
                            input_number_register = 0;
                            input_value = 0;

                        } else {
                            registerValues = m.readHoldingRegisters(slaveId, offset, quantity);
                            Thread.sleep(50);
                            registerValues2 = m.readHoldingRegisters(slaveId, offset2, 62);


                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {

                                    value = swapIntToFloat(registerValues2[16], registerValues2[17], 0);
                                    value = round(value, 2);
                                    text1.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[28], registerValues2[29], 1);
                                    text2.setText(String.valueOf(value  + "°"));
                                    value = Math.abs(value);


                                        if (value >= 0 && value < x_1)
                                        {
                                            image1.setImageResource(R.drawable.a2_3);
                                        }
                                        else if (value >= x_1 && value < x_2)
                                        {
                                            image1.setImageResource(R.drawable.a2_4);
                                        }
                                        else if (value >= x_2)
                                        {
                                            image1.setImageResource(R.drawable.a2_5);
                                        }


                                    value = swapIntToFloat(registerValues2[40], registerValues2[41], 1);
                                    text3.setText(String.valueOf(value  + "°"));
                                    value = Math.abs(value);


                                        if (value >= 0 && value < y_1)
                                        {
                                            image2.setImageResource(R.drawable.a3_3);
                                        }
                                        else if (value >= y_1 && value < y_2)
                                        {
                                            image2.setImageResource(R.drawable.a3_4);
                                        }
                                        else if (value >= y_2)
                                        {
                                            image2.setImageResource(R.drawable.a3_4);
                                        }


                                    value = swapIntToFloat(registerValues2[52], registerValues2[53], 1);
                                    text4.setText(String.valueOf(value  + "°"));
                                    value = Math.abs(value);


                                        if (value >= 0 && value < z_1)
                                        {
                                            image3.setImageResource(R.drawable.a1_3);
                                        }
                                        else if (value >= z_1 && value < z_2)
                                        {
                                            image3.setImageResource(R.drawable.a1_4);
                                        }
                                        else if (value >= z_2)
                                        {
                                            image3.setImageResource(R.drawable.a1_5);
                                        }


                                    text5.setText(String.valueOf(registerValues[82]));
                                    if (registerValues[82] == 1)
                                    {
                                        text5_0.setTextColor(Color.RED);
                                        text5.setTextColor(Color.RED);
                                    }
                                    else
                                    {
                                        text5_0.setTextColor(Color.GRAY);
                                        text5.setTextColor(Color.GRAY);
                                    }

                                    text6.setText(String.valueOf(registerValues[83]));
                                    if (registerValues[83] == 1)
                                    {
                                        text6_0.setTextColor(Color.RED);
                                        text6.setTextColor(Color.RED);
                                    }
                                    else
                                    {
                                        text6_0.setTextColor(Color.GRAY);
                                        text6.setTextColor(Color.GRAY);
                                    }

                                    value = swapIntToFloat(registerValues2[24], registerValues2[25], 0);
                                    texttemper.setText(String.valueOf(value));


                                    //Уставки




                                    value = swapIntToFloat(registerValues2[18], registerValues2[19], 0);
                                    vibr_1 = value;
                                    setText2.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[20], registerValues2[21], 0);
                                    vibr_2 = value;
                                    setText4.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[32], registerValues2[33], 0);
                                    x_1 = value;
                                    setText6.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[36], registerValues2[37], 0);
                                    x_2 = value;
                                    setText8.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[44], registerValues2[45], 0);
                                    y_1 = value;
                                    setText10.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[48], registerValues2[49], 0);
                                    y_1 = value;
                                    setText12.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[56], registerValues2[57], 0);
                                    z_1 = value;
                                    setText14.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[60], registerValues2[61], 0);
                                    z_1 = value;
                                    setText16.setText(String.valueOf(value));

                                    setText18.setText(String.valueOf(registerValues[84]));

                                    setText20.setText(String.valueOf(registerValues[96]));

                                    setText22.setText(String.valueOf(registerValues[86]));


                                }
                            });
                        }


                    } catch (ModbusIOException e) {
                        e.printStackTrace();

                        status_connection = 0;

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                text1.setText("-");
                                text2.setText("-");
                                text3.setText("-");
                                text4.setText("-");
                                text5.setText("-");
                                text6.setText("-");
                                texttemper.setText("-");
                            }
                        });
                    }

                    Thread.sleep(500);

                }

                m.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        mSwitch.toggle();

                        Toast.makeText(getApplicationContext(), "Ошибка соединения", Toast.LENGTH_SHORT).show();
                    }
                });
            } finally {
                try {
                    m.disconnect();
                } catch (ModbusIOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }


        protected void onPostExecute(String result) {


        }
    }
}









