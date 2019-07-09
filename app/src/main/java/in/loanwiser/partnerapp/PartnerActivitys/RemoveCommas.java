package in.loanwiser.partnerapp.PartnerActivitys;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class RemoveCommas {
    Splitter splitter = Splitter.on(',').omitEmptyStrings().trimResults();
    Joiner joiner = Joiner.on(',').skipNulls();

    public String cleanUpCommas(String string) {
        return joiner.join(splitter.split(string));
    }
}
