package org.example.test03.controller;

import org.example.test03.model.User;
import org.example.test03.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}/add-money")
    public ResponseEntity<String> addMoney(@PathVariable Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            System.out.println(user.getId());
            user.setMoney(user.getMoney() + 100);
            userRepository.save(user);
            return ResponseEntity.ok("them 100 thanh cong " + user.getMoney());
        }
        return ResponseEntity.status(404).body("khong tim thay user");
    }
    // API chuyển tiền giữa hai user
    @GetMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestParam("fromID") Long fromUserId, @RequestParam("toID") Long toUserId) {
        Optional<User> fromUserOpt = userRepository.findById(fromUserId);
        Optional<User> toUserOpt = userRepository.findById(toUserId);

        if (fromUserOpt.isPresent() && toUserOpt.isPresent()) {
            User fromUser = fromUserOpt.get();
            User toUser = toUserOpt.get();

            if (fromUser.getMoney() >= 300) {
                fromUser.setMoney(fromUser.getMoney() - 300);
                toUser.setMoney(toUser.getMoney() + 300);

                userRepository.save(fromUser);
                userRepository.save(toUser);

                return ResponseEntity.ok("chuyen thanh cong. FromUser : " + fromUser.getMoney() + ", ToUser  " + toUser.getMoney());
            } else {
                return ResponseEntity.status(400).body("khong du tien de chuyen");
            }
        }
        return ResponseEntity.status(404).body("khong tim thay user");
    }
}
