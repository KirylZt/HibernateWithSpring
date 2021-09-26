package by.tms.controller;

import by.tms.dao.UserDao;
import by.tms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class TestController {

	@Autowired
	private UserDao userDao;

	@GetMapping("/save")
	public String save(){
		User user = new User(0, "test", "test", "Test");
		userDao.save(user);
		return "test";
	}

	@GetMapping("/update")
	public String update(){
		User user = new User(1, "new", "new", "Test");
		userDao.update(user);
		return "test";
	}

	@GetMapping("/findAll")
	public String findAll(){
		List<User> all = userDao.findAll();
		System.out.println(all);
		return "test";
	}

	@GetMapping("/findByUsername")
	public String findByUsername(){
		User test = userDao.findByUsername("test");
		System.out.println(test);
		return "test";
	}
}
