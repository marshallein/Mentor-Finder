# Mentor-Finder


Gọi CurrentUserExtractorService để truy xuất ra User hiện tại khi đã đăng nhập xong.
Cứ thế mà phát triển lên từ đó.



# Version

## 12/7/2021
Sửa bảng Enrolled, thêm cột status, hoàn thiện chức năng enrolled, accept reject enrolled

## 7/7/2021
Sửa bảng Subject, thêm cột hình ảnh minh họa của môn học

## 17/6/2021
Sửa lại bảng Request, xong sơ bộ function Create Request

## 15/6/2021
Từ giờ tạo user để làm clg thì cứ register nhé.

## 6/10/2021
Phân quyền admin và user thành công
Giữ User Context
Đối chiếu entity từ bảng
Update table

## 3/6/2021
Thay đổi hoàn toàn sang Spring Boot, tích hợp thành công View và JPA, basic Entity managing.
FR: Login & Register.
## 27/5/2021 
Basic implementation of Spring MVC: điền form và controller đơn giản.

# To-do (liên quan đến kỹ thuật)
- [x] Advanced Security: Spring Security
- [x] Phân quyền
- [x] Kết nối Database MS SQL
- [x] Tích hợp JPA
- [x] Chuyển hết phần view sang Thymeleaf hoặc Spring MVC Tech
- [x] Chuyển hết project sang Spring MVC thuộc Spring Boot

# Config
Chỉnh sửa file application.properties cho phù hợp 
```
spring.datasource.url=jdbc:sqlserver://localhost /tên server, hoặc localhost/; databaseName=SWPTEST1 /tên database/
spring.datasource.username=sa /tài khỏan/
spring.datasource.password=1 /mật khẩu/
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.show-sql=true
spring.jpa.hibernate.dialect=org.hibernate.dialect.SQLServer2014Dialect /tùy version SQLServer/
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

```

# Directory Note
- Controller tại WebApp2/controller
- DAO Repository tại WebApp2/dao
- Entity tại WebApp2/entity
- Service truy vấn từ Repository ra tại WebApp2/repository
- HTML/Thymeleaf tại resources/templates
- css tại resources/static/css
- js tại resources/static/js
- Ảnh tại resource/static/image

- Nối css với html theo ĐÚNG syntax``` <link rel="stylesheet" type="text/css" th:href="@{/css/tên-folder/tên-file.css}" />```
- Nối background style css theo ĐÚNG syntax  ```url(/image/tên-folder/tên-file.png)```
# Technical Note

## Controller
Spring MVC dựa trên nền tảng Servlet, về bản chất thì flow không khác gì servlet.

Các controller đóng vai trò như servlet, tuy vậy không phải config bất kỳ cái gì, chỉ cần đáp file đúng vị trí.
Mẫu một controller như sau:
```
@Controller
public class LoginController {

     @GetMapping("/login")
    public String showSignUpForm() {
       
        return "SignIn";
    }

    @PostMapping("/login")
    public String checkLogin(@RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "remember", required = false) String remember, Model model)
    {
        Long uId = lcs.checkLoginInfo(username, password);
        if(uId == -1L)
        {
            model.addAttribute("error", "Invalid Account");
            return "SignIn";     
        }
        else
        {
            return "home";
        }
        
    }
```
*@Controller* dùng để định nghĩa class này là controller. *@RequestMapping* để định nghĩa đường dẫn *(value="/login")*, bằng phương thức GET hoặc POST. Method return ra tên file .jsp (vứt hết file .jsp vào thư mục của nó nó tự nhận, rồi chỉ điền **mỗi tên**) dùng để dẫn đến file view đó giống như trong servlet. Ngoài ra có thể return redirect hoặc forward đến một controller khác. Model/ModelMap là object di chuyển thông tin giữa giống các controller hoặc giữa các trang như request và response. Khi điền form thì nhận thông tin từ input bằng @RequestParam.

Một class controller có thể chứa nhiều mapping đến nhiều đường dẫn thay vì phải tạo mới một class Servlet cho mỗi một đường dẫn như trước. 

## View
Sử dụng Thymeleaf, về căn bản là không khác gì HTML. Thymeleaf được tích hợp khả năng tương tác với object như .jsp, có thể truy xuất dữ liệu từ object ra view
Các controller chuyển dữ liệu từ nó qua view và qua nhau bằng object Model. 
Thêm tag cho html : 
``` <html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"> ```
Nếu vẫn không được thì thêm plugin theo link này https://github.com/ArturWisniewski/NB-Thymeleaf-Code-Completion 
Mẫu:
```
 <form action="#" th:action="@{/greeting}" th:object="${greeting}" method="post">
                <p>Id: <input type="text" th:field="*{id}" /></p>
                <p>Message: <input type="text" th:field="*{content}" /></p>
                <p><input type="submit" value="Submit" /> </p>
 </form>
```

Đọc thêm tại: https://loda.me/spring-boot-9-giai-thich-cach-thymeleaf-van-hanh-expression-demo-full-loda1558267496214/
https://www.baeldung.com/spring-boot-crud-thymeleaf

## Database

Tích hợp một công nghệ có tên là JPA. Nếu như hồi trước phải tạo connection đến database, nhập query, lôi dữ liệu ra gán vào object rồi trả về controller, thì nay framework tự làm hết tất cả các việc đó cho ta.
JPA sở hữu các class Entity, tương ứng với một bảng trong database, các class này sẽ chiếu dữ liệu xuống thẳng database thay vì phải gọi nó lên mỗi lần truy xuất script.
```
@Entity
@Table(name = "LoginInfo", uniqueConstraints = { 
               @UniqueConstraint(columnNames = "lgUsername"), @UniqueConstraint(columnNames = "lgEmail")})
public class LoginInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lgId")
    private Long userid;
     

    @Column(name = "lgUsername")
    private String username;
    

    @Column(name = "lgEmail")
    private String email;
    
    @Column(name = "lgPassword")
    private String password;
 ```
 `@Entity` để định nghĩa đây là Entity. `@Table` và `@Column` định nghĩa tương ứng với sự vật. `name` phải đúng y hệt tên tương ứng trong bảng, ngoài ra tên biến đặt tùy thích.
 `@GeneratedValue(strategy = GenerationType.IDENTITY `để định nghĩa rằng biến @Id sẽ tự được gán vào như điều kiện trong database (ở đây là +1 theo IDENTITY(1,1)). Các Entity sẽ được thiết kế tương ứng với một bảng trong database để nhanh chóng chiếu xuống và gom dữ liệu (đó là lý do nên tách bảng thành các bảng nhỏ hơn tùy mục đích cho thuận tiện). Nếu có object nào cần dữ liệu đến từ nhiều bảng khác nhau, tạo class như bình thường tại WebApp2/model.
 
 Phần DAO sử dụng các interface Repository, ở đây là `JpaRepository` được extends từ `CrudRepository`. Long ở bên cạnh là Id của object Entity.
 ```
 @Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfo, Long> {
    
    @Nullable
    LoginInfo findByUsername(String username);
    
    @Nullable 
    LoginInfo findByEmail(String email);
            
}
```
Các hàm interface bên trên có nhiệm vụ đúng như những gì nó được định nghĩa. Điểm đặc biệt của repository bên trên đó là chỉ cần phải viết hàm có đúng mỗi cái tên, framework tự hiểu và tự generate những gì cần phục vụ. Đọc thêm tại 6.3 Query Method https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.
Ngoài ra, còn có thể custom query với `@Query`, tuy vậy được viết bằng một ngôn ngữ query language riêng, cú pháp gần tương tự SQL Server, xin mời tự tìm hiểu.

Các hàm `@Service` dùng để implement cách thức hoạt động của Repository, trước đó cần phải tạo object 
```
 @Autowired
    private LoginInfoRepository repo;
```
Từ đó có thể sử dụng một cách gián tiếp ở các hàm khác.
```
 public boolean checkEmail(String email)
    {
        LoginInfo result = repo.findByEmail(email);
        if(result==null)
        {
            System.out.println("there's no email");
            return true;
        }
        else
        {
            return false;
        }
    }
```
Có thể lưu trực tiếp một object Entity vào thẳng database chỉ bằng một một dòng lệnh repo đã được tạo sẵn:
```
public void saveNewRegister(LoginInfo lgIf)
    {
        repo.save(lgIf);
    }
```


### CỐ GẮNG TRA GOOGLE

