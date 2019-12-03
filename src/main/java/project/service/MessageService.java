package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.MessageDAO;
import project.domain.Message;
import project.domain.User;
import project.repos.MessageRepo;
import project.repos.UserRepo;

import java.util.List;

@Service
@Transactional
public class MessageService {

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    MessageRepo messageRepo;

    @Autowired
    UserRepo userRepo;

    public List<Message> getAll() {
        return messageDAO.getAll();
    }

    public List<Message> getThisToUser(User from, User to) {
        return messageDAO.getThisToUser(from, to);
    }

    public List<Message> getUserChats(User from) {
        return messageDAO.getUserChats(from);
    }

    public boolean uddMessage(User from, Long toId, String message) {

        if (message.equals("") || message == null) {
            return false;
        }

        User to = userRepo.getOne(toId);

        messageRepo.save(new Message(from, to, message));

        return true;
    }

    public void checkedAllMessages(Iterable<Message> messages, User user) {
        for (Message m : messages) {
            if (m.getUserTo().getId() == user.getId()) {
                m.setChecked(true);
            }
        }
    }
}
