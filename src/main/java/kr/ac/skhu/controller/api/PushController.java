package kr.ac.skhu.controller.api;

import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.exceptions.InvalidDeviceTokenFormatException;
import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.domain.UserKey;
import kr.ac.skhu.repository.UserKeyRepository;
import kr.ac.skhu.service.PushService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Manki Kim on 2017-03-22.
 *
 */
@RestController
@RequestMapping("/api/v1/push")
public class PushController {

    @Autowired
    private UserKeyRepository userKeyRepository;

    @Autowired
    private PushService pushService;

    @GetMapping("/{userId}")
    public AsctApiResponse pushMessage(@PathVariable int userId) throws KeystoreException, CommunicationException, JSONException, InvalidDeviceTokenFormatException {
        UserKey userKey = this.userKeyRepository.findByUserId(userId);
        this.pushService.push(userKey.getUserKey(),"김한수님의 생일입니다. 축하 메시지를 보내주세요~");
        return new AsctApiResponse(AsctApiResponse.OK);
    }
}
