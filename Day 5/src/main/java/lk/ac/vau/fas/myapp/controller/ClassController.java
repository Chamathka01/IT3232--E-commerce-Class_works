import lk.ac.vau.fas.myapp.model.Course;

@RestController
@RequestMapping("/course")
public class ClassController {
    Course ecommerce = new Course("IT3232","E-Commerce",2);
    Course webappservice = new Course("IT2234","Web Service And Server Technology",4);
    Course webapplication = new Course("CSC3132","Web Application Development",2);

    getMap().put(ecommerce.getCode(), ecommerce);
    getMap().put(webappservice.getCode(),webappservice);
    getMap().put(webapplication.getCode(),webapplication);

}
