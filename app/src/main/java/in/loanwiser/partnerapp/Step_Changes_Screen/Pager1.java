package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Pager1 extends FragmentPagerAdapter {

    int tabCount;

    public Pager1(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                FragmentApplicant tab1 = new FragmentApplicant();
                return tab1;
            case 1:
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
