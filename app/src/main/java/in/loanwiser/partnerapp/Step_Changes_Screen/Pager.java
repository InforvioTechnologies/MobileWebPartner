package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Pager extends FragmentPagerAdapter {

    int tabCount;
    ViewPager viewPager;

    public Pager(FragmentManager fm, int tabCount, ViewPager viewPager) {
        super(fm);
        this.tabCount= tabCount;
        this.viewPager=viewPager;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                FragmentApplicant tab1 = new FragmentApplicant(viewPager);
                return tab1;
            case 1:
                FragmentCoApplicant1 tab2 = new FragmentCoApplicant1();
                return tab2;
            case 2:
                PropertyFragment tab3 = new  PropertyFragment();
                return tab3;

                default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return tabCount;
    }
}
