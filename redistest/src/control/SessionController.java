package control;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mk on 15/1/7.
 */
@Controller
@EnableRedisHttpSession
public class SessionController {
	@RequestMapping("/mySession")
	public String index( Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("testSession") == null) {
			System.out.println("session is null");
			request.getSession().setAttribute("testSession", "yeah");
		} else {
			System.out.println("not null");
			request.getSession().removeAttribute("testSession");
		}
		return "showSession";
	}

}
