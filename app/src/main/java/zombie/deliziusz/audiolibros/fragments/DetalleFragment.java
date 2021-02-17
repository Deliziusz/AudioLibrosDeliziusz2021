package zombie.deliziusz.audiolibros.fragments;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import zombie.deliziusz.audiolibros.Aplicacion;
import zombie.deliziusz.audiolibros.Libro;
import zombie.deliziusz.audiolibros.MainActivity;
import zombie.deliziusz.audiolibros.R;
import java.io.IOException;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;


public class DetalleFragment extends Fragment implements View.OnTouchListener,
        MediaPlayer.OnPreparedListener, MediaController.MediaPlayerControl {
    private static final String CHANNEL_ID = "com.deliziusz.audiolibros.canal";
    public static String ARG_ID_LIBRO = "id_libro";
    MediaPlayer mediaPlayer;
    MediaController mediaController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        Bundle args = getArguments();
        if(args != null) {
            int position = args.getInt(ARG_ID_LIBRO);
            ponInfoLibro(position, view);
        } else {
            ponInfoLibro(0, view);
        }

        return view;
    }
    static String titulo = "";
    private void ponInfoLibro(int id, View view) {
        Libro libro = ((Aplicacion)getActivity().getApplication()).getVectoLibros().elementAt(id);
        ((TextView)view.findViewById(R.id.titulo)).setText(libro.titulo);
        ((TextView)view.findViewById(R.id.autor)).setText(libro.autor);
        ((ImageView)view.findViewById(R.id.portada)).setImageResource(libro.recursoImagen);
         titulo = (String) ((TextView)view.findViewById(R.id.titulo)).getText();

        view.setOnTouchListener(this);

        if(mediaPlayer != null)
            mediaPlayer.release();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaController = new MediaController(getActivity());
        Uri audio = Uri.parse(libro.urlAudio);
        try {
            mediaPlayer.setDataSource(getActivity(), audio);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            Log.e("AudioLibros", "ERROR: No se puede reproducir " + audio, e);
            Toast.makeText(getActivity(),"ERROR", Toast.LENGTH_SHORT);
        }
    }

    public void ponInfoLibro(int id) {
        ponInfoLibro(id, getView());
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d("AudioLibros", "Entramos en onPrepared");
        mediaPlayer.start();
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(getView());
        mediaController.setEnabled(true);
        mediaController.show();
        createNotification();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mediaController.show();
        return false;
    }


    @Override
    public void onStop() {
        Log.d("AudioLibros", "Entramos en onStop");
//        mediaController.hide();
//        try {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//        } catch (Exception e) {
//            Log.d("AudioLibros", "Error en mediaPlayer.stop()");
//        }

        super.onStop();
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        try {
            return mediaPlayer.getCurrentPosition();
        }
        catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public void pause() {
        Log.d("AudioLibros", "Entramos en onPause");
        mediaPlayer.pause();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }
    public void createNotification() {
        Log.d("AudioLibros", "Entramos en el metodo de la notificación");

        Intent notificationIntent = new Intent(getContext(), DetalleFragment.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(getContext());

        Notification notification = builder.setContentTitle("Reproduciendo AudioLibro")
                .setContentText(""+DetalleFragment.titulo.toString())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent).build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "notifica",
                    IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0, notification);
    }
    @Override
    public void start() {
        Log.d("AudioLibros", "Entramos en onStart");
        mediaPlayer.start();
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
    @Override
    public void onResume() {
        Log.d("AudioLibros", "Entramos en onResume");
        DetalleFragment fragment =
                (DetalleFragment)getFragmentManager()
                        .findFragmentById(R.id.detalle_fragment);

        if (fragment == null) {
            ((MainActivity)getActivity()).mostrarElementos(false);
        }
        super.onResume();
    }
}
