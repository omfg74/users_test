package com.example.omfg.test_task_user_list.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.omfg.test_task_user_list.Logic.DbInjector;
import com.example.omfg.test_task_user_list.Objects.ListData;
import com.example.omfg.test_task_user_list.Objects.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omfg on 18.11.2017.
 */

public class InputIntoDB implements Runnable{

    DBHelper dbHelper;
SQLiteDatabase db;
ContentValues contentValues;
List<User> lists;
List<User> dataList;
//ArrayList<ListData> insertList;
Cursor cursor;
Context context;
DbInjector dbInjector;
    public InputIntoDB(Context context, List<User> lists) {
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
              User data = new User();

              data.setId(cursor.getInt(1));
              data.setName(cursor.getString(2));
              data.setUsername(cursor.getString(3));
              data.setEmail(cursor.getString(4));
              data.setPhone(cursor.getString(5));
              data.setWebsite(cursor.getString(6));
              data.setAddress().setStreet(cursor.getString(7));
              data.setSuite(cursor.getString(8));
              data.setCity(cursor.getString(9));
              data.setZipcode(cursor.getString(10));
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
          List<User>insertList = lists;
insertWhatWeVGot(insertList);


      }else {
          //Прескорбно, что нет технической возможности проверить этот блок. Он отвечает за актуализацию информации.
         List<User> instartList= new ArrayList<>();
//          ArrayList<Integer>checkList= new ArrayList<>();//сомнитьельная надобность этой конструкции
          for (int i = 0; i <lists.size() ; i++) {
              int id = lists.get(i).getId();
              for (int j = 0; j <dataList.size() ; j++) {
                  if(id==dataList.get(j).getId()){
                      //проверка на соответствие всех данных
                      getAndCheck(i);
                      break;
                  }else {
                      if(j==dataList.size()-1){
//                          checkList.add(i);
                          User insertData = lists.get(i);
                          instartList.add(insertData);
                      }
                  }
              }
          }

          if(lists.size()>dataList.size()){
              for (int i = 0; i <lists.size()-dataList.size() ; i++) {
//                  checkList.add(dataList.size()+i+1);
                  User insertData = lists.get(i);
                  instartList.add(insertData);
              }


          }
          insertWhatWeVGot(instartList);
      }

    }
    private void insertWhatWeVGot(List<User>insertList){
        contentValues  = new ContentValues();
        for (int i = 0; i <insertList.size() ; i++) {
            contentValues.put(DBHelper.COLUMN_ID,insertList.get(i).getId());
            contentValues.put(DBHelper.COLUMN_NAME,insertList.get(i).getName());
            contentValues.put(DBHelper.COLUMN_USERNAME,insertList.get(i).getUsername());
            Log.d("USERNAME0"," "+insertList.get(i).getUsername());
            contentValues.put(DBHelper.COLUMN_EMAIL,insertList.get(i).getEmail());
            contentValues.put(DBHelper.COLUMN_PHONE,insertList.get(i).getPhone());
            contentValues.put(DBHelper.COLUMN_WEBSITE,insertList.get(i).getWebsite());
            contentValues.put(DBHelper.COLUMN_STREET,insertList.get(i).getAddress().getStreet());
            contentValues.put(DBHelper.COLUMN_SUITE,insertList.get(i).getAddress().getSuite());
            contentValues.put(DBHelper.COLUMN_CITY,insertList.get(i).getAddress().getCity());
            contentValues.put(DBHelper.COLUMN_ZIPCODE,insertList.get(i).getAddress().getZipcode());
            Log.d("ZIP_CONTENT"," "+insertList.get(i).getAddress().getZipcode());
//            contentValues.put(DBHelper.COLUMN_LAT,insertList.get(i).get);
//            contentValues.put(DBHelper.COLUMN_LNG,insertList.get(i).getLng());
            contentValues.put(DBHelper.COLUMN_COMPANY_NAME,insertList.get(i).getCompany().getName());
            contentValues.put(DBHelper.COLUMN_CATCH_PHRASE,insertList.get(i).getCompany().getCatchPhrase());
            contentValues.put(DBHelper.COLUMN_BS,insertList.get(i).getCompany().getBs());
            db.insert(DBHelper.TABLE_USERS,null,contentValues);
            contentValues.clear();


        }
        dbHelper.close();
    }
    private void getAndCheck(int i){
        cursor = db.query(dbHelper.TABLE_USERS,null,
                DBHelper.COLUMN_ID+  " = ?",
                new String[]{String.valueOf(lists.get(i).getId())},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()){
            User data = new User();



                data.setId(cursor.getInt(1));
                data.setName(cursor.getString(2));
                data.setUserName(cursor.getString(3));
                data.setEmail(cursor.getString(4));
                data.setPhone(cursor.getString(5));
                data.setSite(cursor.getString(6));
                data.setStreet(cursor.getString(7));
                data.setSuite(cursor.getString(8));
                data.setCity(cursor.getString(9));
                data.setZipcode(cursor.getString(10));
                data.setLat(cursor.getDouble(11));
                data.setLng(cursor.getDouble(12));
                data.setCompanyName(cursor.getString(13));
                data.setCatchPhrase(cursor.getString(14));
                data.setBs(cursor.getString(15));





            cursor.close();
            Log.d("DB compare "," "+lists.get(i).getName()+" "+(data.getName()));
            Log.d("DB compare "," "+lists.get(i).getId()+" "+(data.getId()));
            Log.d("I"," "+i);
            if(!lists.get(i).getName().equals(data.getName())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+"  "+DBHelper.COLUMN_NAME+ " = '"+lists.get(i).getName()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(lists.get(i).getUsername().equals(data.getUsername())){

            }else {
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_USERNAME+" = '"+lists.get(i).getUsername()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(!lists.get(i).getEmail().equals(data.getEmail())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_EMAIL+" = '"+lists.get(i).getEmail()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(lists.get(i).getPhone()!=(data.getPhone())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_PHONE+" = '"+lists.get(i).getPhone()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(!lists.get(i).getWebsite().equals(data.getSite())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_WEBSITE+" = '"+lists.get(i).getWebsite()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(!lists.get(i).getAddress().getStreet().equals(data.getStreet())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_STREET+" = '"+lists.get(i).getAddress().getStreet()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(!lists.get(i).getAddress().getSuite().equals(data.getSuite())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_SUITE+" = '"+lists.get(i).getAddress().getSuite()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(!lists.get(i).getAddress().getCity().equals(data.getCity())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_CITY+" = '"+lists.get(i).getAddress().getCity()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
            if(!lists.get(i).getAddress().getZipcode().equals(data.getZipcode())){
                Log.d("ZIPCODE0"," "+lists.get(i).getAddress().getZipcode()+" "+(data.getZipcode()));
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_ZIPCODE+" = '"+lists.get(i).getAddress().getZipcode()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
//            if(lists.get(i).getLat()!=(data.getLat())){
//                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_LAT+" = '"+lists.get(i).getLat()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
//                        +"'"+i+"'");
//            }if(lists.get(i).getLng()!=(data.getLng())){
//                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_LNG+" = '"+lists.get(i).getLng()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
//                        +"'"+i+"'");
//            }
            if(!lists.get(i).getCompany().getName().equals(data.getCompanyName())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_COMPANY_NAME+" = '"+lists.get(i).getCompany().getName()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }if(!lists.get(i).getCompany().getCatchPhrase().equals(data.getCatchPhrase())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_CATCH_PHRASE+" = '"+lists.get(i).getCompany().getCatchPhrase()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }if(!lists.get(i).getCompany().getBs().equals(data.getBs())){
                db.execSQL("UPDATE "+DBHelper.TABLE_USERS+" SET "+" "+DBHelper.COLUMN_BS+" = '"+lists.get(i).getCompany().getBs()+"' "+" WHERE "+DBHelper.COLUMN_ID + " = "
                        +"'"+i+"'");
            }
    }
}
}
