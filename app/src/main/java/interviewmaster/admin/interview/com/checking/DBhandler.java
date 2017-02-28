package interviewmaster.admin.interview.com.checking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBhandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "employee.db";
    private static final String TABLE_NAME = "employeedata";

    private static final String KEY_ID = "employeeid";
    private static final String KEY_FIRST_NAME = "employeefirstname";
    private static final String KEY_LAST_NAME = "employeelastname";
    private static final String KEY_ADDRESS = "employeeaddress";
    private static final String KEY_CITY = "employeecity";
    private static final String KEY_ZIPCODE = "employeezipcode";
    private static final String KEY_GENDER = "employeegender";
    private static final String KEY_DOB = "employeedob";
    private static final String KEY_DESIGNATION = "employeedesignation";
    private static final String KEY_MOBILE = "employeemobile";
    private static final String KEY_EMAIL = "employeemail";
    private static final String KEY_NATIONALITY = "employeenationality";
    private static final String KEY_LANGUAGE = "employeelanguage";
    private static final String KEY_IMAGEURL = "employeeimageurl";
    private static final String KEY_TECHNICAL = "employeetechnical";
    private static final String KEY_EXTRACURRICULAR = "employeeextracurricular";
    List<Employee> array;

    String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " TEXT," + KEY_FIRST_NAME + " TEXT," + KEY_LAST_NAME + " TEXT," + KEY_ADDRESS + " TEXT," + KEY_CITY
            + " TEXT," + KEY_ZIPCODE + " TEXT," + KEY_GENDER + " TEXT," + KEY_DOB + " TEXT," + KEY_DESIGNATION + " TEXT," + KEY_MOBILE + " TEXT," + KEY_EMAIL + " TEXT," + KEY_NATIONALITY + " TEXT," + KEY_LANGUAGE + " TEXT," +
            KEY_IMAGEURL + " TEXT," + KEY_TECHNICAL + " TEXT," + KEY_EXTRACURRICULAR + " TEXT" + ")";
    String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DBhandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }


    public void addFoodCountryName(List<Employee> item) {
        this.array = item;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            for (int y = 0; y < array.size(); y++) {
                values.put(KEY_ID, array.get(y).getId());
                values.put(KEY_FIRST_NAME, array.get(y).getFirstName());
                values.put(KEY_LAST_NAME, array.get(y).getLastName());
                values.put(KEY_ADDRESS, array.get(y).getAddress());
                values.put(KEY_CITY, array.get(y).getCity());
                values.put(KEY_ZIPCODE, array.get(y).getZipcode());
                values.put(KEY_GENDER, array.get(y).getGender());
                values.put(KEY_DOB, array.get(y).getDob());
                values.put(KEY_DESIGNATION, array.get(y).getDesignation());
                values.put(KEY_MOBILE, array.get(y).getMobile());
                values.put(KEY_EMAIL, array.get(y).getEmail());
                values.put(KEY_NATIONALITY, array.get(y).getNationality());
                values.put(KEY_LANGUAGE, array.get(y).getLanguage());
                values.put(KEY_IMAGEURL, array.get(y).getImageURL());
                values.put(KEY_TECHNICAL, array.get(y).getSkills().get(0).getTechnical().toString().replace("[", "").replace("]", ""));
                values.put(KEY_EXTRACURRICULAR, array.get(y).getSkills().get(0).getExtraCurricular().toString().replace("[", "").replace("]", ""));
                db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            }
            db.close();
        } catch (Exception e) {
            Log.e("problem", e + "");
        }
    }


    public ArrayList<Employee> getAllFoodCountryName() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Employee> cityList = null;
        ArrayList<Example> xyz = null;
        try {
            cityList = new ArrayList<>();
            String QUERY = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    Employee item = new Employee();
                    item.setId(cursor.getString(0));
                    item.setFirstName(cursor.getString(1));
                    item.setLastName(cursor.getString(2));
                    item.setAddress(cursor.getString(3));
                    item.setCity(cursor.getString(4));
                    item.setZipcode(cursor.getString(5));
                    item.setGender(cursor.getString(6));
                    item.setDob(cursor.getString(7));
                    item.setMobile(cursor.getString(8));
                    item.setEmail(cursor.getString(9));
                    item.setNationality(cursor.getString(10));
                    item.setLanguage(cursor.getString(11));
                    item.setImageURL(cursor.getString(13));
                    Skill skill = new Skill();
                    skill.setTechnical(Arrays.asList(cursor.getString(14).split(",")));
                    skill.setExtraCurricular(Arrays.asList(cursor.getString(15).split(",")));
                    List<Skill> skills = new ArrayList<>();
                    skills.add(skill);
                    item.setSkills(skills);
                    item.setSkills(new ArrayList<Skill>());
                    cityList.add(item);

                }
            }
            db.close();
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return cityList;
    }

}