package com.binar.Batch7.Repository.oauth;

//import com.dibimbing.dibimbing.model.oauth.Client;
import com.binar.Batch7.Entity.oauth.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    Client findOneByClientId(String clientId);

}

