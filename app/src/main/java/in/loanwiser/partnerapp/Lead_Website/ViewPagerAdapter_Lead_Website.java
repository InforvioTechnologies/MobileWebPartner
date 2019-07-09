package in.loanwiser.partnerapp.Lead_Website;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.loanwiser.partnerapp.PartnerActivitys.Add_Applicant;
import in.loanwiser.partnerapp.PartnerActivitys.Add_Home_loan;

public class ViewPagerAdapter_Lead_Website extends FragmentStatePagerAdapter {
    private static int TAB_COUNT = 2;

    public ViewPagerAdapter_Lead_Website(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return Add_Home_loan_Lead_Website.newInstance();

            case 1:
                return Add_Applicant_Lead_Website.newInstance();



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
                return Add_Applicant.TITLE;

        }
        return super.getPageTitle(position);
    }

}