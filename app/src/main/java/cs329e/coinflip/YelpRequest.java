package cs329e.coinflip;

import android.os.AsyncTask;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONStringer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by cjs2599 on 4/18/16.
 */

// AsyncTask takes <Params, Progress, Result> as arguments

//    private void randomSelector(String response) {
//        JSONParser parser = new JSONParser();
//
//        try {
//
//            Object obj = parser.parse(response);
//
//            JSONObject json = (JSONObject) obj;
//
//
//
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//    }


