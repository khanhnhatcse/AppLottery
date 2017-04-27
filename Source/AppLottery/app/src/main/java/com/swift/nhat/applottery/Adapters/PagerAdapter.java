package com.swift.nhat.applottery.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.swift.nhat.applottery.Activities.DetailFragment;
import com.swift.nhat.applottery.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nhat on 4/25/17.
 */


public class PagerAdapter  extends FragmentStatePagerAdapter {

    private Map<String, ArrayList<String>> data;

    public PagerAdapter(FragmentManager fm, Map<String, ArrayList<String>> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        Utils<ArrayList<String>> utils = new Utils<>();
        List<String> keys = utils.getAllKeys(data);
        Fragment frag= new DetailFragment();
        Bundle bundle= new Bundle();
        bundle.putStringArrayList("data", data.get(keys.get(position)));
        frag.setArguments(bundle);
        return frag;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {

        Utils<ArrayList<String>> utils = new Utils<>();
        List<String> keys = utils.getAllKeys(data);
        String title = "Giải " + keys.get(position);

        return title;
    }

}
