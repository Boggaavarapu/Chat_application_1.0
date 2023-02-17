package com.example.WebDemo.ModelController;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
public class ControllerClass extends AbstractWebSocketHandler {

	
    private final List<WebSocketSession> webSocketSessions = new ArrayList<>();
    private HashMap<String,WebSocketSession> map=new HashMap<String,WebSocketSession>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,  TextMessage message) throws Exception {
    	JSONObject json = new JSONObject(message.getPayload());
    	System.out.println(json);
    	String sender=(String) json.get("sender1");
    	if (sender.equals("server")) {
    		map.put((String) json.get("usernam1"),session);
    	}
    	else {
    		session.sendMessage(message);
    		if ((boolean) json.get("sender1").equals("Group")) {
    			
        		for(WebSocketSession webSocketSession : webSocketSessions){
                	if (webSocketSession!=session) {
                		webSocketSession.sendMessage(message);
                	}
                    
                }
        	}
        	else {
        		WebSocketSession session1;
        		if (map.containsKey((String) json.get("sender1"))) {
        			session1=map.get((String) json.get("sender1"));
        			
        			session1.sendMessage(message);
            		
        		}
        		
        	}
    	}    	
    	
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	webSocketSessions.remove(session);
    }
}
