package com.example.omfg.test_task_user_list.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.omfg.test_task_user_list.Logic.DbInjector;
import com.example.omfg.test_task_user_list.Objects.ListData;

import java.util.ArrayList;

/**
 * Created by omfg on 18.11.2017.
 */

public class InputIntoDB implements Runnable{

    DBHelper dbHelper;
SQLiteDatabase db;
ContentValues contentValues;
ArrayList<ListData> lists;
ArrayList<ListData> dataList;
//ArrayList<ListData> insertList;
Cursor cursor;
Context context;
DbInjector dbInjector;
    public InputIntoDB(Context context, ArrayList<ListData> lists) {
this.lists = lists;
this.context = context;
this.dbInjector = dbInjector;
    }

    @Override
    public void run() {
        dbHelper = new DBHelper(context);
        db =dbHelper.getWritableDatabase();
      dataList = new ArrayList<>();
      cursor = db.query(dbHelper.TABLE_USERS,null,
              null,
              null,
              null,
              null,
              null,
              null);
      if (cursor.moveToFirst()){
          do{
              ListData data = new ListData();

              data.setId(cursor.getInt(1));
              data.setName(cursor.getString(2));
              data.setUserName(cursor.getString(3));
              data.setEmail(cursor.getString(4));
              data.setPhone(cursor.getLong(5));
              data.setSite(cursor.getString(6));
              data.setStreet(cursor.getString(7));
              data.setSuite(cursor.getString(8));
              data.setCity(cursor.getString(9));
              data.setZipcode(cursor.getLong(10));
              data.setLat(cursor.getDouble(11));
              data.setLng(cursor.getDouble(12));
              data.setCompanyName(cursor.getString(13));
              data.setCatchPhrase(cursor.getString(14));
              data.setBs(cursor.getString(15));


              dataList.add(data);

          }while (cursor.moveToNext());
          cursor.close();
      }

      if (dataList.size()==0){
          ArrayList<ListData>insertList = lists;
insertWhatWeVGot(insertList);


      }else {
          //Прескорбно, что нет технической возможности проверить этот блок. Он отвечает за актуализацию информации.
          ArrayList<ListData> instartList= new ArrayList<>();
//          ArrayList<Integer>checkList= new ArrayList<>();//сомнитьельная надобность этой конструкции
          for (int i = 0; i <lists.size() ; i++) {
              int id = lists.get(i).getId();
              for (int j = 0; j <dataList.size() ; j++) {
                  if(id==dataList.get(j).getId()){
                      //проверка на соответствие всех данных
                      getAndCheck(i+1);
                      break;
                  }else {
                      if(j==dataList.size()-1){
//                          checkList.add(i);
                          ListData insertData = lists.get(i);
                          instartList.add(insertData);
                      }
                  }
              }
          }

          if(lists.size()>dataList.size()){
              for (int i = 0; i <lists.size()-dataList.size() ; i++) {
//                  checkList.add(dataList.size()+i+1);
                  ListData insertData = lists.get(i);
                  instartList.add(insertData);
              }


          }
          insertWhatWeVGot(instartList);
      }

    }
    private void insertWhatWeVGot(ArrayList<ListData>insertList){
        contentValues  = new ContentValues();
        for (int i = 0; i <insertList.size() ; i++) {
            contentValues.put(DBHelper.COLUMN_ID,insertList.get(i).getId());
            contentValues.put(DBHelper.COLUMN_NAME,insertList.get(i).getName());
            contentValues.put(DBHelper.COLUMN_USERNAME,insertList.get(i).getUserName());
            contentValues.put(DBHelper.COLUMN_EMAIL,insertList.get(i).getEmail());
            contentValues.put(DBHelper.COLUMN_PHONE,insertList.get(i).getPhone());
            contentValues.put(DBHelper.COLUMN_WEBSITE,insertList.get(i).getSite());
            contentValues.put(DBHelper.COLUMN_STREET,insertList.get(i).getStreet());
            contentValues.put(DBHelper.COLUMN_SUITE,insertList.get(i).getSuite());
            contentValues.put(DBHelper.COLUMN_CITY,insertList.get(i).getCity());
            contentValues.put(DBHelper.COLUMN_ZIPCODE,insertList.get(i).getZipcode());
            contentValues.put(DBHelper.COLUMN_LAT,insertList.get(i).getLat());
            contentValues.put(DBHelper.COLUMN_LNG,insertList.get(i).getLng());
            contentValues.put(DBHelper.COLUMN_COMPANY_NAME,insertList.get(i).getCompanyName());
            contentValues.put(DBHelper.COLUMN_CATCH_PHRASE,insertList.get(i).getCatchPhrase());
            contentValues.put(DBHelper.COLUMN_BS,insertList.get(i).getBs());
            db.insert(DBHelper.TABLE_USERS,null,contentValues);
            contentValues.clear();


        }
        dbHelper.close();
    }
    private void getAndCheck(int i){
        cursor = db.query(dbHelper.TABLE_USERS,null,
                DBHelper.COLUMN_ID+  " = ?",
                new String[]{String.valueOf(i)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()){
            ListData data = new ListData();



                data.setId(cursor.getInt(1));
                data.setName(cursor.getString(2));
                data.setUserName(cursor.getString(3));
                data.setEmail(cursor.getString(4));
                data.setPhone(cursor.getLong(5));
                data.setSite(cursor.getString(6));
                data.setStreet(cursor.getString(7));
                data.setSuite(cursor.getString(8));
                data.setCity(cursor.getString(9));
                data.setZipcode(cursor.getLong(10));
                data.setLat(cursor.getDouble(11));
                data.setLng(cursor.getDouble(12));
                data.setCompanyName(cursor.getString(13));
                data.setCatchPhrase(cursor.getString(14));
                data.setBs(cursor.getString(15));





            cursor.close();
            if(!lists.get(i).getName().equals(data.getName())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_NAME+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(!lists.get(i).getUserName().equals(data.getUserName())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_USERNAME+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(!lists.get(i).getEmail().equals(data.getEmail())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_EMAIL+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(lists.get(i).getPhone()!=(data.getPhone())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_PHONE+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(!lists.get(i).getSite().equals(data.getSite())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_WEBSITE+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(!lists.get(i).getStreet().equals(data.getStreet())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_STREET+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(!lists.get(i).getUserName().equals(data.getUserName())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_SUITE+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(!lists.get(i).getUserName().equals(data.getUserName())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_CITY+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(!lists.get(i).getUserName().equals(data.getUserName())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_ZIPCODE+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(lists.get(i).getLat()!=(data.getLat())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_LAT+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }if(lists.get(i).getLng()!=(data.getLng())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_LNG+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }if(!lists.get(i).getUserName().equals(data.getUserName())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_COMPANY_NAME+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }if(!lists.get(i).getUserName().equals(data.getUserName())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_CATCH_PHRASE+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }if(!lists.get(i).getUserName().equals(data.getUserName())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_BS+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
    }
}
}
