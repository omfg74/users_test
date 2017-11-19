package com.example.omfg.test_task_user_list.Gui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.omfg.test_task_user_list.Logic.MessageParser;
import com.example.omfg.test_task_user_list.Logic.MessageSelector;
import com.example.omfg.test_task_user_list.Objects.Message;
import com.example.omfg.test_task_user_list.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MessageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment implements MessageSelector {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int i;
    TextView nameTextView,titleTextView,bodyTextView;
    ListView messageListView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private String name;

    public MessageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        nameTextView.setText(name);
//        nameTextView.setText(name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.message_fragment_layout,container,false);

        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        int i = bundle.getInt("i");
        String name = bundle.getString("name");
        Log.d("Name from bundrl ", name);
        Log.d("I"," "+i);
        this.i =i;
        this.name = name;
//        nameTextView = view.findViewById(R.id.nameTextView);
        messageListView = view.findViewById(R.id.messages_ListView);
//        titleTextView = view.findViewById(R.id.tittle_textView);
//        bodyTextView = view.findViewById(R.id.body_textView);
        MessageParser messageParser = new MessageParser(this,i);
        messageParser.execute();
        return view;




    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;

    }

    @Override
    public void msg(ArrayList<Message> msg) {

       MessageAdapter messageAdapter = new MessageAdapter(getContext(),msg);
       messageListView.setAdapter(messageAdapter);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();

    }
}
