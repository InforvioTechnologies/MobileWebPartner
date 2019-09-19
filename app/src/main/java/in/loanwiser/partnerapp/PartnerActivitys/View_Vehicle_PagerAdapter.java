package in.loanwiser.partnerapp.PartnerActivitys;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class View_Vehicle_PagerAdapter extends FragmentStatePagerAdapter {
    private static int TAB_COUNT = 2;

    public View_Vehicle_PagerAdapter(FragmentManager fm) {

        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return Add_Home_loan.newInstance();

            case 1:
                return Add_Vehicle_Applicant1.newInstance();

        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return Add_Home_loan.TITLE;

            case 1:
                return Add_Vehicle_Applicant1.TITLE;

        }
        return super.getPageTitle(position);
    }

}