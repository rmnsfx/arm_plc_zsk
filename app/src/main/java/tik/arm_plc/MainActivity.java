package tik.arm_plc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiInfo;
import android.os.Handler;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
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
    private TextView text7;
    private TextView text8;
    private TextView text9;
    private TextView text10;
    private TextView text11;
    private TextView text12;
    private TextView text13;
    private TextView text14;
    private TextView text15;
    private TextView text16;
    private TextView text17;
    private TextView text18;
    private TextView text19;
    private TextView text20;
    private TextView text21;
    private TextView text22;
    private TextView texttemper;
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
    int[] registerValues2;

    int flag_write = 0;
    int input_number_register = 0;
    int input_value = 0;
    float float_input_value = (float) 0.0;


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

        text5 = (TextView) findViewById(R.id.textView_relay2);
        text6 = (TextView) findViewById(R.id.textView3_relay4);

        texttemper = (TextView) findViewById(R.id.textView5);

        text7 = (TextView) findViewById(R.id.editText);
        //text7.setBackgroundResource(R.drawable.corner);
        //text7.setOnFocusChangeListener((OnFocusChangeListener) this);
        //text7.setOnLongClickListener((OnLongClickListener) this);

        text8 = (TextView) findViewById(R.id.editText2);
        //text8.setBackgroundResource(R.drawable.corner);
        //text8.setOnFocusChangeListener((OnFocusChangeListener) this);

        text9 = (TextView) findViewById(R.id.editText3);
        //text9.setBackgroundResource(R.drawable.corner);
        //text9.setOnFocusChangeListener((OnFocusChangeListener) this);

        text10 = (TextView) findViewById(R.id.editText6);
        //text10.setBackgroundResource(R.drawable.corner);
        //text10.setOnFocusChangeListener((OnFocusChangeListener) this);

        text11 = (TextView) findViewById(R.id.editText7);
        //text11.setBackgroundResource(R.drawable.corner);
        //text11.setOnFocusChangeListener((OnFocusChangeListener) this);

        text12 = (TextView) findViewById(R.id.editText8);
        //text12.setBackgroundResource(R.drawable.corner);
        //text12.setOnFocusChangeListener((OnFocusChangeListener) this);

        text13 = (TextView) findViewById(R.id.editText9);
        //text13.setBackgroundResource(R.drawable.corner);
        //text13.setOnFocusChangeListener((OnFocusChangeListener) this);

        text14 = (TextView) findViewById(R.id.editText10);
        //text14.setBackgroundResource(R.drawable.corner);
        //text14.setOnFocusChangeListener((OnFocusChangeListener) this);

        text15 = (TextView) findViewById(R.id.editText11);
        //text15.setBackgroundResource(R.drawable.corner);
        //text15.setOnFocusChangeListener((OnFocusChangeListener) this);

        text16 = (TextView) findViewById(R.id.editText12);
        //text16.setBackgroundResource(R.drawable.corner);
        //text16.setOnFocusChangeListener((OnFocusChangeListener) this);

        text17 = (TextView) findViewById(R.id.editText13);
        //text17.setBackgroundResource(R.drawable.corner);
        //text17.setOnFocusChangeListener((OnFocusChangeListener) this);

        text18 = (TextView) findViewById(R.id.editText14);
       //text18.setBackgroundResource(R.drawable.corner);
        //text18.setOnFocusChangeListener((OnFocusChangeListener) this);

        text19 = (TextView) findViewById(R.id.editText15);
        //text19.setBackgroundResource(R.drawable.corner);
        //text19.setOnFocusChangeListener((OnFocusChangeListener) this);

        text20 = (TextView) findViewById(R.id.editText16);
        //text20.setBackgroundResource(R.drawable.corner);
        //text20.setOnFocusChangeListener((OnFocusChangeListener) this);

        text21 = (TextView) findViewById(R.id.textView30);

        text22 = (TextView) findViewById(R.id.textView32);

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


        text7.setOnClickListener(new OnClickListener() {
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
                        input_number_register = 14;
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

        text8.setOnClickListener(new OnClickListener() {
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
                        input_number_register = 13;
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

        text9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Виброскорость. Нижняя предупредительная уставка, мм/с");
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
                        input_number_register = 1;
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

        text10.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Виброскорость. Верхняя предупредительная уставка, мм/с");
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
                        input_number_register = 2;
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

        text11.setOnClickListener(new OnClickListener() {
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
                        input_number_register = 3;
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

        text12.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ось X. Нижняя предупредительная уставка, град.");
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
                        input_number_register = 4;
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

        text13.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ось X. Верхняя предупредительная уставка, град.");
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
                        input_number_register = 5;
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

        text14.setOnClickListener(new OnClickListener() {
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
                        input_number_register = 6;
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



        text15.setOnClickListener(new OnClickListener() {
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
                        input_number_register = 7;
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

        text16.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ось Y. Верхняя предупредительная уставка, град.");
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
                        input_number_register = 8;
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

        text17.setOnClickListener(new OnClickListener() {
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
                        input_number_register = 9;
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

        text18.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ось Z. Нижняя предупредительная уставка, град.");
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
                        input_number_register = 10;
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

        text19.setOnClickListener(new OnClickListener() {
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
                        input_number_register = 11;
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

        text20.setOnClickListener(new OnClickListener() {
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
                        input_number_register = 12;
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

        text21.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Включить канал 485");
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
                        input_number_register = 16;
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


        text22.setOnClickListener(new OnClickListener() {
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
                        input_number_register = 17;
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
                input_number_register = 15;
                input_value = 0;


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

                m.connect();

                while (mSwitch.isChecked()) {
                    try {

                        if (flag_write == 1) {
                            try {

                                byte[] byte_array = float2ByteArray(float_input_value);
                                int[] int_array = byteArrayToInt(byte_array);
                                //m.writeSingleRegister(slaveId, 1118, input_value);
                                if (input_number_register == 1)
                                    m.writeMultipleRegisters(slaveId, 1118, int_array);

                                else if (input_number_register == 2)
                                    m.writeMultipleRegisters(slaveId, 1120, int_array);

                                else if (input_number_register == 3)
                                    m.writeMultipleRegisters(slaveId, 1122, int_array);

                                else if (input_number_register == 4)
                                    m.writeMultipleRegisters(slaveId, 1130, int_array);

                                else if (input_number_register == 5)
                                    m.writeMultipleRegisters(slaveId, 1132, int_array);

                                else if (input_number_register == 6)
                                    m.writeMultipleRegisters(slaveId, 1134, int_array);

                                else if (input_number_register == 7)
                                    m.writeMultipleRegisters(slaveId, 1142, int_array);

                                else if (input_number_register == 8)
                                    m.writeMultipleRegisters(slaveId, 1144, int_array);

                                else if (input_number_register == 9)
                                    m.writeMultipleRegisters(slaveId, 1146, int_array);

                                else if (input_number_register == 10)
                                    m.writeMultipleRegisters(slaveId, 1154, int_array);

                                else if (input_number_register == 11)
                                    m.writeMultipleRegisters(slaveId, 1156, int_array);

                                else if (input_number_register == 12)
                                    m.writeMultipleRegisters(slaveId, 1158, int_array);

                                else if (input_number_register == 13)
                                    m.writeSingleRegister(slaveId, 1086, input_value);

                                else if (input_number_register == 14)
                                    m.writeSingleRegister(slaveId, 1096, input_value);

                                else if (input_number_register == 15) {
                                    m.writeSingleRegister(slaveId, 1107, 43981);

                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), "Настройки применены", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                else if (input_number_register == 16)
                                    m.writeSingleRegister(slaveId, 1072, input_value);

                                else if (input_number_register == 17)
                                    m.writeSingleRegister(slaveId, 1084, input_value);

                            } catch (ModbusIOException e) {
                                e.printStackTrace();
                            }

                            flag_write = 0;
                            input_number_register = 0;
                            input_value = 0;

                        } else {
                            registerValues = m.readHoldingRegisters(slaveId, offset, quantity);
                            Thread.sleep(50);
                            registerValues2 = m.readHoldingRegisters(slaveId, offset2, 60);


                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {

                                    value = swapIntToFloat(registerValues2[16], registerValues2[17], 0);
                                    value = round(value, 2);
                                    text1.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[24], registerValues2[25], 0);
                                    texttemper.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[28], registerValues2[29], 1);
                                    text2.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[40], registerValues2[41], 1);
                                    text3.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[52], registerValues2[53], 1);
                                    text4.setText(String.valueOf(value));

                                    text5.setText(String.valueOf(registerValues[82]));

                                    text6.setText(String.valueOf(registerValues[83]));

                                    text7.setText(String.valueOf(registerValues[96]));

                                    text8.setText(String.valueOf(registerValues[86]));

                                    value = swapIntToFloat(registerValues2[18], registerValues2[19], 0);
                                    text9.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[20], registerValues2[21], 0);
                                    text10.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[22], registerValues2[23], 0);
                                    text11.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[30], registerValues2[31], 0);
                                    text12.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[32], registerValues2[33], 0);
                                    text13.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[34], registerValues2[35], 0);
                                    text14.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[42], registerValues2[43], 0);
                                    text15.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[44], registerValues2[45], 0);
                                    text16.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[46], registerValues2[47], 0);
                                    text17.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[54], registerValues2[55], 0);
                                    text18.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[56], registerValues2[57], 0);
                                    text19.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues2[58], registerValues2[59], 0);
                                    text20.setText(String.valueOf(value));

                                    text21.setText(String.valueOf(registerValues[72]));

                                    text22.setText(String.valueOf(registerValues[84]));
                                }
                            });
                        }


                    } catch (ModbusIOException e) {
                        e.printStackTrace();
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









