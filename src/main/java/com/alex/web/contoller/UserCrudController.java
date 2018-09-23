package com.alex.web.contoller;

import com.alex.web.domain.User;
import com.alex.web.exception.EntityNotFoundException;
import com.alex.web.storage.UserCrudDao;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;

@Singleton
public class UserCrudController implements CrudHandler {

    @Inject
    private UserCrudDao userCrudDao;

    @Override
    public void getAll(Context context) {
        context.json(userCrudDao.findAll());
    }

    @Override
    public void getOne(Context context, String id) {
        User user = userCrudDao.read(id);
        validateUser(user);
        context.json(user);
    }

    @Override
    public void create(Context context) {
        userCrudDao.create(extractUser(context));
    }

    @Override
    public void update(Context context, String id) {
        validateUser(userCrudDao.read(id));
        userCrudDao.update(extractUser(context));
    }

    @Override
    public void delete(Context context, String id) {
        userCrudDao.delete(id);
        context.status(204);
    }

    private void validateUser(User user) {
        if (null == user) {
            throw new EntityNotFoundException("User is not found");
        }
    }

    private User extractUser(Context context) {
        return context.bodyAsClass(User.class);
    }
}
