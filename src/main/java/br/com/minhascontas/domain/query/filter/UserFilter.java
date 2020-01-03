package br.com.minhascontas.domain.query.filter;

import br.com.minhascontas.domain.entity.User;

/**
 * Created by Rapha on 08/08/2019.
 */
public class UserFilter implements Filter<User> {

    private Long id;
    private String user;

    public UserFilter(Long id, String user) {
        this.id = id;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }
}
