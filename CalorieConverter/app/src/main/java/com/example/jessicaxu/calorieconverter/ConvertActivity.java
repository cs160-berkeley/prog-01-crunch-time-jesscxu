package com.example.jessicaxu.calorieconverter;

import android.content.Context;
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
import android.text.SpannableString;
import android.text.Spannable;
import android.text.style.RelativeSizeSpan;

import java.text.DecimalFormat;
import java.util.ArrayList;




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConvertActivity.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConvertActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConvertActivity extends Fragment implements AdapterView.OnItemSelectedListener {

    ArrayList<String> activities;
    String selected_activity;
    double calories;
    ArrayList<String> units;
    EditText input_num;
    ImageView image;

    TextView calories_text;
    TextView unit;
    double num_cal;

    TextView a1;
    TextView a2;
    TextView a3;
    TextView a4;
    TextView a5;
    TextView a6;
    TextView a7;
    TextView a8;
    TextView a9;
    TextView a10;
    TextView a11;


    TextView n1;
    TextView n2;
    TextView n3;
    TextView n4;
    TextView n5;
    TextView n6;
    TextView n7;
    TextView n8;
    TextView n9;
    TextView n10;
    TextView n11;

    ImageView im1;
    ImageView im2;
    ImageView im3;
    ImageView im4;
    ImageView im5;
    ImageView im6;
    ImageView im7;
    ImageView im8;
    ImageView im9;
    ImageView im10;
    ImageView im11;

    Integer weight_input = 150;

    private OnFragmentInteractionListener mListener;

    public ConvertActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConvertActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static ConvertActivity newInstance(int weight) {
        ConvertActivity fragment = new ConvertActivity();
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
        View view = inflater.inflate(R.layout.fragment_convert, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        int n = adapter.getCount();
        activities = new ArrayList<String>();
        units = new ArrayList<String>();

        for (int i = 0; i < n; i++) {

            activities.add(((String)adapter.getItem(i)).toLowerCase());

            if (i < 4) {
                units.add((String) "reps");
            } else {
                units.add((String) "minutes");
            }

        }

        calories_text = (TextView) view.findViewById(R.id.calories);
        unit = (TextView) view.findViewById(R.id.rep_or_min);

        a1 = (TextView) view.findViewById(R.id.act1);
        a2 = (TextView) view.findViewById(R.id.act2);
        a3 = (TextView) view.findViewById(R.id.act3);
        a4 = (TextView) view.findViewById(R.id.act4);
        a5 = (TextView) view.findViewById(R.id.act5);
        a6 = (TextView) view.findViewById(R.id.act6);
        a7 = (TextView) view.findViewById(R.id.act7);
        a8 = (TextView) view.findViewById(R.id.act8);
        a9 = (TextView) view.findViewById(R.id.act9);
        a10 = (TextView) view.findViewById(R.id.act10);
        a11 = (TextView) view.findViewById(R.id.act11);


        im1 = (ImageView) view.findViewById(R.id.pic1);
        im2 = (ImageView) view.findViewById(R.id.pic2);
        im3 = (ImageView) view.findViewById(R.id.pic3);
        im4 = (ImageView) view.findViewById(R.id.pic4);
        im5 = (ImageView) view.findViewById(R.id.pic5);
        im6 = (ImageView) view.findViewById(R.id.pic6);
        im7 = (ImageView) view.findViewById(R.id.pic7);
        im8 = (ImageView) view.findViewById(R.id.pic8);
        im9 = (ImageView) view.findViewById(R.id.pic9);
        im10 = (ImageView) view.findViewById(R.id.pic10);
        im11 = (ImageView) view.findViewById(R.id.pic11);

        n1 = (TextView) view.findViewById(R.id.num1);
        n2 = (TextView) view.findViewById(R.id.num2);
        n3 = (TextView) view.findViewById(R.id.num3);
        n4 = (TextView) view.findViewById(R.id.num4);
        n5 = (TextView) view.findViewById(R.id.num5);
        n6 = (TextView) view.findViewById(R.id.num6);
        n7 = (TextView) view.findViewById(R.id.num7);
        n8 = (TextView) view.findViewById(R.id.num8);
        n9 = (TextView) view.findViewById(R.id.num9);
        n10 = (TextView) view.findViewById(R.id.num10);
        n11 = (TextView) view.findViewById(R.id.num11);

        input_num = (EditText) view.findViewById(R.id.input_number);
        input_num.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {


//                TextView myOutputBox = (TextView) findViewById(R.id.myOutputBox);
//                myOutputBox.setText(s);

                update();


            }
        });

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


        selected_activity = ((String) parent.getItemAtPosition(pos)).toLowerCase();

        if (pos < 4) {
            unit.setText("reps of ");
        }
        else {
            unit.setText("minutes of ");
        }

        update();


    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void update() {

        int selected_activity_index = activities.indexOf(selected_activity);

        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < activities.size(); i ++) {
            if (i != selected_activity_index) {
                indices.add(i);
            }
        }

        for (int i = 0; i < indices.size(); i += 1) {
            if (i == 0) { image = im1; }
            if (i == 1) { image = im2; }
            if (i == 2) { image = im3; }
            if (i == 3) { image = im4; }
            if (i == 4) { image = im5; }
            if (i == 5) { image = im6; }
            if (i == 6) { image = im7; }
            if (i == 7) { image = im8; }
            if (i == 8) { image = im9; }
            if (i == 9) { image = im10; }
            if (i == 10) { image = im11; }

            String activity = activities.get(indices.get(i)).toLowerCase();

            if (activity.equals("push ups")) {
                image.setImageResource(R.drawable.pushups_icon);
            }
            if (activity.equals("sit ups")) {
                image.setImageResource(R.drawable.situps_icon);
            }
            if (activity.equals("jumping jacks")) {
                image.setImageResource(R.drawable.jumpingjacks_icon);
            }
            if (activity.equals("squats")) {
                image.setImageResource(R.drawable.squats_icon);
            }
            if (activity.equals("jogging")) {
                image.setImageResource(R.drawable.jogging_icon);
            }
            if (activity.equals("walking")) {
                image.setImageResource(R.drawable.walking_icon);
            }
            if (activity.equals("cycling")) {
                image.setImageResource(R.drawable.cycling_icon);
            }
            if (activity.equals("stair climbing")) {
                image.setImageResource(R.drawable.stairclimbing_icon);
            }
            if (activity.equals("pull ups")) {
                image.setImageResource(R.drawable.pullups_icon);
            }
            if (activity.equals("leg lift")) {
                image.setImageResource(R.drawable.leglifts_icon);
            }
            if (activity.equals("plank")) {
                image.setImageResource(R.drawable.plank_icon);
            }
            if (activity.equals("swimming")) {
                image.setImageResource(R.drawable.swimming_icon);
            }
        }


        String num = input_num.getText().toString();
        if (num.toString().equals("")) {
            num = "0";
        }


        if (selected_activity.equals("push ups")) {
            calories = Float.parseFloat(num.toString()) / 3.5;
        }
        if (selected_activity.equals("sit ups")) {
            calories = Float.parseFloat(num.toString()) / 2;
        }
        if (selected_activity.equals("jumping jacks")) {
            calories = Float.parseFloat(num.toString()) * 10;
        }
        if (selected_activity.equals("jogging")) {
            calories = Float.parseFloat(num.toString()) * (100 / 12);
        }
        if (selected_activity.equals("squats")) {
            calories = Float.parseFloat(num.toString()) / 2.25;
        }
        if (selected_activity.equals("walking")) {
            calories = Float.parseFloat(num.toString()) / 3.5;
        }
        if (selected_activity.equals("cycling")) {
            calories = Float.parseFloat(num.toString()) / 2;
        }
        if (selected_activity.equals("walking")) {
            calories = Float.parseFloat(num.toString()) * 10;
        }
        if (selected_activity.equals("swimming")) {
            calories = Float.parseFloat(num.toString()) * (100 / 12);
        }
        if (selected_activity.equals("leg lifts")) {
            calories = Float.parseFloat(num.toString()) / 2.25;
        }
        if (selected_activity.equals("pull ups")) {
            calories = Float.parseFloat(num.toString()) * (100 / 12);
        }
        if (selected_activity.equals("stair climbing")) {
            calories = Float.parseFloat(num.toString()) / 2.25;
        }

        calories = calories + (weight_input - 150) / 10 * 5;

        if (Integer.parseInt(num) == 0) {
            calories = 0;
        }
        if (calories < 0) {
            calories = 1;
        }



        String num_calories = new DecimalFormat("#,###").format(calories);

        a1.setText(activities.get(indices.get(0)));
        a2.setText(activities.get(indices.get(1)));
        a3.setText(activities.get(indices.get(2)));
        a4.setText(activities.get(indices.get(3)));
        a5.setText(activities.get(indices.get(4)));
        a6.setText(activities.get(indices.get(5)));
        a7.setText(activities.get(indices.get(6)));
        a8.setText(activities.get(indices.get(7)));
        a9.setText(activities.get(indices.get(8)));
        a10.setText(activities.get(indices.get(9)));
        a11.setText(activities.get(indices.get(10)));


        calories_text.setText("burns  " + num_calories + "    calories");




        Spannable span = new SpannableString(calories_text.getText());
        span.setSpan(new RelativeSizeSpan(2f), 5, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        calories_text.setText(span);

        n1.setText(convertCalToActivity(activities.get(indices.get(0)), calories) + "  " + units.get(indices.get(0)));
        n2.setText(convertCalToActivity(activities.get(indices.get(1)), calories) + "  " + units.get(indices.get(1)));
        n3.setText(convertCalToActivity(activities.get(indices.get(2)), calories) + "  " + units.get(indices.get(2)));
        n4.setText(convertCalToActivity(activities.get(indices.get(3)), calories) + "  " + units.get(indices.get(3)));
        n5.setText(convertCalToActivity(activities.get(indices.get(4)), calories) + "  " + units.get(indices.get(4)));
        n6.setText(convertCalToActivity(activities.get(indices.get(5)), calories) + "  " + units.get(indices.get(5)));
        n7.setText(convertCalToActivity(activities.get(indices.get(6)), calories) + "  " + units.get(indices.get(6)));
        n8.setText(convertCalToActivity(activities.get(indices.get(7)), calories) + "  " + units.get(indices.get(7)));
        n9.setText(convertCalToActivity(activities.get(indices.get(8)), calories) + "  " + units.get(indices.get(8)));
        n10.setText(convertCalToActivity(activities.get(indices.get(9)), calories) + "  " + units.get(indices.get(9)));
        n11.setText(convertCalToActivity(activities.get(indices.get(10)), calories) + "  " + units.get(indices.get(10)));

    }
    public int convertCalToActivity(String activity, double calories){

        if (activity.equals("push ups")) {
            num_cal = calories * 3.5;
            if (num_cal < 0) { return 1; }
            if (num_cal == 0) { return 0; }
        }
        if (activity.equals("sit ups")) {
            num_cal = calories * 2;
            if (num_cal < 0) { return 1; }
            if (num_cal == 0) { return 0; }
        }
        if (activity.equals("squats")) {
            num_cal = calories * 2.25;
            if (num_cal < 0) { return 1; }
            if (num_cal == 0) { return 0; }
        }

        if (activity.equals("leg lifts")) {
            num_cal = calories / 4;
            if (num_cal < 0) { return 1; }
            if (num_cal == 0) { return 0; }
        }
        if (activity.equals("plank")) {
            num_cal = calories / 4;
            if (num_cal < 0) { return 1; }
            if (num_cal == 0) { return 0; }
        }
        if (activity.equals("jumping jacks")) {
            num_cal = calories / 10;
            if (num_cal < 0) { return 1; }
            if (num_cal == 0) { return 0; }
        }
        if (activity.equals("pull ups")) {
            num_cal = calories;
            if (num_cal < 0) { return 1; }
            if (num_cal == 0) { return 0; }
        }
        if (activity.equals("cycling")) {
            num_cal = calories / (100 / 12);
            if (num_cal < 0) { return 1; }
            if (num_cal == 0) { return 0; }
        }
        if (activity.equals("walking")) {
            num_cal = calories / 5;
            if (num_cal < 0) { return 1; }
            if (num_cal == 0) { return 0; }
        }
        if (activity.equals("jogging")) {
            num_cal = calories / (100 / 12);
            if (num_cal < 0) { return 1; }
            if (num_cal == 0) { return 0; }
        }
        if (activity.equals("swimming")) {
            num_cal = calories / (100 / 13);
            if (num_cal < 0) { return 1; }
            if (num_cal == 0) { return 0; }
        }
        if (activity.equals("stair climbing")) {
            num_cal = calories / (100 / 15);
            if (num_cal < 0) { return 1; }
            if (num_cal == 0) { return 0; }
        }

        String cal = new DecimalFormat("#").format(num_cal);
        return Integer.parseInt(cal);

    }
}
