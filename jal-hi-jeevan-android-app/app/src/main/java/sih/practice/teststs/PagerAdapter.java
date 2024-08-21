package sih.practice.teststs;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    Context mContext;
    String id;


    private String[] tabTitles;

    //Constructor to the class
    public PagerAdapter(FragmentManager fm, int tabCount, Context context,String id) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        mContext=context;
        this.id=id;
        String y = mContext.getString(R.string.yc);
        String ay = mContext.getString(R.string.pc);
        tabTitles= new String[]{y, ay};
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Fragment1 tab1 = new Fragment1(id);
                return tab1;
            case 1:
                Fragment2 tab2 = new Fragment2(id);
                return tab2;
            default:
                return null;
        }
    }

    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}