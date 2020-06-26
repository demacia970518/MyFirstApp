package com.example.myfirstapp;

import android.content.SearchRecentSuggestionsProvider;

public class RecentSuggestionsProvider extends SearchRecentSuggestionsProvider {
    public final static  String AUTHORITY="com.example.myfirstapp";
    public final static  int MODE =DATABASE_MODE_QUERIES|DATABASE_MODE_2LINES;

    public RecentSuggestionsProvider(){
        setupSuggestions(AUTHORITY,MODE);
    }



     ; ;


}
