package com.example.sendmessagebinding.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sendmessagebinding.SendMessageApplication;
import com.example.sendmessagebinding.data.Message;
import com.example.sendmessagebinding.databinding.ActivitySendMessageBinding;

/**
 * <h1>Proyecto SendMessage</h1>
 * En este proyecto aprenderemos a realizar las siguientes operaciones
 * <ol>
 *     <li>Crear un evento en un componente Button en código XML</li>
 *     <li>Crear un escuchador/listener del evento <code>onClick()</code></li>
 *     <li>Crear un intent y un elemento Bumdle para pasar información entre Activity</li>
 *     <li>El ciclo de vida de una Activity</li>
 *     <li>Manejar la pila de actividades</li>
 * </ol>
 *
 * @author Christian Gaitán
 * @version 1.0
 * @see android.widget.Button
 * @see android.app.Activity
 * @see android.content.Intent
 * @see android.os.Bundle
 */
public class SendMessageActivity extends AppCompatActivity {
    private static final String TAG = "SendMessageActivity";
    private SendMessageOnClickListener delegate;
    private ActivitySendMessageBinding binding;

    //region CICLO DE VIDA DE LA ACTIVITY

    /**
     * Método que se ejecuta cuando se crea la Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySendMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*// Se inicializa el delegado
        delegate = new SendMessageOnClickListener();
        // Se establece el vínculo entre el botón y el delegado
        btSend.setOnClickListener(delegate);*/

        // Establecer el Listener OnClickListener al botón mediante una clase anónima que implementa la interfaz View.OnClickListener
        binding.setMessage(new Message(((SendMessageApplication)getApplication()).getUser()));
        binding.btSend.setOnClickListener(view -> sendMessage());
        //view -> Toast.makeText(SendMessageActivity.this, "Esto es a través de una clase anónima", Toast.LENGTH_SHORT).show());

        Log.d(TAG, "SendMessageActivity -> onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "SendMessageActivity -> onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "SendMessageActivity -> onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "SendMessageActivity -> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "SendMessageActivity -> onStop()");
    }

    /**
     * Este método se ejecuta cuando se destruye la Activity
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        Log.d(TAG, "SendMessageActivity -> onDestroy()");
    }
    //endregion


    /**
     * Este método es el que se llama cuando se pulsa osbre el botón btSend definido en el XML
     * android:onClick="sendMessage"
     *
     * @param view
     */
    public void sendMessage(View view) {
        sendMessage();
    }

    /**
     * Este método es el que se llama cuando se pulsa osbre el botón btSend definido en el XML
     * android:onClick="sendMessage"
     */
    public void sendMessage() {
        //TODO Se modificará este ejercicio para estudiar ViewBinding
        //Toast.makeText(this, "Hemos pulsado el botón",  Toast.LENGTH_SHORT).show();

        // 1. Crear el contenedor para añadir los datos
        Bundle bundle = new Bundle();

        // 1.1 Pasar dato a dato
        //bundle.putString("user", binding.getMessage().getUser().getName());
        //bundle.putString("message", binding.getMessage().getContent());

        // 1.2 Crear un objeto Message
        bundle.putParcelable("message", binding.getMessage());

        // 2. Vamos a crear el objeto Intent explícito porque se conoce la Actividad destino
        Intent intent = new Intent(this, ViewMessageActivity.class);
        intent.putExtras(bundle);

        // 3. Se inicia una transición entre una vista y otra mediante StartActivity
        startActivity(intent);
    }

    /**
     * Esta clase NO ANÓNIMA implementa el Listener que responde siempre al evento OnClick
     */
    class SendMessageOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Toast.makeText(SendMessageActivity.this, "Esto es a través de un delegado", Toast.LENGTH_SHORT).show();
        }
    }
}