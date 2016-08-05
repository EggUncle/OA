package rixin.app.officeauto.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import rixin.app.officeauto.fragment.ContactFragment;

/**
 * Created by egguncle on 16.8.4.
 */
public class ContactFragmentAdapter  extends FragmentStatePagerAdapter {
    private Fragment[] fragments = new Fragment[1];

    //private Context context;

    public ContactFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        init(context);
    }

    private void init(Context context) {
        fragments[0] = new ContactFragment(context);

    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
