package com.example.jessicaxu.calorieconverter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class CalorieConverter extends Fragment implements AdapterView.OnItemSelectedListener {

    ArrayList<String> activities;
    String selected_activity;
    double calories;
    ArrayList<String> units;
    EditText input_num;
    ImageView image;

    TextView calories_text;
    TextView unit;

    TextView first;
    TextView second;
    TextView third;
    TextView fourth;

    ImageView im1;
    ImageView im2;
    ImageView im3;
    ImageView im4;

    EditText input_cal;


    TextView pushups;
    TextView situps;
    TextView squats;
    TextView leglifts;
    TextView plank;
    TextView jumpingjacks;
    TextView pullups;
    TextView cycling;
    TextView walking;
    TextView jogging;
    TextView swimming;
    TextView staircliming;

    Integer weight_input = 150;
    double cal;


    private OnFragmentInteractionListener mListener;

    public CalorieConverter() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConvertActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static CalorieConverter newInstance(int weight) {
        CalorieConverter fragment = new CalorieConverter();
        Bundle args = new Bundle();
        args.putInt("weight", weight);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            weight_input = getArguments().getInt("weight");

//            System.out.println(weight_input);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calorie_converter, container, false);


        ArrayList<String> activities = new ArrayList<String>();
        pushups = (TextView) view.findViewById(R.id.pushups);
        activities.add("push ups");
        situps = (TextView) view.findViewById(R.id.situps);
        activities.add("sit ups");
        squats = (TextView) view.findViewById(R.id.squats);
        activities.add("squats");
        leglifts = (TextView) view.findViewById(R.id.leglifts);
        activities.add("leg lifts");
        plank = (TextView) view.findViewById(R.id.plank);
        activities.add("plank");
        jumpingjacks = (TextView) view.findViewById(R.id.jumpingjacks);
        activities.add("jumping jacks");
        pullups = (TextView) view.findViewById(R.id.pullups);
        activities.add("pull ups");
        cycling = (TextView) view.findViewById(R.id.cycling);
        activities.add("cycling");
        walking = (TextView) view.findViewById(R.id.walking);
        activities.add("walking");
        jogging = (TextView) view.findViewById(R.id.jogging);
        activities.add("jogging");
        swimming = (TextView) view.findViewById(R.id.swimming);
        activities.add("swimming");
        staircliming = (TextView) view.findViewById(R.id.stairclimbing);
        activities.add("stair climbing");


        input_cal = (EditText) view.findViewById(R.id.input_calories);
        input_cal.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                update();
            }
        });
        update();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)


        selected_activity = (String) parent.getItemAtPosition(pos);

        if (pos != 4 && pos !=2) {
            unit.setText("rep");
        }
        else {
            unit.setText("min");
        }

        update();


    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void update() {



        String calories = input_cal.getText().toString();
        if (calories.toString().equals("")) {
            calories = "0";
        } else {
            calories = String.valueOf(Integer.parseInt(calories) - (weight_input - 150) / 10 * 5);
        }


        String num_rep_or_min = new DecimalFormat("#").format(convertCalToActivity("push ups",
                Integer.parseInt(calories)));


        pushups.setText(num_rep_or_min + " Push Ups");
        num_rep_or_min = new DecimalFormat("#").format(convertCalToActivity("sit ups",
                Integer.parseInt(calories)));
        situps.setText(num_rep_or_min + " Sit Ups");
        num_rep_or_min = new DecimalFormat("#").format(convertCalToActivity("squats",
                Integer.parseInt(calories)));
        squats.setText(num_rep_or_min + " Squats");
        num_rep_or_min = new DecimalFormat("#").format(convertCalToActivity("leg lifts",
                Integer.parseInt(calories)));
        leglifts.setText(num_rep_or_min + " minutes of Leg Lifts");
        num_rep_or_min = new DecimalFormat("#").format(convertCalToActivity("plank",
                Integer.parseInt(calories)));
        plank.setText(num_rep_or_min + " minutes of Plank");
        num_rep_or_min = new DecimalFormat("#").format(convertCalToActivity("jumping jacks",
                Integer.parseInt(calories)));
        jumpingjacks.setText(num_rep_or_min + " Jumping Jacks");
        num_rep_or_min = new DecimalFormat("#").format(convertCalToActivity("pull ups",
                Integer.parseInt(calories)));
        pullups.setText(num_rep_or_min + " Pull Ups");
        num_rep_or_min = new DecimalFormat("#").format(convertCalToActivity("cycling",
                Integer.parseInt(calories)));
        cycling.setText(num_rep_or_min + " minutes of Cycling");
        num_rep_or_min = new DecimalFormat("#").format(convertCalToActivity("walking",
                Integer.parseInt(calories)));
        walking.setText(num_rep_or_min + " minutes of Walking");
        num_rep_or_min = new DecimalFormat("#").format(convertCalToActivity("jogging",
                Integer.parseInt(calories)));
        jogging.setText(num_rep_or_min + " minutes of Jogging");
        num_rep_or_min = new DecimalFormat("#").format(convertCalToActivity("swimming",
                Integer.parseInt(calories)));
        swimming.setText(num_rep_or_min + " minutes of Swimming");
        num_rep_or_min = new DecimalFormat("#").format(convertCalToActivity("stair climbing",
                Integer.parseInt(calories)));
        staircliming.setText(num_rep_or_min + " minutes of Stair Climbing");

    }



    public double convertCalToActivity(String activity, int calories){
        if (activity.equals("push ups")) {
            cal = calories * 3.5;
            if (cal < 0) { return 1; }
        }
        if (activity.equals("sit ups")) {
            cal = calories * 2;
            if (cal < 0) { return 1; }
        }
        if (activity.equals("squats")) {
            cal = calories * 2.25;
            if (cal < 0) { return 1; }
        }
        if (activity.equals("leg lifts")) {
            cal = calories / 4;
            if (cal < 0) { return 1; }
        }
        if (activity.equals("plank")) {
            cal = calories / 4;
            if (cal < 0) { return 1; }
        }
        if (activity.equals("jumping jacks")) {
            cal = calories / 10;
            if (cal < 0) { return 1; }
        }
        if (activity.equals("pull ups")) {
            cal = calories;
            if (cal < 0) { return 1; }
        }
        if (activity.equals("cycling")) {
            cal = calories / (100 / 12);
            if (cal < 0) { return 1; }
        }
        if (activity.equals("walking")) {
            cal = calories / 5;
            if (cal < 0) { return 1; }
        }
        if (activity.equals("jogging")) {
            cal = calories / (100 / 12);
            if (cal < 0) { return 1; }
        }
        if (activity.equals("swimming")) {
            cal = calories / (100 / 13);
            if (cal < 0) { return 1; }
        }
        if (activity.equals("stair climbing")) {
            cal = calories / (100 / 15);
            if (cal < 0) { return 1; }
        }
        return cal;
    }
}
