package info.chhaileng.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import ch.qos.logback.core.net.SyslogOutputStream;
import info.chhaileng.app.model.AccKitPostRequest;
import info.chhaileng.app.model.PhoneUser;
import info.chhaileng.app.model.User;
import info.chhaileng.app.repository.mybatis.UserRepository;

@Controller
public class SigninController {
	
	@Autowired
	private UserRepository userRepo;
	
	private String account_kit_api_version = "v1.1";
    private String  app_id = "110325006282946";
    private String  app_secret = "8f9ace887d409bf31785b84ebda8a033";
    private String  me_endpoint_base_url = "https://graph.accountkit.com/v1.1/me";
    private String  token_exchange_base_url = "https://graph.accountkit.com/v1.1/access_token";

    private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/")
	public String root() {
		return "redirect:/signin";
	}
	
	@GetMapping("/signin")
	public String signin(ModelMap m) {
		m.addAttribute("AccKit", new AccKitPostRequest());
		return "signin";
	}
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@PostMapping("/signin/next")
	public String check(@ModelAttribute AccKitPostRequest accKitPostRequest, ModelMap m) {
		// Exchange access_token with server
        String uri = token_exchange_base_url + "?grant_type=authorization_code" + "&code=" +
                accKitPostRequest.getCode()+"&access_token=AA|"+app_id+"|"+app_secret;
        Object obj = restTemplate.getForObject(uri, Object.class);

        Map<String, Object> info = (HashMap<String,Object>) obj;
        String user_id = (String) info.get("id");
        String user_access_token = (String) info.get("access_token");
        int refresh_rate = (int) info.get("token_refresh_interval_sec");

        String me_endpoint_uri = me_endpoint_base_url+"?access_token="+user_access_token;
        Object me = restTemplate.getForObject(me_endpoint_uri,Object.class);

        //Set Phone User Object
        Map<String, Object> map = (HashMap<String,Object>) me;
        Map<String, Object> phone = (HashMap<String,Object>) map.get("phone");
        String number = (String) phone.get("number");

        PhoneUser pu = new PhoneUser();
        pu.setUser_id(user_id);
        pu.setPhone_number(number);
        pu.setAccess_token(user_access_token);

//        User user = userRepo.findUserByUid(pu.getUser_id());
//        if (user!=null){
//        	m.addAttribute("user", user);
//        	System.out.println(user);
//        	return "index";
//        }
        
        
        
        System.out.println("USER ID : " + pu.getUser_id());
        m.addAttribute("PHONEUSER",pu);
		return "success"; // signup acc
	}
}
