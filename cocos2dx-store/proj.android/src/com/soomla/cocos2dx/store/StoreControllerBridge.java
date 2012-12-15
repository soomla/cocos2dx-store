package com.soomla.cocos2dx.store;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import com.soomla.store.*;
import com.soomla.store.exceptions.InsufficientFundsException;
import com.soomla.store.exceptions.NotEnoughGoodsException;
import com.soomla.store.exceptions.VirtualItemNotFoundException;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

/**
 * This bridge is used to let cocos2dx functions perform actions on StoreController (through JNI).
 *
 * You can see the documentation of every function in {@link StoreController}
 */
public class StoreControllerBridge {
    private static Activity mActivity          = null;
    private static Handler  mHandler           = null;
    private static IStoreAssets mStoreAssets   = null;
    private static String mPublicKey           = "";
    private static String mCustomSec           = "";

    public static void initialize(Handler handler,
                                  Cocos2dxGLSurfaceView mGLView,
                                  IStoreAssets storeAssets,
                                  String publicKey,
                                  String customSecret,
                                  Activity activity) {
        mHandler     = handler;
        mStoreAssets = storeAssets;
        mPublicKey   = publicKey;
        mCustomSec   = customSecret;
        mActivity    = activity;

        StoreEventHandlers.getInstance().addEventHandler(new EventHandlerBridge(mGLView));
    }

    static void initialize() {
        Log.d("SOOMLA", "initialize is called from java !");
        StoreController.getInstance().initialize(mStoreAssets, mPublicKey, mCustomSec);
    }

    static void storeOpening() {
        Log.d("SOOMLA", "storeOpening is called from java !");
        StoreController.getInstance().storeOpening(mActivity, mHandler);
    }

    static void storeClosing() {
        Log.d("SOOMLA", "storeClosing is called from java !");
        StoreController.getInstance().storeClosing();
    }

    static void buyCurrencyPack(String productId) throws VirtualItemNotFoundException {
        Log.d("SOOMLA", "buyCurrencyPack is called from java with productId: " + productId + " !");
        StoreController.getInstance().buyCurrencyPack(productId);
    }

    static void buyVirtualGood(String itemId) throws VirtualItemNotFoundException, InsufficientFundsException {
        Log.d("SOOMLA", "buyVirtualGood is called from java with itemId: " + itemId + " !");
        StoreController.getInstance().buyVirtualGood(itemId);
    }

    static void buyManagedItem(String productId) throws VirtualItemNotFoundException {
        Log.d("SOOMLA", "buyManagedItem is called from java with productId: " + productId + " !");
        StoreController.getInstance().buyManagedItem(productId);
    }

    static void equipVirtualGood(String itemId) throws NotEnoughGoodsException, VirtualItemNotFoundException {
        Log.d("SOOMLA", "equipVirtualGood is called from java with itemId: " + itemId + " !");
        StoreController.getInstance().equipVirtualGood(itemId);
    }

    static void unequipVirtualGood(String itemId) throws VirtualItemNotFoundException{
        Log.d("SOOMLA", "unequipVirtualGood is called from java with itemId: " + itemId + " !");
        StoreController.getInstance().unequipVirtualGood(itemId);
    }

    private static String TAG = "StoreControllerBridge";
}
