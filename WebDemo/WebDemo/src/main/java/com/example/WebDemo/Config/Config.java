package com.example.WebDemo.Config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.WebDemo.Model.Data;
import com.example.WebDemo.ModelController.ControllerClass;
import com.example.WebDemo.Repository.DataRepo;
@Configuration
@EnableWebSocket
@RestController
public class Config implements WebSocketConfigurer {
	@Autowired
	private DataRepo jp1;
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new ControllerClass(), "/chat")
        .setAllowedOrigins("*");
		List<Data> ud1=jp1.findAll();
		for(Data ud2:ud1) {
			ud2.setLogin(false);
			jp1.save(ud2);
		}
		
	}
			

}
