package com.example.omfg.test_task_user_list.Gui;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.omfg.test_task_user_list.Database.DBHelper;
import com.example.omfg.test_task_user_list.Database.InputIntoDB;
import com.example.omfg.test_task_user_list.Logic.ApiService;
import com.example.omfg.test_task_user_list.Logic.GetRealId;
import com.example.omfg.test_task_user_list.Logic.RetroClient;
import com.example.omfg.test_task_user_list.Logic.SortListAlbeticaly;
import com.example.omfg.test_task_user_list.Objects.ListData;
import com.example.omfg.test_task_user_list.Logic.OnDownLoadComplated;
import com.example.omfg.test_task_user_list.Objects.User;
import com.example.omfg.test_task_user_list.Objects.UserList;
import com.example.omfg.test_task_user_list.R;
import com.example.omfg.test_task_user_list.Logic.UserParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by omfg on 16.11.2017.
 */

public class MainActivityFragment extends Fragment implements OnDownLoadComplated {
    ArrayList<User> lists = new ArrayList<>();
    ArrayList<ListData> sotredList = new ArrayList<>();
    ListView listView;
    boolean phone;
    CustomAdapter customAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new DBHelper(getContext());
//        setRetainInstance(true);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserParser userParser = new UserParser(this);
        userParser.execute();
        Bundle bundle = getArguments();

        this.phone = bundle.getBoolean("phone");
        return inflater.inflate(R.layout.mainfragment, null);


    }

    @Override
    public void downloaded(final ArrayList<ListData> lists) {

        //не так красиво как могло быть

//        Runnable insertIntoDB = new InputIntoDB(getContext(), lists);
//        new Thread(insertIntoDB).start();


//        this.lists = lists;
//        SortListAlbeticaly sortListAlbeticaly = new SortListAlbeticaly();
//        sotredList = sortListAlbeticaly.sort(lists);

//        customAdapter = new CustomAdapter(getContext(), sotredList);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) getActivity().findViewById(R.id.mainListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//               Toast.makeText(getActivity(),i+" "+l,Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                GetRealId getRealId = new GetRealId();
//                i = getRealId.getID(lists, sotredList.get(i).getName());

                bundle.putInt("i", i);
                bundle.putBoolean("phone",phone);
                //     Log.d("name from list "," "+lists.get(i).getName());
                bundle.putString("name", lists.get(i).getName());
                MessageFragment messageFragment = new MessageFragment();
                FragmentTransaction tr = getFragmentManager().beginTransaction();
                tr.addToBackStack(null);
                messageFragment.setArguments(bundle);
                if (phone) {
                    tr.replace(R.id.mainFrame, messageFragment);
                    tr.commit();
                } else if (!phone) {
                    tr.replace(R.id.message_fragment, messageFragment);
                    tr.commit();
                }

            }
        });

        this.lists = lists;
        ApiService apiService = RetroClient.getApiService();
        Call<List<UserList>> call = apiService.getMyJSON();
        call.enqueue(new Callback<List<UserList>>() {

public void onResponse(Call<List<UserList>> call, Response<List<UserList>> response) {
    String body = String.valueOf(response.body());
    Log.d("body", "body: " + body);
    lists = response.body().get(0).getUser();

    customAdapter = new CustomAdapter(getContext(), lists);
    listView.setAdapter(customAdapter);
}

            @Override
            public void onFailure(Call<List<UserList>> call, Throwable t) {
                Toast.makeText(getContext(), "Faild to load data", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
//Вот тут изначально планировалось затолкать вызов бд
    }
    public void onSaveInstanceState(Bundle savedState) {

        super.onSaveInstanceState(savedState);




    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
