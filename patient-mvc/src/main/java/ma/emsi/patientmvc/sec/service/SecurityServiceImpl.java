package ma.emsi.patientmvc.sec.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.patientmvc.sec.entities.AppRole;
import ma.emsi.patientmvc.sec.entities.AppUser;
import ma.emsi.patientmvc.sec.repositories.AppRoleRepository;
import ma.emsi.patientmvc.sec.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {

    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;



    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new  RuntimeException("Passwords not match!");
        String hashedPWD = passwordEncoder.encode(password);
        AppUser appUser =new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = new AppRole();
        AppRole  appRoleName = appRoleRepository.findByRoleName(roleName);
        if (appRoleName!=null) throw new RuntimeException("Role "+roleName+" alredy exist");
        appRole=new AppRole();

        appRole.setRoleName(roleName);
        appRole.setDescription(description);

        AppRole savedAppRole = appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        AppUser appUser=appUserRepository.findByUsername (username);
        if (appUser==null) throw new RuntimeException("user not found");

        AppRole appRole=appRoleRepository.findByRoleName (roleName);
        if (appRole==null) throw new RuntimeException("user not found");

        appUser.getAppRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {

        AppUser appUser=appUserRepository.findByUsername (username);
        if (appUser==null) throw new RuntimeException("user not found");

        AppRole appRole=appRoleRepository.findByRoleName (roleName);
        if (appRole==null) throw new RuntimeException("user not found");

        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}
