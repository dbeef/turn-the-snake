/*
package com.devdbeef.nasc.android.ads;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.devdbeef.nasc.AdsController;
import com.devdbeef.nasc.Starter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AndroidLauncherWithAds extends AndroidApplication implements AdsController {
    
    private static final String BANNER_AD_UNIT_ID = "ca-app-pub-1736692144698426/6407614398";
      
    AdView bannerAd;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		 config.useWakelock = true;

	        // Create a gameView and a bannerAd AdView
	        View gameView = initializeForView(new Starter(this), config);
	        setupAds();
	         
	        // Define the layout
	        RelativeLayout layout = new RelativeLayout(this);
	        layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT,
	                ViewGroup.LayoutParams.MATCH_PARENT);
	        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
	                ViewGroup.LayoutParams.MATCH_PARENT,
	                ViewGroup.LayoutParams.WRAP_CONTENT);
	        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	        layout.addView(bannerAd, params);
	         
	        setContentView(layout);
	    }
	     
	    public void setupAds() {
	        bannerAd = new AdView(this);
	        bannerAd.setVisibility(View.INVISIBLE);
	        bannerAd.setBackgroundColor(Color.TRANSPARENT); // black
	        bannerAd.setAdUnitId(BANNER_AD_UNIT_ID);
	      //  bannerAd.setAdSize(AdSize.SMART_BANNER);
	        bannerAd.setAdSize(AdSize.LARGE_BANNER);
	        bannerAd.setY(0);
	        
	    }
	 
	    @Override
	    public void showBannerAd() {
	        runOnUiThread(new Runnable() {
	            @Override
	            public void run() {
	                bannerAd.setVisibility(View.VISIBLE);
	                AdRequest.Builder builder = new AdRequest.Builder();
	                AdRequest ad = builder.build();
	                bannerAd.loadAd(ad);
	              
	            }
	        });
	    }
	 
	    @Override
	    public void hideBannerAd() {
	        runOnUiThread(new Runnable() {
	            @Override
	            public void run() {
	                bannerAd.setVisibility(View.INVISIBLE);
	            }
	        });
	    }

	
		
	} 
*/