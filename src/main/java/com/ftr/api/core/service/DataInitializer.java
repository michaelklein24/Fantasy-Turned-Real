package com.ftr.api.core.service;

import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.code.LeagueStatusCode;
import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.model.ParticipantModel;
import com.ftr.api.league.repository.LeagueRepository;
import com.ftr.api.league.repository.ParticipantRepository;
import com.ftr.api.user.helper.UserHelper;
import com.ftr.api.user.model.GlobalRole;
import com.ftr.api.user.model.GlobalRoleModel;
import com.ftr.api.user.model.PasswordModel;
import com.ftr.api.user.model.UserModel;
import com.ftr.api.user.repository.GlobalRoleRepository;
import com.ftr.api.user.repository.PasswordRepository;
import com.ftr.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepo;
    private final PasswordRepository passwordRepo;
    private final GlobalRoleRepository globalRoleRepo;
    private final LeagueRepository leagueRepo;
    private final ParticipantRepository participantRepo;

    @Override
    public void run(String... args) throws Exception {

        List<UserModel> userModels = createUsers();

        LeagueModel leagueModel = new LeagueModel();
        leagueModel.setName("Corner By The Bookshelf");
        leagueModel.setStatus(LeagueStatusCode.NOT_STARTED);
        leagueRepo.save(leagueModel);

        for (int i = 0; i < userModels.size(); i++) {
            UserModel userModel = userModels.get(i);
            PasswordModel passwordModel = new PasswordModel();
            passwordModel.setUserModel(userModel);
            passwordModel.setEncodedPassword("$2a$10$eC5IdxQRafWen9oW2UfMGOR.X0DSGffCN9Z0Qv1rU15vXrui5G/Ga");
            passwordModel.setActive(true);
            passwordModel.setCreatedDate(new Date());
            passwordModel.setExpiryDate(UserHelper.calculateExpiryDate(90));
            passwordRepo.save(passwordModel);

            GlobalRoleModel globalRoleModel = new GlobalRoleModel();
            globalRoleModel.setRole(i == 0 ? GlobalRole.ADMIN: GlobalRole.USER);
            globalRoleModel.setUserModel(userModel);
            globalRoleRepo.save(globalRoleModel);

            ParticipantModel participantModel = new ParticipantModel();
            participantModel.setLeagueModel(leagueModel);
            participantModel.setUserModel(userModel);
            participantModel.setLeagueRole(i == 0 ? LeagueRoleCode.ADMIN: LeagueRoleCode.USER);
            participantRepo.save(participantModel);
        }
    }

    private List<UserModel> createUsers() {
        List<UserModel> userModels = new ArrayList<>();

        UserModel userModel = new UserModel();
        userModel.setEmail("michaelklein@gmail.com");
        userModel.setUsername("michaelklein");
        userModel.setFirstName("michael");
        userModel.setLastName("klein");
        userModels.add(userRepo.save(userModel));

        userModel = new UserModel();
        userModel.setEmail("jacksonsanders@gmail.com");
        userModel.setUsername("jacksonsanders");
        userModel.setFirstName("michael");
        userModel.setLastName("klein");
        userModels.add(userRepo.save(userModel));

        userModel = new UserModel();
        userModel.setEmail("thomasvonbuettner@gmail.com");
        userModel.setUsername("thomasvonbuettner");
        userModel.setFirstName("thomas");
        userModel.setLastName("von buettner");
        userModels.add(userRepo.save(userModel));

        userModel = new UserModel();
        userModel.setEmail("chrislynvonbuettner@gmail.com");
        userModel.setUsername("chrislynvonbuettner");
        userModel.setFirstName("chrislyn");
        userModel.setLastName("von buettner");
        userModels.add(userRepo.save(userModel));

        userModel = new UserModel();
        userModel.setEmail("hannahfawcett@gmail.com");
        userModel.setUsername("hannahfawcett");
        userModel.setFirstName("hannah");
        userModel.setLastName("fawcett");
        userModels.add(userRepo.save(userModel));

        userModel = new UserModel();
        userModel.setEmail("codyschultz@gmail.com");
        userModel.setUsername("codyschultz");
        userModel.setFirstName("codys");
        userModel.setLastName("schultz");
        userModels.add(userRepo.save(userModel));

        userModel = new UserModel();
        userModel.setEmail("kellyschultz@gmail.com");
        userModel.setUsername("kellyschultz");
        userModel.setFirstName("kelly");
        userModel.setLastName("schultz");
        userModels.add(userRepo.save(userModel));

        return userModels;
    }

}
