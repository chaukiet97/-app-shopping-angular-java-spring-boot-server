package com.fe2.project.rest.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fe2.project.rest.entities.Contact;
import com.fe2.project.rest.repositories.ContactRepositories;
import com.fe2.project.rest.repositories.DTO.ReplyContact;
import com.fe2.project.rest.repositories.DTO.Response;
import com.fe2.project.rest.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "*")
public class ContactController {
    @Autowired
    private ContactRepositories contactRepositories;

    @Autowired
    private EmailService emailService;

    @GetMapping("/getAllContact")
    public Response getAllContact() {
        List<Contact> contacts = contactRepositories.findAll();
        if (contacts.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", contacts);
    }

    @GetMapping("/getContactByID/{id}")
    public Response getContactByID(@PathVariable Integer id) {
        Optional<Contact> contacts = contactRepositories.findById(id);
        if (contacts.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        return new Response(HttpStatus.OK.value(), "success", contacts);
    }

    @PostMapping("/updateContact/{id}")
    public Response updateContact(@RequestBody ReplyContact replyContact, @PathVariable Integer id) {
        Optional<Contact> contacts = contactRepositories.findById(id);
        if (contacts.isEmpty()) {
            return new Response(HttpStatus.NOT_FOUND.value(), "Không tồn tại dữ liệu", null);
        }
        replyContact.setDay_reply(new Date());
        replyContact.setStatus(1);
        contactRepositories.replyContact(replyContact.getDay_reply(), replyContact.getMaker_id(),
                replyContact.getReply(), replyContact.getStatus(), id);
        String body_email = "Cảm ơn quý khách " + contacts.get().getName() + " đã gởi feedback cho chúng tôi\n" + "\n"
                + "Chủ để : " + contacts.get().getSubject() + "\n" + "Nội dung feedback của bạn : "
                + contacts.get().getMessage() + "\n" + "Thời gian feedback : " + contacts.get().getDay_send() + "\n"
                + "Nội dung Chúng tôi trả lời : " +replyContact.getReply() + "\n" + "\n";
        emailService.sendMail(contacts.get().getEmail(), "Trả lời feedback của " + contacts.get().getName(),
                body_email);
        return new Response(HttpStatus.OK.value(), "success", null);
    }
}
