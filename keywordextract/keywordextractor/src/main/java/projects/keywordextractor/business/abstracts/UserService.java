package projects.keywordextractor.business.abstracts;

import projects.keywordextractor.core.entities.UserEntity;
import projects.keywordextractor.core.utilities.results.DataResult;
import projects.keywordextractor.core.utilities.results.Result;

public interface UserService {
    Result add(UserEntity user);

    DataResult<UserEntity> getByEmail(String email );
}
