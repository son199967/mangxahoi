package com.example.controller;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.exceptions.ConversationException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.UserExeption;
import com.example.model.Conversation;
import com.example.model.Message;
import com.example.model.User;
import com.example.model.DAO.IConversationDAO;
import com.example.model.DAO.IMessageDAO;
import com.example.model.DAO.UserDAO;



@Controller
public class ConversationController {

	@Autowired
	IConversationDAO conversationDAO;

	@Autowired
	IMessageDAO messeageDAO;

	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/showAllMyConversations", method = RequestMethod.GET)
	public String showConversations(HttpServletRequest request, HttpSession session, Model viewModel) {
		try {
		User user = (User) session.getAttribute("user");
		List<Conversation> myconverastions = null;
		System.out.println(user.getUserId());
		myconverastions = conversationDAO.getConversationById(user.getUserId());
		Collection<User> friends = null;
		try {
			friends = userDAO.allFriends(user);
		} catch (InvalidDataException e) {
			e.printStackTrace();
			return "error";
		}
		if (myconverastions == null || friends == null) {
			return "error";
		} else {
			viewModel.addAttribute("conversations", myconverastions);
			viewModel.addAttribute("friends", friends);
			return "showAllMyConversations";
		}
		}catch(Exception e) {
			e.printStackTrace();
			return "home";
		}
	}


	@RequestMapping(value = "/createConversation", method = RequestMethod.GET)
	public synchronized String createConversation(@ModelAttribute Message message, HttpServletRequest request,
			HttpSession session, Model viewModel) {
		User user = (User) session.getAttribute("user");
		int friendId = Integer.parseInt(request.getParameter("friendId"));
		int conversationId;
		try {
			User member;
			try {
				member = userDAO.getUserById(friendId);
			} catch (UserExeption e1) {
				e1.printStackTrace();
				return "error";
			} catch (InvalidDataException e1) {
				e1.printStackTrace();
				return "error";
			}
			
			try {
				conversationId = conversationDAO.MakeConversation(member, new Conversation());
			} catch (UserExeption e) {
				e.printStackTrace();
				return "error";
			}
		} catch (ConversationException e) {
			e.printStackTrace();
			return "error";
		}
		try {
			conversationDAO.setConversationToUsers(user.getUserId(), conversationId);
		} catch (ConversationException e) {
			e.printStackTrace();
			return "error";
		}
		try {
			conversationDAO.setConversationToUsers(friendId, conversationId);
		} catch (ConversationException e) {
			e.printStackTrace();
			return "error";
		}
		return "forward:showAllMyConversations";
	}

}