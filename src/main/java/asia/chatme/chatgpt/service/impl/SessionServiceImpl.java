package asia.chatme.chatgpt.service.impl;

import asia.chatme.chatgpt.mapper.UserSessionMapper;
import asia.chatme.chatgpt.model.UserSession;
import asia.chatme.chatgpt.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SessionServiceImpl implements SessionService {
    private final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

    @Resource
    private UserSessionMapper userSessionMapper;
    @Override
    public String getSessionId(UserSession userSession) {
        UserSession checkSession = userSessionMapper.selectSession(
                userSession.getSessionId());
        if (checkSession != null) {
            return checkSession.getSessionId();
        }
        userSession.setCreateTime(new Date());
        userSession.setRounds(0);
        userSession.setTokens(0);
        Integer ins = userSessionMapper.insert(userSession);
        return userSession.getSessionId();
    }
}
