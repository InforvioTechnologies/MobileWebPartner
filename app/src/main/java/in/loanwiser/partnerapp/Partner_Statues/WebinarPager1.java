package in.loanwiser.partnerapp.Partner_Statues;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import in.loanwiser.partnerapp.Step_Changes_Screen.FragmentApplicant;
import in.loanwiser.partnerapp.Step_Changes_Screen.PropertyFragment;

public class WebinarPager1 extends FragmentStatePagerAdapter {

    int tabCount;
    ViewPager viewPager;

    public WebinarPager1(FragmentManager fm, int tabCount, ViewPager viewPager) {
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
                Loanwiser_Fragment tab1 = new Loanwiser_Fragment(viewPager);
                return tab1;
            case 1:
                Fragmentwebinar tab3 = new  Fragmentwebinar();
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
