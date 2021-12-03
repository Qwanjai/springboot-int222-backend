package int222.backend.models.services;


import int222.backend.models.entities.User;
import int222.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userService")
@Scope("singleton")
public class UserService {
    @Autowired
    private UserRepository userRepo;


    public User getUserCurrent(Authentication auth){
        String userCurrent  = auth.getName();
        return    userRepo.findByUsername(userCurrent);
//        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public Boolean checkUserNameIsAlreadyExists(String userName){
        return userRepo.existsByUserName(userName);
    }
    public Boolean checkFullNameIsAlreadyExists(String fullName){
        return userRepo.existsByName(fullName);
    }
}
