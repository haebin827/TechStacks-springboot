package edu.library.libraryspringboot.service;

import edu.library.libraryspringboot.domain.Notification;
import edu.library.libraryspringboot.dto.NotificationDTO;
import edu.library.libraryspringboot.dto.PageRequestDTO;
import edu.library.libraryspringboot.dto.PageResponseDTO;
import edu.library.libraryspringboot.repository.NotifRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class NotifServiceImpl implements NotifService {

    private final NotifRepository np;
    private final ModelMapper mm;

    @Override
    public int register(NotificationDTO notifDTO) {

        Notification notif = mm.map(notifDTO, Notification.class);
        int nNo = np.save(notif).getNNo();

        return nNo;
    }

    @Override
    public NotificationDTO readOne(int nNo) {

        Optional<Notification> result = np.findById(nNo);
        Notification notif = result.orElseThrow();
        NotificationDTO notifDTO = mm.map(notif, NotificationDTO.class);

        return notifDTO;
    }

    @Override
    public void modify(NotificationDTO notifDTO) {

        Optional<Notification> result = np.findById(notifDTO.getNNo());
        Notification notif = result.orElseThrow();
        notif.change(notifDTO.getNTitle(), notifDTO.getNContent(), notifDTO.getNIsImp());
        np.save(notif);
    }

    @Override
    public void remove(int nNo) {
        np.deleteById(nNo);
    }


    @Override
    public PageResponseDTO<NotificationDTO> list(PageRequestDTO pageRequestDTO) {

        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("nNo");

        Page<Notification> result = np.searchAll(keyword, pageable);

        List<NotificationDTO> dtoList = result.getContent().stream()
                .map(notif -> mm.map(notif, NotificationDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<NotificationDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public void setViews(int nNo) {
        np.updateViews(nNo);
    }
}
