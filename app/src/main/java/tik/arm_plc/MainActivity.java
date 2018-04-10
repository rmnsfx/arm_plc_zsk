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
    private Button quit_button;
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

    private TextView setText23;
    private TextView setText24;
    private TextView setText25;
    private TextView setText26;

    private TextView setText27;
    private TextView setText28;
    private TextView setText29;
    private TextView setText30;

    private TextView setText31;
    private TextView setText32;
    private TextView setText33;
    private TextView setText34;


    private TextView setText35;
    private TextView setText36;
    private TextView setText37;
    private TextView setText38;
    private TextView setText39;
    private TextView setText40;

    private TextView setText41;
    private TextView setText42;
    private TextView setText43;
    private TextView setText44;
    private TextView setText45;
    private TextView setText46;

    private TextView setText47;
    private TextView setText48;
    private TextView setText49;
    private TextView setText50;
    private TextView setText51;
    private TextView setText52;



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
    int[] registerValues3;
    int[] registerValues4;

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

    float vibr_1_low = 0;
    float vibr_2_low = 0;
    float x_1_low = 0;
    float x_2_low = 0;
    float y_1_low = 0;
    float y_2_low = 0;
    float z_1_low = 0;
    float z_2_low = 0;

    float vibr_a_x = 0;
    float vibr_a_x_pred = 0;
    float vibr_a_x_avar = 0;

    float vibr_a_y = 0;
    float vibr_a_y_pred = 0;
    float vibr_a_y_avar = 0;

    float vibr_a_z = 0;
    float vibr_a_z_pred = 0;
    float vibr_a_z_avar = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enableButton = (Button) findViewById(R.id.button1);
        quit_button = (Button) findViewById(R.id.button2);
        //mResultEditText = (TextView) findViewById(R.id.textView8);

        text1 = (TextView) findViewById(R.id.textView8);
        //text2 = (TextView) findViewById(R.id.textView11);
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
        //setText19= (TextView) findViewById(R.id.setText19);
        //setText20= (TextView) findViewById(R.id.setText20);
        setText21= (TextView) findViewById(R.id.setText21);
        setText22= (TextView) findViewById(R.id.setText22);

        setText23= (TextView) findViewById(R.id.setText23);
        setText24= (TextView) findViewById(R.id.setText24);
        setText25= (TextView) findViewById(R.id.setText25);
        setText26= (TextView) findViewById(R.id.setText26);

        setText27= (TextView) findViewById(R.id.setText27);
        setText28= (TextView) findViewById(R.id.setText28);
        setText29= (TextView) findViewById(R.id.setText29);
        setText30= (TextView) findViewById(R.id.setText30);

        setText31= (TextView) findViewById(R.id.setText31);
        setText32= (TextView) findViewById(R.id.setText32);
        setText33= (TextView) findViewById(R.id.setText33);
        setText34= (TextView) findViewById(R.id.setText34);


        //виброускорение по оси Х
        setText35= (TextView) findViewById(R.id.setText35);
        setText36= (TextView) findViewById(R.id.setText36);
        setText37= (TextView) findViewById(R.id.setText37);
        setText38= (TextView) findViewById(R.id.setText38);
        setText39= (TextView) findViewById(R.id.setText39);
        setText40= (TextView) findViewById(R.id.setText40);

        //виброускорение по оси Y
        setText41= (TextView) findViewById(R.id.setText41);
        setText42= (TextView) findViewById(R.id.setText42);
        setText43= (TextView) findViewById(R.id.setText43);
        setText44= (TextView) findViewById(R.id.setText44);
        setText45= (TextView) findViewById(R.id.setText45);
        setText46= (TextView) findViewById(R.id.setText46);

        //виброускорение по оси Z
        setText47= (TextView) findViewById(R.id.setText47);
        setText48= (TextView) findViewById(R.id.setText48);
        setText49= (TextView) findViewById(R.id.setText49);
        setText50= (TextView) findViewById(R.id.setText50);
        setText51= (TextView) findViewById(R.id.setText51);
        setText52= (TextView) findViewById(R.id.setText52);





        mSwitch = (Switch) findViewById(R.id.switch3);

        // устанавливаем переключатель программно в значение OFF
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
                        input_number_register = 221;

                        try
                        {
                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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
                        input_number_register = 223;
                        try
                        {
                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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



        setText23.setOnClickListener(new OnClickListener() {
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
                        input_number_register = 157;
                        try
                        {
                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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
                        input_number_register = 161;
                        try
                        {
                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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




        setText25.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ось X. Нижняя аварийная уставка, град.");
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
                        input_number_register = 159;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }


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
                builder.setTitle("Ось X. Верхняя аварийная уставка, град.");
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
                        input_number_register = 163;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }


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



        setText27.setOnClickListener(new OnClickListener() {
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
                        input_number_register = 177;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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
                        input_number_register = 181;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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


        setText29.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ось Y. Нижняя аварийная уставка, град.");
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
                        input_number_register = 179;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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
                builder.setTitle("Ось Y. Верхняя аварийная уставка, град.");
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
                        input_number_register = 183;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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

        setText31.setOnClickListener(new OnClickListener() {
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
                        input_number_register = 197;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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
                        input_number_register = 201;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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



        setText33.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ось Z. Нижняя аварийная уставка, град.");
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
                        input_number_register = 199;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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
                builder.setTitle("Ось Z. Верхняя аварийная уставка, град.");
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
                        input_number_register = 203;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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





        setText37.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Виброускорение, ось X. Предупредительная уставка, град.");
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
                        input_number_register = 261;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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

        setText39.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Виброускорение, ось X. Аварийная уставка, град.");
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
                        input_number_register = 263;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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

        setText43.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Виброускорение, ось Y. Предупредительная уставка, град.");
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
                        input_number_register = 281;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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

        setText45.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Виброускорение, ось Y. Аварийная уставка, град.");
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
                        input_number_register = 283;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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

        setText49.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Виброускорение, ось Z. Предупредительная уставка, град.");
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
                        input_number_register = 301;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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

        setText51.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Виброускорение, ось Z. Аварийная уставка, град.");
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
                        input_number_register = 303;

                        try
                        {

                            float_input_value = Float.parseFloat(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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

                        try
                        {
                            input_value = Integer.parseInt(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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


//        setText19.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Квитирование реле");
//                builder.setMessage("Введите новое значение");
//                final EditText input = new EditText(MainActivity.this);
//                //input.setId(TEXT_ID);
//                builder.setView(input);
//
//                builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        //String value = input.getText().toString();
//                        //Log.d(TAG, "User name: " + value);
//
//                        flag_write = 1;
//                        input_number_register = 96;
//
//                        try
//                        {
//                            input_value = Integer.parseInt(input.getText().toString());
//
//                        } catch (Exception e)
//                            {
//
//                                mHandler.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        e.printStackTrace();
//                    }
//
//                        return;
//                    }
//                });
//
//                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        return;
//                    }
//                });
//
//                AlertDialog alert = builder.create();
//                alert.show();
//
//            }
//        });

        setText21.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Задержка реле на срабатывание, сек.");
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

                        try
                        {

                            input_value = Integer.parseInt(input.getText().toString());

                        } catch (Exception e)
                        {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Введено неверное значение", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

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

        quit_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                flag_write = 1;
                input_number_register = 96;
                input_value = 1;


            }
        });

        //image1 = (ImageView) findViewById(R.id.imageView);
        image2 = (ImageView) findViewById(R.id.imageView2);
        image3 = (ImageView) findViewById(R.id.imageView3);

        //image1.setImageResource(R.drawable.i0_3);
        image2.setImageResource(R.drawable.i0_2);
        image3.setImageResource(R.drawable.i0_1);


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
        int value_int = 0;
        int val_set = 0;

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

                                if (input_number_register == 157)
                                    m.writeMultipleRegisters(slaveId, 1156, int_array);

                                else if (input_number_register == 159)
                                    m.writeMultipleRegisters(slaveId, 1158, int_array);

                                else if (input_number_register == 161)
                                    m.writeMultipleRegisters(slaveId, 1160, int_array);

                                else if (input_number_register == 163)
                                    m.writeMultipleRegisters(slaveId, 1162, int_array);

                                else if (input_number_register == 177)
                                    m.writeMultipleRegisters(slaveId, 1176, int_array);

                                else if (input_number_register == 179)
                                    m.writeMultipleRegisters(slaveId, 1178, int_array);

                                else if (input_number_register == 181)
                                    m.writeMultipleRegisters(slaveId, 1180, int_array);

                                else if (input_number_register == 183)
                                    m.writeMultipleRegisters(slaveId, 1182, int_array);

                                else if (input_number_register == 197)
                                    m.writeMultipleRegisters(slaveId, 1196, int_array);

                                else if (input_number_register == 199)
                                    m.writeMultipleRegisters(slaveId, 1198, int_array);

                                else if (input_number_register == 201)
                                    m.writeMultipleRegisters(slaveId, 1200, int_array);

                                else if (input_number_register == 203)
                                    m.writeMultipleRegisters(slaveId, 1202, int_array);

                                else if (input_number_register == 217)
                                    m.writeMultipleRegisters(slaveId, 1216, int_array);

                                else if (input_number_register == 219)
                                    m.writeMultipleRegisters(slaveId, 1218, int_array);

                                else if (input_number_register == 221)
                                    m.writeMultipleRegisters(slaveId, 1220, int_array);

                                else if (input_number_register == 223)
                                    m.writeMultipleRegisters(slaveId, 1222, int_array);



                                else if (input_number_register == 261)
                                    m.writeMultipleRegisters(slaveId, 1260, int_array);

                                else if (input_number_register == 263)
                                    m.writeMultipleRegisters(slaveId, 1262, int_array);

                                else if (input_number_register == 281)
                                    m.writeMultipleRegisters(slaveId, 1280, int_array);

                                else if (input_number_register == 283)
                                    m.writeMultipleRegisters(slaveId, 1282, int_array);

                                else if (input_number_register == 301)
                                    m.writeMultipleRegisters(slaveId, 1300, int_array);

                                else if (input_number_register == 303)
                                    m.writeMultipleRegisters(slaveId, 1302, int_array);




                                else if (input_number_register == 84)
                                    m.writeSingleRegister(slaveId, 1084, input_value);

                                else if (input_number_register == 96)
                                    m.writeSingleRegister(slaveId, 1096, input_value);

                                else if (input_number_register == 86)
                                    m.writeSingleRegister(slaveId, 1086, input_value);

                                else if (input_number_register == 107)
                                {
                                    m.writeSingleRegister(slaveId, 1107, 0xABCD);


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
                            registerValues = m.readHoldingRegisters(slaveId, 1000, 100);
                            Thread.sleep(50);
                            registerValues2 = m.readHoldingRegisters(slaveId, 1100, 100);
                            Thread.sleep(50);
                            registerValues3 = m.readHoldingRegisters(slaveId, 1200, 100);
                            Thread.sleep(50);
                            registerValues4 = m.readHoldingRegisters(slaveId, 1300, 50);

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {

                                    //Виброскорость по оси X
                                    value = swapIntToFloat(registerValues3[14], registerValues3[15], 0);
                                    value = round(value, 2);
                                    text1.setText(String.valueOf(value));

                                    //ось X
                                    //value = swapIntToFloat(registerValues2[54], registerValues2[55], 0);

                                    value_int =  registerValues2[54];
                                    if ( value_int >> 14 != 0 )
                                        value_int = value_int | ~((1 << 15) - 1);

/*                                    text2.setText(String.valueOf(value_int + "°"));
                                    //val_set = Math.abs(value_int);

                                        if (value_int <= x_2_low)
                                        {
                                            image1.setImageResource(R.drawable.i3_b);
                                        }
                                        if (value_int <= x_1_low && value_int > x_2_low)
                                        {
                                            image1.setImageResource(R.drawable.i3_aa);
                                        }

                                        if (value_int < (x_1_low/2) && value_int > x_1_low)
                                        {
                                            image1.setImageResource(R.drawable.i3_1);
                                        }
                                        if (value_int > (x_1_low/2) && value_int < (x_1/2))
                                        {
                                            image1.setImageResource(R.drawable.i3_2);
                                        }
                                        if (value_int > (x_1/2) && value_int < (x_1))
                                        {
                                            image1.setImageResource(R.drawable.i3_3);
                                        }

                                        if (value_int >= x_1 && value_int < x_2)
                                        {
                                            image1.setImageResource(R.drawable.i3_aa);
                                        }
                                        if (value_int >= x_2)
                                        {
                                            image1.setImageResource(R.drawable.i3_a);
                                        }*/

                                    //ось Y
                                    //value = swapIntToFloat(registerValues2[70], registerValues2[71], 0);
                                    value_int =  registerValues2[74];

                                    if ( value_int >> 14 != 0 )
                                        value_int = value_int | ~((1 << 15) - 1);

                                    text3.setText(String.valueOf(value_int  + "°"));
                                    //val_set = Math.abs(value_int);


                                    if (value_int <= y_2_low)
                                    {
                                        image2.setImageResource(R.drawable.i2_b);
                                    }
                                    if (value_int <= y_1_low && value_int > y_2_low)
                                    {
                                        image2.setImageResource(R.drawable.i2_bb);
                                    }

                                    if (value_int < (y_1_low/2) && value_int > (y_1_low))
                                    {
                                        image2.setImageResource(R.drawable.i2_1);
                                    }
                                    if (value_int > (y_1_low/2) && value_int < (y_1/2))
                                    {
                                        image2.setImageResource(R.drawable.i2_2);
                                    }
                                    if (value_int > (y_1/2) && value_int < (y_1))
                                    {
                                        image2.setImageResource(R.drawable.i2_3);
                                    }

                                    if (value_int >= y_1 && value_int < y_2)
                                    {
                                        image2.setImageResource(R.drawable.i2_aa);
                                    }
                                    if (value_int >= y_2)
                                    {
                                        image2.setImageResource(R.drawable.i2_a);
                                    }

                                    //ось Z
                                    //value = swapIntToFloat(registerValues2[86], registerValues2[87], 0);
                                    value_int =  registerValues2[94];

                                    if ( value_int >> 14 != 0 )
                                        value_int = value_int | ~((1 << 15) - 1);

                                    text4.setText(String.valueOf(value_int  + "°"));
                                    //val_set = Math.abs(value_int);


                                    if (value_int <= z_2_low)
                                    {
                                        image3.setImageResource(R.drawable.i1_a);
                                    }
                                    if (value_int <= z_1_low && value_int > z_2_low)
                                    {
                                        image3.setImageResource(R.drawable.i1_aa);
                                    }

                                    if (value_int <= (z_1_low/1.5) && value_int > (z_1_low))
                                    {
                                        image3.setImageResource(R.drawable.i1_7);
                                    }
                                    if (value_int <= (z_1_low/3) && value_int > (z_1_low/1.5))
                                    {
                                        image3.setImageResource(R.drawable.i1_8);
                                    }

                                    if (value_int > (z_1_low/3) && value_int < (z_1/3)) //центр
                                    {
                                        image3.setImageResource(R.drawable.i1_1);
                                    }

                                    if (value_int >= (z_1/3) && value_int < (z_1/2))
                                    {
                                        image3.setImageResource(R.drawable.i1_2);
                                    }
                                    if (value_int >= (z_1/2) && value_int < (z_1/1.3))
                                    {
                                        image3.setImageResource(R.drawable.i1_4);
                                    }
                                    if (value_int >= (z_1/1.3) && value_int < (z_1))
                                    {
                                        image3.setImageResource(R.drawable.i1_3);
                                    }

                                    if (value_int >= z_1 && value_int < z_2)
                                    {
                                        image3.setImageResource(R.drawable.i1_bb);
                                    }
                                    if (value_int >= z_2)
                                    {
                                        image3.setImageResource(R.drawable.i1_b);
                                    }

                                    //Предупредительное реле
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

                                    //Аварийное реле
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


                                    //Температура
                                    value_int = registerValues3[34];
                                    texttemper.setText(String.valueOf(value_int));


                                    //Уставки
                                    //Виброскорость
                                    value = swapIntToFloat(registerValues3[20], registerValues3[21], 0);
                                    vibr_1 = value;
                                    value = round(value, 2);
                                    setText2.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues3[22], registerValues3[23], 0);
                                    vibr_2 = value;
                                    value = round(value, 2);
                                    setText4.setText(String.valueOf(value));

                                    //ось X
                                    //Ось X. Верхняя предупредительная уставка
                                    value = swapIntToFloat(registerValues2[60], registerValues2[61], 0);
                                    x_1 = value;
                                    setText6.setText(String.valueOf(value));

                                    //Ось X. Верхняя аварийная уставка
                                    value = swapIntToFloat(registerValues2[62], registerValues2[63], 0);
                                    x_2 = value;
                                    setText8.setText(String.valueOf(value));

                                    //Ось X. Нижняя предупредительная уставка
                                    value = swapIntToFloat(registerValues2[56], registerValues2[57], 0);
                                    x_1_low = value;
                                    setText24.setText(String.valueOf(value));

                                    //Ось X. Нижняя аварийная уставка
                                    value = swapIntToFloat(registerValues2[58], registerValues2[59], 0);
                                    x_2_low = value;
                                    setText26.setText(String.valueOf(value));

                                    //ось Y
                                    //Ось Y. Верхняя предупредительная уставка
                                    value = swapIntToFloat(registerValues2[80], registerValues2[81], 0);
                                    y_1 = value;
                                    setText10.setText(String.valueOf(value));

                                    //Ось Y. Верхняя аварийная уставка
                                    value = swapIntToFloat(registerValues2[82], registerValues2[83], 0);
                                    y_2 = value;
                                    setText12.setText(String.valueOf(value));

                                    //Ось Y. Нижняя предупредительная уставка
                                    value = swapIntToFloat(registerValues2[76], registerValues2[77], 0);
                                    y_1_low = value;
                                    setText28.setText(String.valueOf(value));

                                    //Ось Y. Нижняя аварийная уставка
                                    value = swapIntToFloat(registerValues2[78], registerValues2[79], 0);
                                    y_2_low = value;
                                    setText30.setText(String.valueOf(value));

                                    //ось Z
                                    //Ось Z. Верхняя предупредительная уставка
                                    value = swapIntToFloat(registerValues3[0], registerValues3[1], 0);
                                    z_1 = value;
                                    setText14.setText(String.valueOf(value));

                                    //Ось Z. Верхняя аварийная уставка
                                    value = swapIntToFloat(registerValues3[2], registerValues3[3], 0);
                                    z_2 = value;
                                    setText16.setText(String.valueOf(value));

                                    //Ось Z. Нижняя предупредительная уставка
                                    value = swapIntToFloat(registerValues2[96], registerValues2[97], 0);
                                    z_1_low = value;
                                    setText32.setText(String.valueOf(value));

                                    //Ось Z. Нижняя аварийная уставка
                                    value = swapIntToFloat(registerValues2[98], registerValues2[99], 0);
                                    z_2_low = value;
                                    setText34.setText(String.valueOf(value));


                                    //Виброускорение Х
                                    value = swapIntToFloat(registerValues3[54], registerValues3[55], 0);
                                    vibr_a_x = value;
                                    value = round(value, 2);
                                    setText36.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues3[60], registerValues3[61], 0);
                                    setText38.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues3[62], registerValues3[63], 0);
                                    setText40.setText(String.valueOf(value));

                                    //Виброускорение Y
                                    value = swapIntToFloat(registerValues3[74], registerValues3[75], 0);
                                    vibr_a_y = value;
                                    value = round(value, 2);
                                    setText42.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues3[80], registerValues3[81], 0);
                                    setText44.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues3[82], registerValues3[83], 0);
                                    setText46.setText(String.valueOf(value));

                                    //Виброускорение Z
                                    value = swapIntToFloat(registerValues3[94], registerValues3[95], 0);
                                    vibr_a_z = value;
                                    value = round(value, 2);
                                    setText48.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues4[0], registerValues4[1], 0);
                                    setText50.setText(String.valueOf(value));

                                    value = swapIntToFloat(registerValues4[2], registerValues4[3], 0);
                                    setText52.setText(String.valueOf(value));


                                    setText18.setText(String.valueOf(registerValues[84]));

                                    //setText20.setText(String.valueOf(registerValues[96]));

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
                                //text2.setText("-");
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









