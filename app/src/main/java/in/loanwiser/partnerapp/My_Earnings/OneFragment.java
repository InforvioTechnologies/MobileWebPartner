package in.loanwiser.partnerapp.My_Earnings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.loanwiser.partnerapp.R;


public class OneFragment extends Fragment {

    RecyclerView expanderRecyclerView;
    List<Cashearnings> cashearningsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_one, container, false);
        expanderRecyclerView =v.findViewById(R.id.recyclerView);
        expanderRecyclerView.setNestedScrollingEnabled(true);
        showData();
        initRecyclerView();
        return v;
    }
    private void initRecyclerView() {
        CashearningAdapter cashearningAdapter=new CashearningAdapter(cashearningsList);
        expanderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        expanderRecyclerView.setAdapter(cashearningAdapter);
    }

    private void showData(){
        cashearningsList=new ArrayList<>();
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));

    }


    



}
