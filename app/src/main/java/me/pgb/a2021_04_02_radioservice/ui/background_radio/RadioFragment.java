package me.pgb.a2021_04_02_radioservice.ui.background_radio;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import me.pgb.a2021_04_02_radioservice.MainActivity;
import me.pgb.a2021_04_02_radioservice.R;
import me.pgb.a2021_04_02_radioservice.controllers.MediaPlayerHandler;
import me.pgb.a2021_04_02_radioservice.service.RadioService;
import me.pgb.a2021_04_02_radioservice.service.ServiceContainer;

public class RadioFragment extends Fragment {


    MainActivity main;

    private String TAG = "FRAGMENT_RADIO";

    private RadioViewModel radioViewModel;
    private Button stopBackgroundThread;
    private RecyclerView recyclerView;
    private MediaPlayerHandler mediaPlayerHandler;

    private static String link="";

    private Button radioToggleButton;
    private boolean radioOn=false;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        radioViewModel = new ViewModelProvider(this).get(RadioViewModel.class);
        View root = inflater.inflate(R.layout.fragment_radio, container, false);

        final TextView txt_name = root.findViewById(R.id.textView_RSName);
        final TextView txt_genre = root.findViewById(R.id.textView_RSGenre);
        final TextView txt_location = root.findViewById(R.id.textView_RSLocation);
        final TextView txt_link = root.findViewById(R.id.textView_RSlink);

        main = (MainActivity) getActivity();

        Bundle bundle = this.getArguments();

        String nameBundle = bundle.getString("name");
        String genreBundle = bundle.getString("genre");
        String locationBundle = bundle.getString("location");
        String linkBundle = bundle.getString("link");
        Log.i("LINK", " " + linkBundle);


        txt_name.setText(nameBundle);
        txt_genre.setText(genreBundle);
        txt_location.setText(locationBundle);
        txt_link.setText(linkBundle);

        link = txt_link.getText().toString();
        Log.i("LINK", " " + link);
        main.setRadioServiceURL(link);

        radioToggleButton = root.findViewById(R.id.radio_toggle_button);
        radioToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioOn){
                    radioOn= false;
                    ServiceContainer.radioService.radioOff();
                    radioToggleButton.setText("RADIO ON");
                   // Toast.makeText(getActivity().getApplicationContext(), "Radio OFF" , Toast.LENGTH_SHORT).show();
                } else{
                    radioOn = true;
                    ServiceContainer.radioService.radioOn();
                    radioToggleButton.setText("RADIO OFF");
                   // Toast.makeText(getActivity().getApplicationContext(), "Radio ON" , Toast.LENGTH_SHORT).show();
                }


            }
        });

        return root;
    }

    public static String getURL(){
        Log.i("LINK", "HERE " + link);
        return link;
    }


}