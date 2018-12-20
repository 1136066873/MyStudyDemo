





参考 ：https://blog.csdn.net/javensun/article/details/7334230

在Android应用开发中，有时会遇到以下两种情况，

1. 一些敏感的广播并不想让第三方的应用收到 ；

2. 要限制自己的Receiver接收某广播来源，避免被恶意的同样的ACTION的广播所干扰。



在这些场景下就需要用到广播的权限限制。

第一种场景： 谁有权收我的广播？

在这种情况下，可以在自己应用发广播时添加参数声明Receiver所需的权限。

首先，在Androidmanifest.xml中定义新的权限RECV_XXX，例如：



<permission android:name = "com.android.permission.RECV_XXX"/>
然后，在Sender app发送广播时将此权限作为参数传入，如下：

sendBroadcast("com.android.XXX_ACTION", "com.android.permission.RECV_XXX");

这样做之后就使得只有具有RECV_XXX权限的Receiver才能接收此广播要接收该广播，在Receiver应用的AndroidManifest.xml中要添加对应的RECV_XXX权限。
例如：

   <uses-permission android:name="com.android.permission.RECV_XXX"></uses-permission>



第二种场景： 谁有权给我发广播？

在这种情况下，需要在Receiver app的<receiver> tag中声明一下Sender app应该具有的权限。

首先同上，在AndroidManifest.xml中定义新的权限SEND_XXX，例如：

<permission android:name="com.android.SEND_XXX"/>

然后，在Receiver app的Androidmanifest.xml中的<receiver>tag里添加权限SEND_XXX的声明，如下：
<receiver android:name=".XXXReceiver"
          android:permission="com.android.permission.SEND_XXX">
	<intent-filter>
		 <action android:name="com.android.XXX_ACTION" />
	</intent-filter>
</receiver>



这样一来，该Receiver便只能接收来自具有该SEND_XXX权限的应用发出的广播。
要发送这种广播，需要在Sender app的AndroidManifest.xml中也声明使用该权限即可，如下：

<uses-permission android:name="com.android.permission.SEND_XXX"></uses-permission>


如此，可以用来对广播的来源与去处进行简单的控制。

同样，对Activity 和 ContentProvider的访问权限控制也类似。
---------------------
作者：WJ_S
来源：CSDN
原文：https://blog.csdn.net/javensun/article/details/7334230
版权声明：本文为博主原创文章，转载请附上博文链接！