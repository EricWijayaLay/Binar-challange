package com.binar.Batch7.Repository.oauth;

//import com.dibimbing.dibimbing.model.oauth.Role;
import com.binar.Batch7.Entity.oauth.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Role findOneByName(String name);

    List<Role> findByNameIn(String[] names);
}
