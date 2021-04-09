package me.pgb.a2021_04_02_radioservice.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.pgb.a2021_04_02_radioservice.R;
import me.pgb.a2021_04_02_radioservice.controllers.RecyclerAdapter;
import me.pgb.a2021_04_02_radioservice.models.RadioStation;

public class DashboardFragment extends Fragment {


    public DashboardViewModel dashboardViewModel;
    public RecyclerView recyclerView;
    public ArrayList<RadioStation> stationList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        stationList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.recyclerView);

        setUserInfo();
        setAdapter();

        return root;
    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(stationList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }


    private void setUserInfo() {
        stationList.add(new RadioStation("http://stream.revma.ihrhls.com/zc185/hls.m3u8",
                "KIIS 102.7 FM",
                "Hot Adult Contemporary",
                " Los Angeles, CA"));
        stationList.add(new RadioStation("http://stream.whus.org:8000/whusfm",
                "WHUS 91.7 FM",
                "College/University",
                "Storrs, CT"));
        stationList.add(new RadioStation(
                "http://usa5.fastcast4u.com:16224/stream",
                "Pinoy Radio",
                "Pop",
                "Philippines"));
        stationList.add(new RadioStation(
                "http://stream.zeno.fm/agcn309f9ueuv",
                "KIIS FM 97.7",
                "Adult Contemporary",
                "Guatemala"));

    }
}