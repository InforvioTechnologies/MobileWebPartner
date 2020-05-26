package in.loanwiser.partnerapp.Partner_Statues;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import in.loanwiser.partnerapp.My_Earnings.OneFragment;
import in.loanwiser.partnerapp.My_Earnings.TwoFragment;
import in.loanwiser.partnerapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyearningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyearningFragment extends  Fragment {


    public MyearningFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_myearning, container, false);
        TabLayout tabLayout =v.findViewById(R.id.tabs);
        ViewPager viewPager = v.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        return v;

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new OneFragment(), "CASH EARNINGS");
        adapter.addFragment(new TwoFragment(), "CREDIT COINS");
        viewPager.setAdapter(adapter);
    }



    static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mList = new ArrayList<>();
        private final List<String> mTitleList = new ArrayList<>();
        ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }
        @Override
        public Fragment getItem(int i) {
            return mList.get(i);
        }
        @Override
        public int getCount() {
            return mList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mList.add(fragment);
            mTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }
}