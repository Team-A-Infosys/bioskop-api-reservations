package com.teamc.bioskop.Controller.mvc;

import com.teamc.bioskop.Model.User;
import com.teamc.bioskop.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class MVCUserController {
    private final UserService userService;

    /**
     * Get All Users
     */
    @GetMapping("/getusers")
    public String showUserList(Model model) {
        return paginatedSeats(1, model);
    }

    @GetMapping("/getusers/{page}")
    public String paginatedSeats(@PathVariable(value="page") int pageNumber, Model model){
        int pageSize = 10;
        Page<User> usersPage = this.userService.findPaginated(pageNumber, pageSize);
        List<User> usersList = usersPage.getContent();

        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages",usersPage.getTotalPages());
        model.addAttribute("totalItems",usersPage.getTotalElements());
        model.addAttribute("users",usersPage);

        return "users";
    }

    /**
     * Create New Users
     */
    @GetMapping("/add-user")
    public String formUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "add-user";
    }

    @PostMapping("/added-user")
    public String newUser(@ModelAttribute("user") User user) {
        this.userService.createUser(user);
        return "redirect:/getusers";
    }

    /**
     * Update USer
     */
    @GetMapping("/update/user/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = this.userService.getReferenceById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/user/{id}")
    public String updateUserById(@PathVariable("id") Long id,
                                 @ModelAttribute("user") User user) {
        user.setUserId(id);
        this.userService.updateUser(user, id);
        return "redirect:/getusers";
    }

    /**
     * Delete User
     */
    @GetMapping("/delete/user/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.findbyid(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id : " + id));
        userService.deleteUserById(id);
        return "redirect:/getusers";
    }
}