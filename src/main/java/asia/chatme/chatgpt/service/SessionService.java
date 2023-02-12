package asia.chatme.chatgpt.service;

import asia.chatme.chatgpt.model.UserSession;

public interface SessionService {
    String getSessionId(UserSession userSession);
}
