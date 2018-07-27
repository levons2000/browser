package com.example.hp.mybrowser.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hp.mybrowser.R;
import com.example.hp.mybrowser.providers.OperationsProvider;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment {
    public WebView webView;

    public WebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        webView = view.findViewById(R.id.web);
        return view;
    }

    public void addWeb(String s) {
        String url;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        if (s.startsWith("https://")) {
            webView.loadUrl(s);
            url = s;
        } else {
            int k = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '.') {
                    k++;
                }
            }
            if (k == 1) {
                url = "https://" + s;
                webView.loadUrl(url);
            } else {
                url = "http://www.google.com/#q=" + s;
                webView.loadUrl(url);
            }

        }
        OperationsProvider.historyList.add(url);

    }

}
