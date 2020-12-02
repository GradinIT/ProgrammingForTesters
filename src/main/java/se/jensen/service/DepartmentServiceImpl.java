package se.jensen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jensen.aspects.TimeAndLogg;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.EntityAlreadyInStorageException;
import se.jensen.dao.EntityNotFoundException;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;

import java.util.List;
import java.util.Optional;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;

    @TimeAndLogg
    public List<Department> getDepartments() {
        return DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
    }

    @TimeAndLogg
    public Department getDepartmentById(Integer departmentId) {
        Optional<DepartmentDatabaseEntry> departmentDatabaseEntry = departmentDao.findById(departmentId);
        if (departmentDatabaseEntry.isPresent())
            return DepartmentDatabaseEntryMapper.map(departmentDatabaseEntry.get());
        throw new EntityNotFoundException(departmentId);
    }

    @TimeAndLogg
    public Department create(Department department) {
        Optional<DepartmentDatabaseEntry> departmentDatabaseEntry = departmentDao.findById(department.getDepartmentId());
        if(departmentDatabaseEntry.isPresent())
            throw new EntityAlreadyInStorageException(department.getDepartmentId());
        return DepartmentDatabaseEntryMapper.map(departmentDao.save(DepartmentDatabaseEntryMapper.map(department)));
    }

    @TimeAndLogg
    public Department update(Department department) {
        Optional<DepartmentDatabaseEntry> departmentDatabaseEntry = departmentDao.findById(department.getDepartmentId());
        if(departmentDatabaseEntry.isPresent())
            return DepartmentDatabaseEntryMapper.map(departmentDao.save(DepartmentDatabaseEntryMapper.map(department)));
        throw new EntityNotFoundException(department.getDepartmentId());
    }

    @TimeAndLogg
    public Department remove(Department department) {
        Optional<DepartmentDatabaseEntry> departmentDatabaseEntry = departmentDao.findById(department.getDepartmentId());
        if(departmentDatabaseEntry.isPresent()) {
            departmentDao.delete(DepartmentDatabaseEntryMapper.map(department));
            return department;
        }
        throw new EntityNotFoundException(department.getDepartmentId());
    }
}
