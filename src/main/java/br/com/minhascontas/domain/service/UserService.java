package br.com.minhascontas.domain.service;

import br.com.minhascontas.domain.entity.User;
import br.com.minhascontas.domain.query.filter.Filter;
import br.com.minhascontas.infra.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Rapha on 08/08/2019.
 */
@Service
public class UserService extends ServiceAbstract<User,Long> {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public Page<User> find(Filter<User> filter, Pageable pageable) {
        return userRepository.find(filter,pageable);
    }

    public User findByUser(String user) {
        return userRepository.findByUser(user);
    }

}
