# DAO start

DAO，Data Access Object Pattern，数据访问对象模式，用于把低级的数据访问API从高级业务中分离出来。

参与者：

* 数据访问对象接口 Data Access Object Interface

  定义了一个模型对象上要执行的标准操作

* 数据访问对象实体类 Data Access Object concrete class

  实现了上述接口，负责从数据源获取数据

* 模型对象/数值对象 Model Object / Value Object

  POJO，Plain Ordinary Java Object，简单Java对象，有一些属性和setter、getter方法

## 示例 StudentDAO

 ![数据访问对象模式的 UML 图](https://www.runoob.com/wp-content/uploads/2014/08/dao_pattern_uml_diagram.jpg) 

`Student.java` 创建数值对象

```java
public class Student {
   private String name;
   private int rollNo;
 
   Student(String name, int rollNo){
      this.name = name;
      this.rollNo = rollNo;
   }
 
   public String getName() {
      return name;
   }
 
   public void setName(String name) {
      this.name = name;
   }
 
   public int getRollNo() {
      return rollNo;
   }
 
   public void setRollNo(int rollNo) {
      this.rollNo = rollNo;
   }
}
```

`StudentDao.java` 实现数据访问对象接口

```java
import java.util.List;
 
public interface StudentDao {
   public List<Student> getAllStudents();
   public Student getStudent(int rollNo);
   public void updateStudent(Student student);
   public void deleteStudent(Student student);
}
```

`StudentDaoImpl.java` 实现数据访问对象实体类

```java
import java.util.ArrayList;
import java.util.List;
 
public class StudentDaoImpl implements StudentDao {
   
   //列表是当作一个数据库
   List<Student> students;
 
   public StudentDaoImpl(){
      students = new ArrayList<Student>();
      Student student1 = new Student("Robert",0);
      Student student2 = new Student("John",1);
      students.add(student1);
      students.add(student2);    
   }
   @Override
   public void deleteStudent(Student student) {
      students.remove(student.getRollNo()); // remove()是List类的方法
      System.out.println("Student: Roll No " + student.getRollNo() 
         +", deleted from database");
   }
 
   //从数据库中检索学生名单
   @Override
   public List<Student> getAllStudents() {
      return students;
   }
 
   @Override
   public Student getStudent(int rollNo) {
      return students.get(rollNo);
   }
 
   @Override
   public void updateStudent(Student student) {
      students.get(student.getRollNo()).setName(student.getName());
      System.out.println("Student: Roll No " + student.getRollNo() 
                         +", updated in the database");
   }
}
```

`DaoPatterDemo.java` 演示

```java
public class DaoPatternDemo {
   public static void main(String[] args) {
      StudentDao studentDao = new StudentDaoImpl();
 
      //输出所有的学生
      for (Student student : studentDao.getAllStudents()) {
         System.out.println("Student: [RollNo : "
            +student.getRollNo()+", Name : "+student.getName()+" ]");
      }
 
      //更新学生
      Student student =studentDao.getAllStudents().get(0);
      student.setName("Michael");
      studentDao.updateStudent(student);
 
      //获取学生
      studentDao.getStudent(0);
      System.out.println("Student: [RollNo : "
         +student.getRollNo()+", Name : "+student.getName()+" ]");      
   }
}
```