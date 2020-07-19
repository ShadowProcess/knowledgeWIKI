package com.example.springboottransaction.serviceImpl;

import com.example.springboottransaction.dao.StudentDao;
import com.example.springboottransaction.model.Student;
import com.example.springboottransaction.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Spring 业务实现类
 */

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public void add(Student student) {
        studentDao.save(student);
    }
}
