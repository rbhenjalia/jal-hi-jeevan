package sih.practice.teststs;

/**
  Created by Akshar on 3/12/2018.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager
{
    SharedPreferences pref;
 //   SharedPreferences preff;
    SharedPreferences.Editor editor;
    //SharedPreferences.Editor editorr;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "androidhive-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    /*private static final String IS_FIRST_TIME_LAUNCH_Lang = "IsFirstTimeLaunchLang";
    private static final String IS_FIRST_TIME_LAUNCH_Login = "IsFirstTimeLaunchLog";
    private static final String IS_FIRST_TIME_LAUNCH_Reg = "IsFirstTimeLaunchReg";
    private static final String IS_FIRST_TIME_LAUNCH_OTP = "IsFirstTimeLaunchOTP";*/


    public PrefManager(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }

    public void setFirstTimeLaunch(boolean isFirstTime)
    {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }
    public boolean isFirstTimeLaunch()
    {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    /*public void setFirstTimeLaunchLang(boolean isFirstTime)
    {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH_Lang, isFirstTime);
        editor.commit();
    }
    public boolean isFirstTimeLaunchLang()
    {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH_Lang, true);
    }

    public void setFirstTimeLaunchLog(boolean isFirstTime)
    {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH_Login, isFirstTime);
        editor.commit();
    }
    public boolean isFirstTimeLaunchLog()
    {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH_Login, true);
    }

    public void setFirstTimeLaunchReg(boolean isFirstTime)
    {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH_Reg, isFirstTime);
        editor.commit();
    }
    public boolean isFirstTimeLaunchReg()
    {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH_Reg, true);
    }

    public void setFirstTimeLaunchOTP(boolean isFirstTime)
    {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH_OTP, isFirstTime);
        editor.commit();
    }
    public boolean isFirstTimeLaunchOTP()
    {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH_OTP, true);
    }*/

}
