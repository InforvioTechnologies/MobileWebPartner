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


public class TwoFragment extends Fragment {



    RecyclerView expanderRecyclerView;

    List<Creditearnings> creditearninglist;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_two, container, false);
        expanderRecyclerView =v.findViewById(R.id.recyclerView);

        showData();
        initRecyclerView();

        return v;
    }

    private void initRecyclerView() {
        // MovieAdapter movieAdapter = new MovieAdapter(movieList);
        CreditearningAdapter creditearningAdapter=new CreditearningAdapter(creditearninglist);
        expanderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        expanderRecyclerView.setAdapter(creditearningAdapter);
    }

    private void showData(){
        creditearninglist=new ArrayList<>();

        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));

    }
}
