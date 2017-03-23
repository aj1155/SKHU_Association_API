package kr.ac.skhu.service;

import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.devices.exceptions.InvalidDeviceTokenFormatException;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manki Kim on 2017-03-21.
 */
@Service
public class PushService {

    @Value("${key.store}")
    private String keystore;

    @Value("${key.password}")
    private String password;

    public void push(String key,String message) throws CommunicationException, KeystoreException, InvalidDeviceTokenFormatException, JSONException {
        boolean sendSingle = true;
        List<String> tokens = new ArrayList<String>();
        tokens.add(key);



        PushNotificationManager pushManager = new PushNotificationManager();
        pushManager.initializeConnection(new AppleNotificationServerBasicImpl(keystore, password,false));
        List<PushedNotification> notifications = new ArrayList<PushedNotification>();

        PushNotificationPayload payload = PushNotificationPayload.complex();
        payload.addAlert(message);
        payload.addSound("default");
        if (sendSingle){

            Device device = new BasicDevice();
            device.setToken(tokens.get(0));
            PushedNotification notification = pushManager.sendNotification(device, payload);
            notifications.add(notification);
        }else{
            List<Device> device = new ArrayList<Device>();
            for (String token : tokens){
                device.add(new BasicDevice(token));
            }
            notifications = pushManager.sendNotifications(payload, device);
        }

        List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
        List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
        int failed = failedNotifications.size();
        int successful = successfulNotifications.size();


    }

}
