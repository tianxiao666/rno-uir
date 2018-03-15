package com.hgicreate.rno.xdr.backend.service;

import com.hgicreate.rno.xdr.backend.domain.Task;
import com.hgicreate.rno.xdr.backend.repository.TaskRepository;
import com.hgicreate.rno.xdr.backend.repository.search.TaskSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class TrainService {

    private final Logger log = LoggerFactory.getLogger(TrainService.class);

    private final TaskRepository taskRepository;

    private final TaskSearchRepository taskSearchRepository;

    public TrainService(TaskRepository taskRepository,
                        TaskSearchRepository taskSearchRepository
                        ){
        this.taskRepository = taskRepository;
        this.taskSearchRepository = taskSearchRepository;

    }

    public Page<Task> findAll(Pageable pageable) {
        return taskSearchRepository.findAll(pageable);
    }



    public Page<Task> findByNameAndStatusAndTime(String name,
                                                 String status,
                                                 LocalDateTime startTime,
                                                 LocalDateTime endTime,
                                                 String type,
                                                 Pageable pageable){
        if(name.trim().equals("") && !status.trim().equals("")){
            return taskRepository.findByStatusAndStartTimeBetweenAndTypeOrderByStartTimeDesc(status,startTime,endTime,type,pageable);
        }else if(name.trim().equals("") && status.trim().equals("")){
            return taskRepository.findByStartTimeBetweenAndTypeOrderByStartTimeDesc(startTime,endTime,type,pageable);
        }else if(!name.trim().equals("") && !status.trim().equals("")){
            return taskRepository.findByNameLikeAndStatusAndStartTimeBetweenAndTypeOrderByStartTimeDesc("%"+name+"%",status,startTime,endTime,type,pageable);
        } else{
            return taskRepository.findByNameLikeAndStartTimeBetweenAndTypeOrderByStartTimeDesc("%"+name+"%",startTime,endTime,type,pageable);
        }
    }

}
