package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.Notification;
import edu.library.libraryspringboot.dto.BookDTO;
import edu.library.libraryspringboot.dto.NotificationDTO;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;

public interface NotifService {

    int register(NotificationDTO notifDTO);

    NotificationDTO readOne(int nNo);

    void modify(NotificationDTO notifDTO);

    void remove(int nNo);

    PageResponseDTO<NotificationDTO> list(PageRequestDTO pageRequestDTO);

    void setViews(int nNo);
}
