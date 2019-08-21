package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.bean.Department;
import org.apache.ibatis.annotations.*;

//指定这是一个操作数据库的mapper
@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id = #{id}")
    public int deleteDeptById(Integer id);

    //设置了Options，当我们往数据库插入数据后，生成的主键id，会封装到当前这个对象
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id = #{id}")
    public int updateDept(Department department);

    //当开启驼峰标识时，可以这样使用 ||  #{departmentName}这个表示取对象中的指定属性，跟那个没关系
    //@Update("update department set department_name=#{departmentName} where id = #{id}")
}
