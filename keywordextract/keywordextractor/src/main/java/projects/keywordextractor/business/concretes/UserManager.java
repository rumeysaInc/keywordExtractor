package projects.keywordextractor.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.keywordextractor.business.abstracts.UserService;
import projects.keywordextractor.core.dataAccess.UserDao;
import projects.keywordextractor.core.entities.UserEntity;
import projects.keywordextractor.core.utilities.results.DataResult;
import projects.keywordextractor.core.utilities.results.Result;
import projects.keywordextractor.core.utilities.results.SuccessDataResult;
import projects.keywordextractor.core.utilities.results.SuccessResult;

@Service
public class UserManager implements UserService {

    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Result add(UserEntity user) {
        this.userDao.save(user);
        return new SuccessResult ("Kullanıcı eklendi");
    }

    @Override
    public DataResult<UserEntity> getByEmail(String email) {
        return new SuccessDataResult<UserEntity>(this.userDao.getByEmail(email),"Kullanıcı bulundu");
    }


}
