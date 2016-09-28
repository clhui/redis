package control;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.Demo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xin on 15/1/7.
 */
@Controller
public class SentinelController {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@RequestMapping("/sentinelTest")
	@ResponseBody
	public Demo sentinelTest(final Model model,
			final HttpServletRequest request, final String action) {
		//先将java对象转换为json对象，在将json对象转换为json字符串
		JSONObject json = JSONObject.fromObject(new Demo());//将java对象转换为json对象
		String str = json.toString();//将json对象转换为字符串
		return new Demo();
	}

	@ExceptionHandler(value = { java.lang.Exception.class })
	@RequestMapping("/setValueToRedis")
	public String setValueToRedis(final Model model,
			final HttpServletRequest request, final String action)
			throws Exception {
//		CountCreater.setCount();
		String key = String.valueOf("1");
		Map<String,String> mapValue = new HashMap<String, String>();
		for (int i = 0; i < 1000; i++) {
			mapValue.put(String.valueOf(i), String.valueOf(i+1));
		}
		try {
			BoundHashOperations<String, String, String> boundHashOperations = redisTemplate
					.boundHashOps(key);
			boundHashOperations.putAll(mapValue);
			System.out.println("put key into redis");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return "sentinelTest";
	}


}
