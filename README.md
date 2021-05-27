# Mentor-Finder

# Version
## 27/5/2021 
Basic implementation of Spring MVC: điền form và controller đơn giản.

# To-do (liên quan đến kỹ thuật)
- [ ] Kết nối Database MS SQL
- [ ] Tích hợp JPA
- [ ] Chuyển hết phần view sang Thymeleaf hoặc Spring MVC Tech
- [ ] Chuyển hết project sang Spring MVC thuộc Spring Boot

# Basic Note

Spring MVC dựa trên nền tảng Servlet, về bản chất thì flow không khác gì servlet.

Các controller đóng vai trò như servlet, tuy vậy không phải config trong web.xml. Không cần động chạm gì vào các file xml. Ngoài ra, **bắt buộc** phải để các controller trong folder com.prjmvc.controllers.

Mẫu một controller như sau:
```
@Controller
public class LoginController {

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String viewLogin(){
        return "accountLogin";
    }
    
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String doLogin( @RequestParam("userLoginName") String username,
		@RequestParam("userPassword") String password,
		HttpSession session,
		ModelMap modelMap) 
    {
        if(username.equalsIgnoreCase("acc1") && password.equalsIgnoreCase("123")) 
        {
            session.setAttribute("username", username);
            modelMap.addAttribute("username", username);
            modelMap.addAttribute("password", password);
	          return "redirect:/win"; 
	      } 
        else 
        {
	          modelMap.put("error", "Invalid Account");
	          return "accountLogin";
	      }
    }
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String viewRegister(){
        return "register";
    }
    
    @RequestMapping(value="/win", method = RequestMethod.GET)
    public String viewWin(){
        return "loginsuccess";
    }
```
*@Controller* dùng để định nghĩa class này là controller. *@RequestMapping* để định nghĩa đường dẫn *(value="/login")*, bằng phương thức GET hoặc POST. Method return ra tên file .jsp (vứt hết file .jsp vào thư mục của nó nó tự nhận, rồi chỉ điền **mỗi tên**) dùng để dẫn đến file view đó giống như trong servlet. Ngoài ra có thể return redirect hoặc forward đến một controller khác. Model/ModelMap là object di chuyển thông tin giữa giống các controller hoặc giữa các trang như request và response. Khi điền form thì nhận thông tin từ input bằng @RequestParam.

Một class controller có thể chứa nhiều mapping đến nhiều đường dẫn thay vì phải tạo mới một class Servlet cho mỗi một đường dẫn như trước. Xử lý .jsp sử dụng taglib JSTL đã biết. Tất cả chỉ có như thế. **Ngoài ra không động chạm đến các file khác dễ gây có vấn đề.**

### CỐ GẮNG TRA GOOGLE

