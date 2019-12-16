package com.example.unlimitedaliengames.userdata;

import android.content.Context;
/*
The Interface for UserManager, accessed by User.
 */
interface UserManagerInterface {
    /*
    Write (save) user data onto a file
     */
    void writeToFile(Context context);

}
