package com.example.findbuzz.findbuzz;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by akash on 11/1/18.
 */


public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    //ARRAYS

    public int[] slide_images={
            R.drawable.icon1,
            R.drawable.icon2,
            R.drawable.icon3
    };

    public String[] slide_headings={
            "head1","head2","head3"
    };

    public String[] slide_contents={
            "sodnhe djwbejf hjfbjds hfbvhd fhbjkf jkbjk jkf jdhvfdhv hjfd vju hfdjv dcfeff",
            "sodnhe djwbejf hjfbjds hfbvhd fhbjkf jkbjk jkf jdhvfdhv hjfd vju hfdjv dcfeff",
            "sodnhe djwbejf hjfbjds hfbvhd fhbjkf jkbjk jkf jdhvfdhv hjfd vju hfdjv dcfeff"
    };

    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.activity_slidelayout_onboarding,container,false);

        ImageView slideImageView =(ImageView) view.findViewById(R.id.slide_image);
        TextView slideheading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slidecontent = (TextView) view.findViewById(R.id.slide_content);

        slideImageView.setImageResource(slide_images[position]);
        slideheading.setText(slide_headings[position]);
        slidecontent.setText(slide_contents[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
