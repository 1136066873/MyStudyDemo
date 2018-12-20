package com.heguodong.study.broadcastwithpermission;

/**
 * Created by 01438511 on 2018/12/20.
 */

public class Constant {

    //操作另一个应用的时候需要申请的权限。
    public static final String BROADCAST_USES_PERMISSION_OPERATING_RECEIVE_APP = "com.android.permission.SEND_XXX";

    //另一个应用的包名。
    public static final String ANOTHER_APP_PACKAGENAME = "com.heguodong.study.broadcastwithpermission_receive";

    //另一个应用的 action 信息。
    public static final String ANOTHER_APP_ACTION = "com.android.action.ANOTHER_APP_ACTION";



    //本应用 Receiver 的action 信息。
    public static final String MY_APP_ACTION = "com.android.action.MY_APP_ACTION";

    //本应用 Receiver 的 权限信息。
    public static final String BROADCAST_USES_PERMISSION_OPERATING_MY_APP = "com.android.permission.AAA";
}
